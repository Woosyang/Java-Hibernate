package Chapter05;

/**
 * Test the usual usage of the String
 * @author Woo
 *
 */

public class TestString02 {
	public static void main(String[] args) {
		String s = "";
        String s1 = "How are you?";
        System.out.println(s1.startsWith("How")); // boolean, true or false
        System.out.println("---------------------");
        System.out.println(s1.endsWith("you")); // boolean, true or false
        System.out.println("---------------------");
        s = s1.substring(4); // extract the String, starts with the index of 4 to the end
        System.out.println(s);
        System.out.println("---------------------");
        s = s1.substring(4, 7); //extract the String: index -> [4, 7), 7 is not included
        System.out.println(s);
        System.out.println("---------------------");
        s = s1.toLowerCase();
        System.out.println(s);
        System.out.println("---------------------");
        s = s1.toUpperCase();
        System.out.println(s);
        System.out.println("---------------------");
        String s2 = "  How old are you!! ";
        s = s2.trim(); // clear the spaces before the Head and after the Tail in the String, spaces that in the String can't be removed
        System.out.println(s);
        System.out.println("---------------------");
        System.out.println(s2); // Because String is unchangeable, s2 remains as original
        System.out.println("---------------------");
        String s3 = "core Java";
        String s4 = "Core Java";
        System.out.println(s3.charAt(3)); // get the char at index 3 -> e
        System.out.println("---------------------");
        System.out.println(s2.length()); 
        System.out.println("---------------------");
        System.out.println(s3.equals(s4));
        System.out.println("---------------------");
        System.out.println(s3.equalsIgnoreCase(s4)); // no matter Lower or Upper
        System.out.println("---------------------");
        // Starts with index 5, return 5
        System.out.println(s3.indexOf("Java")); // whether this String contains "Java¡± and return the index
        System.out.println("---------------------");
        System.out.println(s3.indexOf("apple")); // if this String does not contain "apple¡®, return -1
        System.out.println("---------------------");
        String s5 = s4.replace(' ', '&'); // replace all the space in the String with "&"
        System.out.println("result is :" + s5);
	}
}
