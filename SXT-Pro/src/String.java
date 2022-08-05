/**
 * Test The Delegation Principles Of The Class Loader
 * Used With TestClassLoader01.java
 * @author Woo
 *
 */
// this class will never be loaded by the class loader, because the name(package name + class name)
// of this class has already been used to define a class in bootstrap
// delegation principle
public class String {
	public java.lang.String toString() {
		return "A";
	}
}
