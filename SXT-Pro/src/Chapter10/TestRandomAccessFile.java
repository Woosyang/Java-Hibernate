package Chapter10;
import java.io.*;

/**
 * Test RandomAccessFile
 * Idea of spliting the file
 * @author Woo
 *
 */
public class TestRandomAccessFile {
	
	// split method          mark
	public static void Split(int i, int BeginPos, int ActualSize) throws Exception {
		RandomAccessFile Raf01 = new RandomAccessFile(new File("src/Chapter10/CountFileSize.java"), "r");
		File Des01 = new File("Dest");
		Des01.mkdirs();
		File Des02 = new File(Des01.getName() + "/" + (i + 1) + "-" + "SubFile.java" );
		RandomAccessFile Raf02 = new RandomAccessFile(Des02, "rw"); // will create the file automatically
		Raf01.seek(BeginPos); // designate the starting position
		int ASize = ActualSize;
		int Len = 0; // receiving length
		byte Flush[] = new byte[1024];
		while (Len != -1) {
			Len = Raf01.read(Flush);
			if (Len == -1) {
				break;
			}
			if (ASize > Len) {
				System.out.println(new String(Flush, 0, Len));
				Raf02.write(Flush, 0, Len);
				ASize = ASize - Len;
			} else {
				System.out.println(new String(Flush, 0, ASize));
				Raf02.write(Flush, 0, ASize);
				break; // reading is over
			}
		}
		Raf01.close();
		Raf02.close();
	}
	
	public static void main(String[] args) throws Exception {
		//                 How To Split the File                    //
		//----------------first step of this idea----------------------//
		/* Practice:
		// eg: skip 2 byte and then start to read
		RandomAccessFile Raf = new RandomAccessFile(new File("D:/Eclipse/eclipseJava/workspace/SXT-Pro/src/Chapter10/CountFileSize.java"), "r");
		// random process
		Raf.seek(2); // starts from the third byte of the beginning in the text  
		// read: segment by segment
		byte Flush[] = new byte[1024];
		int Len = 0; // receiving length
		while (Len != -1) {
			Len = Raf.read(Flush);
			if (Len == -1) {
				break;
			}
			String Str = new String(Flush, 0, Len);
			System.out.println(Str);
		}
		Raf.close();
		*/
		
		//----------------second step of this idea----------------------//
		/*         encapsulate the code below into a split method
		// designate the position of starting to read, has the actual size(block)
		//                                                        relative path                   
		RandomAccessFile Raf = new RandomAccessFile(new File("src/Chapter10/CountFileSize.java"), "r");
		// the position of beginning to read
		int BeginPos = 2;
		// the size you want to have
		int ActualSize = 1026;
		// the position you want to start to read
		Raf.seek(BeginPos);
		// read
		byte Flush[] = new byte[1024];
		int Len = 0;
		while (Len != -1) {
			Len = Raf.read(Flush);
			if (Len == -1) {
				break;
			}
			if (ActualSize > Len) {
				// require all of the data which is read by this time
				System.out.println(new String(Flush, 0, Len));
				ActualSize = ActualSize - Len;
			} else {
				System.out.println(new String(Flush, 0, ActualSize));
				break; // reading is over, reset of the text are spaces
			}
		}
		Raf.close();
		*/
		
		//----------------third step of this idea----------------------//
		// divide the file into a desired number of blocks for file according to the actual size
		File Src = new File ("src/Chapter10/CountFileSize.java");
		long Len = Src.length();
		int BlockSize = 1024; // units of the size of the file being divided
		// how many blocks
		int Num = (int) Math.ceil(Len * 1.0 / BlockSize); // Math.ceil(double) ---> needs to be cast
		System.out.println(Num);
		int BeginPos = 0;
		// ActualSize:
		// if BlockSize is greater than File's size, use the BlockSize as the initial remaining size
		// otherwise use the File's size as the initial remaining size
		// not necessary
		// int ActualSize = (int) (BlockSize > Len ? Len : BlockSize); // cast
		int ActualSize;
		for (int i = 0; i < Num; i ++) {
			BeginPos = i * BlockSize;
			if (i == Num - 1) { // final block of the file
				ActualSize = (int) Len;  // Len is the remaining size of the file
			} else {
				ActualSize = BlockSize;
				Len = Len - ActualSize;
			}
			System.out.println("No." + (i + 1) + " Block" + "-->" + "the position starts to read: " + BeginPos + "th byte" + "-->" + "the reading size:" + ActualSize + "bytes");
			Split(i, BeginPos, ActualSize);
			System.out.println("-----------------------------");
		}
	}
}
