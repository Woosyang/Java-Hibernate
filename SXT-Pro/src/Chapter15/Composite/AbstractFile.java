package Chapter15.Composite;
import java.util.*;
/**
 * Test the composite design pattern
 * Simulate a virus killer as the example for composite
 * @author Woo
 *
 */
// Component
public interface AbstractFile {
	public void killVirus();
}

// Leaf
class ImageFile implements AbstractFile {
	private String Name;
	
	public ImageFile(String Name) {
		this.Name = Name;
	}
	
	@Override
	public void killVirus() {
		System.out.println("ImageFile: " + Name + " is being checked..");
	}
}

class TextFile implements AbstractFile {
	private String Name;
	
	public TextFile(String Name) {
		this.Name = Name;
	}
	
	@Override
	public void killVirus() {
		System.out.println("TextFile: " + Name + " is being checked..");
	}
}

class VideoFile implements AbstractFile {
	private String Name;
	
	public VideoFile(String Name) {
		this.Name = Name;
	}
	
	@Override
	public void killVirus() {
		System.out.println("VideoFile: " + Name + " is being checked..");
	}
}

// composite
class Folder implements AbstractFile {
	private String Name;
	private List<AbstractFile> List; // collection of leaf(children) from this composite
	public Folder(String Name) {
		List = new ArrayList<>();
		this.Name = Name;
	}
	
	public void Add(AbstractFile F) {
		List.add(F);
	}
	
	public void Remove(AbstractFile F) {
		List.remove(F);
	}
	
	public AbstractFile getChild(int Index) {
		return List.get(Index);
	}
	
	@Override
	public void killVirus() {
		System.out.println("FolderFile: " + Name + " is being checked..");
		// traverse the collection of the leaf of this composite
		for (AbstractFile F : List) {
			F.killVirus(); // it can be a recursion if the F is still a Folder
		}
	}
}
