package Chapter07;

public class TestArrays02 {
	public static void main(String[] args) {
		
		// static initialization
		int[] a = {2, 4, 6};
		User[] b = {new User(004, "Sam"),
					new User(005, "James"),
					new User(006, "Peter")
					};
		
		// default initialization
		int[] c = new int[3]; // assign the values to the elements in default format
		// the principle of assigning a value is the same as 
		// the principle of assigning a value to the member variable in default format
		
		// dynamic initialization
		int[] d = new int[2]; // allocate the space for the array first
		d[0] = 1; // then assign the value to the corresponding element
		d[1] = 2; 
		d[2] = 3;
	}
}
