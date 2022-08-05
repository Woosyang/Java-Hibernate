package Chapter02;

/**
 * Testing the conditional operator
 * @author Woo
 *
 */

public class TestOperator06 {
	public static void main(String[] args) {
		int score = 80;
		int x = -100;
		String type = score > 60? "pass" : "fail";
		System.out.println(type);
		
		if (score <= 60){
			System.out.println("fail");
		}else{
			System.out.println("pass");
		}
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
		System.out.println(x > 0 ? 1 : (x == 0 ? 0 : -1));
	}
}
