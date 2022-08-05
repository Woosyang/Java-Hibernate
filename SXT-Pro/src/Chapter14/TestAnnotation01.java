package Chapter14;
import java.util.*;

/**
 * Understand The Usage Of The Annotation
 * Built-In Annotation: @Override, @Deprecated, @SuppressWarnings
 * @author Woo
 *
 */
public class TestAnnotation01 {
	
	@Override
	public String toString() {
		return null;
	}
	
	// annotation can be read and compiled by other program
	
	/*
	@Override -> won't work, only works for toString()
	public String ToString() {
		return null;
	}
	*/
	
	// the example of @Deprecated
	@Deprecated
	public static void Play() {}
	
	// @SuppressWarnings("all") -> clear all the warnings, needs to be used with parameters
	// such as deprecation (Old JDK), unchecked (Generics), fallthrough (Case), path (File)
	// serial (serialVersionUID), finally (can't be executed), all (all warnings)
	@SuppressWarnings(value = {"all", "unchecked"}) // use multiple parameters need add the "value = {}"
	public void Game() {
		List L01 = new ArrayList<>();
		List L02 = new ArrayList<>();
	}
	
	// only applys to method because of "@Target(value=ElementType.METHOD)" in MyAnnotation
	// but you add more values to modify the scope of the usage of this annotation
	// eg: @Target(value= {ElementType.METHOD, ElementType.TYPE})
	@MyAnnotation02
	public void Method01() {}
	
	// can assign the value you want to the parameters of the annotation
	@MyAnnotation02(StudentName = "Una", Age = 24, ID = 101, Schools = {"Amazon", "Google", "Apple"})
	public void Method02() {}
	
	@MyAnnotation01("a") // @MyAnnotation01(value="a"), "value=" can be omited when it got only one parameter
	public void Method03() {}
	@SuppressWarnings("all") // use one parameter
	public static void main(String[] args) {
		Date D = new Date();
		D.parse("DD"); // @Deprecated, it is in the old JDK, not recommend
		Play(); // can be used, but there is a "-" covering the method name
	}
}
