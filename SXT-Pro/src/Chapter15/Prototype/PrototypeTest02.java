package Chapter15.Prototype;
import java.io.*;
import java.util.*;

/**
 * Test The Prototype
 * Use Serialization And De-Serialization To Fulfill The Deep Copy
 * Used With DogPrototype01, let the DogPrototype01 implement the Serializable 
 * @author Woo
 *
 */
public class PrototypeTest02 {
	public static void main(String[] args) throws Exception {
		Date Date = new Date(933425L);
		DogPrototype01 Dg01 = new DogPrototype01("Snop", Date);
		System.out.println(Dg01);
		System.out.println(Dg01.getName());
		System.out.println("Dg01: " +Dg01.getBirthDay());
		
		// use serialize and de-serialize to make the deep copy
		// write the Dg01 to a byte[]
		ByteArrayOutputStream Bos = new ByteArrayOutputStream();
		ObjectOutputStream Oos = new ObjectOutputStream(Bos);
		Oos.writeObject(Dg01);
		byte[] Data = Bos.toByteArray();
		// change the date
		Date.setTime(690314L);
		System.out.println("Dg01: " +Dg01.getBirthDay());
		ByteArrayInputStream Bis = new ByteArrayInputStream(Data);
		ObjectInputStream Ois = new ObjectInputStream(Bis);
		DogPrototype01 Dg02 = (DogPrototype01) Ois.readObject();
		System.out.println("Dg02: " + Dg02.getBirthDay()); // same as the original time
	}
}
