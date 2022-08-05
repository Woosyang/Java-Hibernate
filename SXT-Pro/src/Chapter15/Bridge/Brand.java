package Chapter15.Bridge;

/**
 * Modify The Computer01 Case with Bridge Pattern
 * Separate the brand and the type from the subclass(eg.AppleDesktop)
 * Used with Computer02
 * @author Woo
 *
 */
// can add any brand you want and it does not affect the class Computer02
// and it can combine with any types(Pad, Desktop, Laptop) of the Computer02
public interface Brand {
	public void declareBrand();
}

class Apple implements Brand {
	@Override
	public void declareBrand() {
		System.out.println("The brand is Apple");
	}
}

class Dell implements Brand {
	@Override
	public void declareBrand() {
		System.out.println("The brand is Dell");	
	}
}

class Samsung implements Brand {
	@Override
	public void declareBrand() {
		System.out.println("The brand is Samsung");
	}
}
