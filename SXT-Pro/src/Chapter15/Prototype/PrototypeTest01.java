package Chapter15.Prototype;
import java.util.*;

/**
 * Test The Prototype Pattern
 * Spring Bean use Prototype
 * Can Used With Factory
 * @author Woo
 *
 */
/*
 * Prototype pattern refers to creating duplicate object while keeping performance 
 * in mind. This type of design pattern comes under creational pattern as this pattern 
 * provides one of the best ways to create an object.
 * This pattern involves implementing a prototype interface which tells to create a 
 * clone of the current object. This pattern is used when creation of object directly 
 * is costly.
 *
 */
// when you create an object that needs you to have very tedious data preparation or 
// access permission, prototype is the choice, increase the efficiency
// clone is like "new" but different from "new", "new" create the object whose fields'
// value are default, clone create the object whose fields' value are the same as the
// prototype, the cloned one does not make any change to the original one. U can modify
// the field of the cloned without changing the prototype.

public class PrototypeTest01 {
	public static void main(String[] args) throws Exception {
		System.out.println("Shallow Copy Prototype With DogPrototype01: ");
		Date Date = new Date(43345145L);
		DogPrototype01 Dg01 = new DogPrototype01("Snop", Date);
		System.out.println(Dg01.getName());
		System.out.println(Dg01.getBirthDay());
		
		// use clone() to copy the Dg01 (Shallow Copy)
		// Object Dg01, Dg02 both have the same reference of the field of Date
		// if the Date of Dg01 is changed after the clone() finish, the Date of the 
		// Dg02 will be same as the recent Dg01's
		
		// the new object will have all the same information that copied(old) one 
		// will have (if the old one has not been modified, then they got the same
		// value to their fields)
		DogPrototype01 Dg02 = (DogPrototype01) Dg01.clone();
		Date.setTime(68355325L); // change the date 
		System.out.println(Dg01.getBirthDay()); // changed the date of the Dg01
		
		System.out.println(Dg02.getName()); // same as the Dg01 if Name does not change
		System.out.println(Dg02.getBirthDay()); // same as the Dg01 if Date does not change
		// but you can change the value of the Dg02 by using set()/get()
		Dg02.setName("Snap");
		// Dg02.setBirthDay(new Date(2500));
		System.out.println(Dg02.getName()); // changed
								
							/*Shallow Copy*/
		// the Date of Dg02 will be same as the recent Date from Dg01
		// if the Date of Dg01 is changed, the Date of Dg02 will be the same 
		// to the recent Date
		System.out.println(Dg02.getBirthDay()); // changed if use Dg02.setBirthDay()
		
		System.out.println("-------------------------");
							/*Deep Copy*/
		// if the Date of Dg01 is changed, the Date of Dg02 will not be modified 
		// correspondingly because the Dg01 and Dg02 have different reference of the Date
		System.out.println("Deep Copy Prototype With DogPrototype02: ");
		// if you want the Deep Copy for this object, you need to use clone() for
		// each field of the "DogPrototype" object but not for basic data type field(String, int...)
		Date = new Date(89320553L);
		DogPrototype02 Dg03 = new DogPrototype02("Goffy", Date);
		DogPrototype02 Dg04 = (DogPrototype02) Dg03.clone();
		System.out.println(Dg03.getBirthDay());
		System.out.println("Change The Date: ");
		Date.setTime(980302L);
		System.out.println("Dg03: " + Dg03.getBirthDay());
		System.out.println("Dg04: " + Dg04.getBirthDay()); // same as the original one
	}
}
