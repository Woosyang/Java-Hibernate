package Chapter15.Bridge;

/**
 * Modify The Computer01 Case with Bridge Pattern
 * Separate the brand and the type from the subclass(eg.AppleDesktop)
 * Need to have Brand class as the parameter inside, reference
 * Used with Brand
 * @author Woo
 *
 */
// can add any type you want and it does not affect the class Brand
// and it can combine with any brands(Samsung, Apple, Dell) of the Brand
public abstract class Computer02 {
	protected Brand Brand;
	
	public Computer02(Brand Brand) {
		this.Brand = Brand;
	}
	
	public void declarePrice() {
		Brand.declareBrand();
	}
}

class Desktop02 extends Computer02 {
	// got the specific brand
	public Desktop02(Brand Brand) {
		super(Brand);
	}
	
	@Override
	public void declarePrice() {
		super.declarePrice();
		System.out.println("The price is 1000$");
	}
}

class Pad02 extends Computer02 {
	public Pad02(Brand Brand) {
		super(Brand);
	}
	
	@Override
	public void declarePrice() {
		super.declarePrice();
		System.out.println("The price is 800$");
	}
}

class Laptop02 extends Computer02 {
	public Laptop02(Brand Brand) {
		super(Brand);
	}
	
	@Override
	public void declarePrice() {
		super.declarePrice();
		System.out.println("The price is 900$");
	}
}
