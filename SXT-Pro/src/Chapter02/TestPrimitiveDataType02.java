package Chapter02;
import java.math.*;

/**
 * Testing the float
 * @author Woo
 *
 */

public class TestPrimitiveDataType02 {
	public static void main(String[] args) {
		float a = 3.14F;// double can't be converted to float
		double b = 6.28;
		double c = 628E-2;
		
		System.out.println(c);
		
		// float is not accurate, do not use that to compare with other floating numbers
		float f = 0.1F;
		double d = 1.0/10;
		System.out.println(f == d); // false
		
		float d1 = 423432423f;
		float d2 = d1 + 1;
		if(d1 == d2){
			System.out.println("d1 = d2");
		}
		else{
			System.out.println("d1! = d2");
		}
		
		System.out.println("------------------");
		// If you want the accurate arithmetic operations of float, use the BigDecimal 
		BigDecimal bd = BigDecimal.valueOf(1.0);
		bd = bd.subtract(BigDecimal.valueOf(0.1));
		bd = bd.subtract(BigDecimal.valueOf(0.1));
		bd = bd.subtract(BigDecimal.valueOf(0.1));
		bd = bd.subtract(BigDecimal.valueOf(0.1));
		bd = bd.subtract(BigDecimal.valueOf(0.1));
		
		System.out.println(bd); // 0.5
		System.out.println(1.0 - 0.1 - 0.1 - 0.1 - 0.1 - 0.1); // 0.5000000000000001
		
		BigDecimal bd2 = BigDecimal.valueOf(0.1);
		BigDecimal bd3 = BigDecimal.valueOf(1.0/10.0);
		
		System.out.println(bd2.equals(bd3));
	}
}
