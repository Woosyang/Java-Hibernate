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
public class CarSimpleFactory01 {
	public static Car createCar(String Brand) {
		if ("Audi".equals(Brand)) {
			return new CarAudi();
		} else if ("Benz".equals(Brand)) {
			return new CarBenz();
		} else {
			// if you want to create other brand's car
			// you have to create a other brand's car's class
			// and modify the code here 
			// but it does not follow the OCP principle (Open-Closed)
			return null;
		}
	}
}
