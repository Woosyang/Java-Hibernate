package Chapter15.Observer;

/**
 * Test The Observer Pattern
 * @author Woo
 *
 */
/*
 * 
 * Observer pattern is used when there is one-to-many relationship between objects such
 * as if one object is modified, its dependent objects are to be notified automatically.
 * Observer pattern falls under behavioral pattern category.
 * Observer pattern uses three actor classes. Subject, Observer and Client. Subject is 
 * an object having methods to attach and detach observers to a client object. We have 
 * created an abstract class Observer and a concrete class Subject that is extending 
 * class Observer.
 *
 */
public class ObserverTest {
	public static void main(String[] args) {
		// create a targeted object, be careful to the polymorphism
		ConcreteSubject01 Subject01 = new ConcreteSubject01();
		
		// create a set of observers, be careful to the polymorphism
		ConcreteObserver01 A = new ConcreteObserver01();
		ConcreteObserver01 B = new ConcreteObserver01();
		ConcreteObserver01 C = new ConcreteObserver01();
		
		// put those observers in to the collection of the Subject
		Subject01.registerObserver(A);
		Subject01.registerObserver(B);
		Subject01.registerObserver(C);
		
		// at first, all the state is 0 without initialization
		System.out.println("The state of the observer is also changed");
		Subject01.setState(300);
		System.out.println(A.getObState()); // 300 
		System.out.println(B.getObState());
		System.out.println(C.getObState());
		
		System.out.println("Test With The Java.util.Observer and Java.util.Observable: ");
		// create a subject object
		ConcreteSubject02 Subject02 = new ConcreteSubject02();
		
		// create a set of observers
		ConcreteObserver02 a = new ConcreteObserver02();
		ConcreteObserver02 b = new ConcreteObserver02();
		ConcreteObserver02 c = new ConcreteObserver02();
		
		// let the subject have those observers
		Subject02.addObserver(a);
		Subject02.addObserver(b);
		Subject02.addObserver(c);
		
		// change the state of the Subject02
		Subject02.Set(1000);
		
		// the state of those observers has been changed 
		System.out.println("The State Has Been Changed: ");
		System.out.println(a.getObState()); // 1000
		System.out.println(b.getObState());
		System.out.println(c.getObState());
		System.out.println();
	}
}
