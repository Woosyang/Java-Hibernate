package Chapter06;

import java.io.*;

/**
 * Testing the Try-Catch
 * @author Woo
 *
 */

public class TestException02 {
	public static void main (String[] args) {
		FileReader reader = null;
		try {
	
			reader = new FileReader("a.txt"); // try "b.txt"
			System.out.println("step1"); // when first line has error, the rest of the codes in try will be skipped
			char c = (char)reader.read();
			System.out.println(c);
			
		} catch (FileNotFoundException a) { // the Exception of Child Class must be over the Father Class's 
			System.out.println("step2"); // if the exception is matched, then print
			a.printStackTrace();
	
		} catch (IOException e) {
			System.out.println("step3");
			e.printStackTrace();
		}
		// or just do this :
		/*
		 * catch (Exception e) {
		 *  e.printStackTrace();
		 * }
		 */
		
		finally {
			System.out.println("Step4"); // last block to be executed
			try {
				
				if (reader != null) {
				
					reader.close();
				
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			  
			}
		}
  }
}
