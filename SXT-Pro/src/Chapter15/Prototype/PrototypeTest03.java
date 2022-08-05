package Chapter15.Prototype;

/**
 * Compare The Performance Between Prototype And Normal "new" 
 * @author Woo
 *
 */
public class PrototypeTest03 {
	
	public static void testPrototype(int Num) throws Exception {
		Apple A = new Apple();
		long Start = System.currentTimeMillis();
		for (int i = 0; i < Num; i ++) {
			Apple B = (Apple) A.clone();
		}
		long End = System.currentTimeMillis();
		System.out.println("Use \"clone()\" cost " + (End - Start) + "ms");
	}
	
	public static void testNew(int Num) throws Exception {
		long Start = System.currentTimeMillis();
		for (int i = 0; i < Num; i ++) {
			Apple A = new Apple();
		}
		long End = System.currentTimeMillis();
		System.out.println("Use \"new\" cost " + (End - Start) + "ms");
	}
	
	public static void main(String[] args) throws Exception {
		int Num = 100;
		testPrototype(Num);
		testNew(Num);
	}
}

// test example
class Apple implements Cloneable {
	public Apple() throws Exception {
		// simulate the process of creating an object
		Thread.sleep(10); // the time of producing an object
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}