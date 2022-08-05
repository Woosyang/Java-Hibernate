package Chapter15.SimpleFactory;

/**
 * Factory pattern comes under creational pattern as this pattern provides one of 
 * the best ways to create an object. In Factory pattern, we create object without 
 * exposing the creation logic to the client and refer to newly created object 
 * using a common interface.
 * @author Woo
 *
 */
/*
 * basic principles of the OOD(Object-Oriented-Design):
 * 
 * 1.Open-Closed Principle: each software entity should be open for extension, 
 *							but closed for modification.
 * 
 * 2.Single Responsibility Principle: a class should have only a single responsibility.
 * 
 * 3.Liskov Substitution Principle: objects in a program should be replaceable with 
 * 									instances of their subclasses without altering 
 * 									the correctness of that program.
 * 
 * 4.Interface Segregation Principle: clients should not be forced to depend on 
 * 									  interfaces that they do not use.
 * 
 * 5.Dependency Inversion Principle: high level modules should not depend on low level 
 * 									 modules, both should depend on abstraction. Also, 
 * 									 abstraction should not depend on details, details 
 * 									 should depend on abstractions.
 */
public class CarSimpleFactory02 {
	public static Car createCarAudi() {
		return new CarAudi();
	}
	public static Car createCarBenz() {
		return new CarBenz();
	}
	
	// if you want to add more new products
	// you have to create a new class of that
	// product and modify the code here(add a new static method)
	
	// but it violate the OCP principle
}
