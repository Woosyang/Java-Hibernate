package Chapter15.Bridge;

/**
 * Test The Example Without The Bridge
 * Imagine a scenario a store selling the computer with different types and brands.
 * Suppose the store has Pad, Laptop, Desktop(all of them are the child of the Computer)
 * and each type has different brands like(Apple, Dell, Samsung), to create those class
 * (like Apple Ipad, Dell Laptop, Samsung Desktop) will be very complicated, you have to 
 * create the Desktop, Laptop and Pad classes and let them implement the Computer Interface
 * then create the Apple Pad class extending the Ipad class(the Dell Laptop and Samsung 
 * Desktop will have the same process). It is very inefficient.  							
 * @author Woo
 *
 */
// Computer has 3 children(Desktop, Laptop, Pad)
// if you want to create another type of the computer with another brand (SonyPhone)
// you have to write the code like the code below, Phone implements Computer then
// SonyPhone extends the Phone -> Extension Problem
// Single responsibility can be interpreted as function/class should have only one reason 
// to change. The Sony(Brand)Phone(Type) violates it. Brand and Type are 2 reasons in
// this case.
public interface Computer01 {
	public void declarePrice();
}

// Desktop
class Desktop01 implements Computer01 {
	@Override
	public void declarePrice() {
		System.out.println("It is a Desktop"); 
	}
}

// Laptop
class Laptop01 implements Computer01 {
	@Override
	public void declarePrice() {
		System.out.println("It is a Laptop"); 
	}
}

// Pad
class Pad01 implements Computer01 {
	@Override
	public void declarePrice() {
		System.out.println("It is a Pad"); 
	}
}

// Desktop got 3 brands: Apple, Dell, Samsung
class AppleDesktop extends Desktop01 {
	@Override
	public void declarePrice() {
		System.out.println("The brand is Apple and price is 900$");
	}
}

class DellDesktop extends Desktop01 {
	@Override
	public void declarePrice() {
		System.out.println("The brand is Dell and price is 800$");
	}
}

class SamsungDesktop extends Desktop01 {
	@Override
	public void declarePrice() {
		System.out.println("The brand is Samsung and price is 1000$");
	}
}

// Laptop got 3 brands: Apple, Dell, Samsung
class AppleLaptop extends Laptop01 {
	@Override
	public void declarePrice() {
		System.out.println("The brand is Apple and price is 700$");
	}
}

class DellLaptop extends Laptop01 {
	@Override
	public void declarePrice() {
		System.out.println("The brand is Dell and price is 600$");
	}
}
class SamsungLaptop extends Laptop01 {
	@Override
	public void declarePrice() {
		System.out.println("The brand is Samsung and price is 500$");
	}
}

// Pad has three 3 brands: Apple, Dell, Samsung
class ApplePad extends Pad01 {
	@Override
	public void declarePrice() {
		System.out.println("The brand is Apple and price is 600$");
	}
}

class SamsungPad extends Pad01 {
	@Override
	public void declarePrice() {
		System.out.println("The brand is Samsung and price is 600$");
	}
}

class DellPad extends Pad01 {
	@Override
	public void declarePrice() {
		System.out.println("The brand is Dell and price is 600$");
	}
}
