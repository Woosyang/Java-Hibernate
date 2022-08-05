package Chapter03;

/**
 * Testing the recursion
 * @author Woo
 *
 */

public class TestRecursion02 {
	public static void main(String[] args) {
		long d1 = System.currentTimeMillis(); // start
		System.out.printf("the result of the factorial of %d : %s%n", 10, factorial(10)); // c
		long d2 = System.currentTimeMillis(); // finish
		System.out.printf("total time of executing the recursion: %s%n", d2 - d1); // c, unit: ms
		FactorialLoop01(10);
		FactorialLoop02(10);
		Sum(5);
		System.out.println(Fibo01(4));
		Fibo02(4);
	}
	
	/* the method of calculating the factorial */
	static long factorial(int n) {
		if (n == 1) {// head of the recursion
			return 1;
		} else { // body of the recursion
			return n * factorial(n - 1); // n! = n * (n - 1)....
		}
	}
	
	// 1 * 2 * 3 * .... * 10	
	
	static long FactorialLoop01(int n) {
		long d3 = System.currentTimeMillis(); // start
		long result = 1;
		while (n > 1) {
			result *= n * (n - 1); // result = result * n * (n - 1);
			n -= 2; // n = n - 2
		}
		long d4 = System.currentTimeMillis();
		System.out.println(result);
		System.out.printf("total time might be cost: %s%n", d4 - d3);
		return result;
	}
	
	static long FactorialLoop02(int n) {
		long d5 = System.currentTimeMillis(); // start
		long result = 1;
		for (int i = n; i > 1; i --) {
			result = result * i;
		}
		long d6 = System.currentTimeMillis(); // finish
		System.out.println(result);
		System.out.printf("total time might be cost: %s%n", d6 - d5);
		return result;
	}
	
	// 1! + 2! + 3! + ..... + 5!
	static long Sum(int n) {
		long sum = 0;
		for (int i = 1; i <= n; i ++) {
			sum = sum + factorial(i);
		}
		System.out.println(sum);
		return sum;
	}
	
	// fibonacci 1, 1, 2, 3, 5......
	static long Fibo01(int n) {
		if (n <= 2) {
			return 1;
		} else {
			return Fibo01(n - 1) + Fibo01(n - 2);
		}
	}
	
	static void Fibo02(int n) {
		if (n <= 2) {
			System.out.println(1);
		} else {
			int a = 1;
			int b = 1;
			int c = 0;
			for (int i = 3; i <= n; i ++) {
			 c = a + b;
			 a = b;
			 b = c;
			}
		System.out.println(c);
		}
	}
}
