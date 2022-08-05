package Chapter15.Composite;

/**
 * Test the composite design pattern
 * Used with AbstractFile(example for composite design)
 * @author Woo
 *
 */
/*
 * Composite provides a perfect solution for dealing with tree structure and describe
 * how to use the recursion with the same method to both composite and leaf which means 
 * client can treat Leaf and Composite uniformly 
 * 
 * When a specific method is called from an object inside the leaf Collection, the 
 * whole tree structure will be traversed and search the object which has the same 
 * method. This whole process is fulfilled by recursion. 
 */
public class CompositeTest {
	public static void main(String[] args) {
		AbstractFile F2, F3, F5, F6;
		Folder F1 = new Folder("Src"); // composite needs to be initialized separately
		F2 = new ImageFile("Photo.jpg");
		F3 = new TextFile("Text.txt");
		F2.killVirus(); // treat part and whole objects uniformly, use the same method
		
		System.out.println("----------------");
		
		F1.Add(F2);
		F1.Add(F3);
		// F2.killVirus() will be executed
		F1.killVirus(); // treat part and whole objects uniformly, use the same method
		
		System.out.println("----------------");
		
		Folder F4 = new Folder("Lib"); // composite needs to be initialized separately
		F5 = new VideoFile("One Piece.avi");
		F6 = new VideoFile("Death Note.wav");
		F4.Add(F5);
		F4.Add(F6);
		F1.Add(F4); // Folder contains Folder, Leaf and Composite both implement the same
					// interface
		F1.killVirus(); 
	}
}
