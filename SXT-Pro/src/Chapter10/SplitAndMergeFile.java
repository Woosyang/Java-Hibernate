package Chapter10;
import java.io.*;
import java.util.*;

/**
 * Based on TestRandomAccessFile
 * Encapsulation
 * has the method that can split the file
 * has the method that can merge the pieces of file is splitted back into one file
 * @author Woo
 *
 */
public class SplitAndMergeFile {
	
	private File Src;
	private String DirPath; // the path of the file
	private String SrcPath; // the path of the directory
	private List<String> DesPath;  // needs to be initialized
	private int BlockSize; // the units you want to split
	private int Num;
	
	// constructor
	public SplitAndMergeFile(String SrcPath, String DirPath, int BlockSize) {
		this.SrcPath = SrcPath;
		this.DirPath = DirPath;
		this.DesPath = new ArrayList<String>();
		this.BlockSize = BlockSize;
		// initialize
		this.Src = new File(this.SrcPath);
		if (! Src.exists()) {
			throw new RuntimeException("File Not Found");
		}
		File Dir = new File(this.DirPath);
		if (! Dir.exists()) {
			Dir.mkdirs();
		}
	}
	
	// split the file
	// calculate the starting position
	// get the actual size of each subfiles(block)
	public void Split() throws IOException {
		int ActualSize;
		int BeginPos = 0;
		long Len = this.Src.length();
		this.Num = (int) Math.ceil(Src.length() * 1.0 / BlockSize); // how many blocks
		// store the path of the subfiles
		for (int i = 0; i < Num; i ++) {
			this.DesPath.add(this.DirPath + "/" + (i + 1) + "Sub" + Src.getName());
		}
		for (int i = 0; i < this.Num; i ++) {
			BeginPos = i * BlockSize;
			if (i == this.Num - 1) {
				ActualSize = (int) Len;
			} else {
				ActualSize = BlockSize;
				Len = Len - BlockSize; // same as Len - ActualSize
			}
			Excute(i, BeginPos, ActualSize);
		}
	}
	
	// use for split method
	public void Excute(int i, int BeginPos, int ActualSize) throws IOException {
		RandomAccessFile Raf01 = new RandomAccessFile(Src, "r"); // read the target
		RandomAccessFile Raf02 = new RandomAccessFile(new File(this.DesPath.get(i)), "rw");
		Raf01.seek(BeginPos); // starting position to read from the source
		int Len = 0; // receiving length
		int RemainSize = ActualSize; // the rest of the file after reading byte[1024] each time  
		byte Flush[] = new byte[1024];
		while (Len != -1) {
			Len = Raf01.read(Flush);
			if (Len == -1) {
				break;
			}
			if (RemainSize > Len) {
				Raf02.write(Flush); // same as write(Flush, 0, Len) in this situation
				RemainSize = RemainSize - Len;
			} else {
				Raf02.write(Flush, 0, ActualSize);
				break; // nothing left
			}
		}
		Raf01.close();
		Raf02.close();
	}
	
	// use InputStream and OutputStream
	//          the destination for restoring the file
	public void Merge01(String FilePath) throws IOException { // FileOutPutStream(File Src, true) --> append
		OutputStream Os = new BufferedOutputStream(new FileOutputStream(FilePath, true));
		for (int i = 0; i < this.Num; i++) {
			// copy
			int Len = 0;
			byte Flush[] = new byte[1024];
			BufferedInputStream Is = new BufferedInputStream(new FileInputStream(this.DesPath.get(i)));
			while (Len != -1) {
				Len = Is.read(Flush);
				if (Len == -1) {
					break;
				}
				Os.write(Flush, 0, Len);
			}
			Os.flush();
			Is.close();
		}
		Os.close();
	}
	
	// use Vector and SequenceStream
	public void Merge02(String FilePath) throws IOException {
		OutputStream Os = new BufferedOutputStream(new FileOutputStream(FilePath));
		Vector<InputStream> Vi = new Vector<InputStream>();
		SequenceInputStream Sis = null; //
		for (int i = 0; i < Num; i ++) {
			Vi.add(new BufferedInputStream(new FileInputStream(this.DesPath.get(i))));
		}
		Sis = new SequenceInputStream(Vi.elements()); //
		// copy outside the loop
		int Len = 0;
		byte Flush[] = new byte[1024];
		while (Len != - 1) {
			Len = Sis.read(Flush);
			if (Len == -1) {
				break;
			}
			Os.write(Flush, 0, Len);
		}
		Os.flush();
		Sis.close();
		Os.close();
	}
	
	public static void main(String[] args) throws IOException {
		SplitAndMergeFile Samf = new SplitAndMergeFile("src/Chapter10/SplitAndMergeFile.java", "Try", 1024 );
		Samf.Split();
		Samf.Merge01("NotCopy.java");
		Samf.Merge02("Love.java");
	}
}
