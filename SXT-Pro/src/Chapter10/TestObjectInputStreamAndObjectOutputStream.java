package Chapter10;
import java.io.*;
import java.util.*;

/**
 * similar with DataInputStream and DataOutputStream
 * 1. write in first then read out
 * 2. the order of reading out should be same as the order of writing in
 * 3. not all of the object can be serialized, needs the Serializable
 * @author Woo
 *
 */
public class TestObjectInputStreamAndObjectOutputStream {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// node stream: ByteArrayInputStream & ByteArrayOutputStream
		// write ---> Serializable
		byte Data[] = null;
		ByteArrayOutputStream Bos = new ByteArrayOutputStream();
		ObjectOutputStream Oos = new ObjectOutputStream(Bos);
		// same as Dos
		Oos.writeInt(8);
		Oos.writeBoolean(true);
		Oos.writeChar('U');
		// write in the object which has implements the Serializable
		String a = "Hello"; // an object, implements the Serializable
		Oos.writeObject(a);
		Date D = new Date();
		Oos.writeObject(D); // class Date implements the Serializable
		Employee Emp = new Employee("Joe", 4000);
		Oos.writeObject(Emp);
		Oos.flush();
		Data = Bos.toByteArray();
		// read ---> Deserializable
		ByteArrayInputStream Bis = new ByteArrayInputStream(Data);
		ObjectInputStream Ois = new ObjectInputStream(Bis);
		// has to read out all of data in order
		int A = Ois.readInt();
		boolean B = Ois.readBoolean();
		char C = Ois.readChar();
		// restore the data of the object
		Object Stra = Ois.readObject();
		Object Date = Ois.readObject();
		Object Em = Ois.readObject(); 
		System.out.println(A + ", " + B + ", " + C);
		// System.out.println(Ois.readInt() + ", " + Ois.readBoolean() + ", " + Ois.readChar());
		// wrong way to type, if there are still data remains
		if (Stra instanceof String) {
			System.out.println(Stra);
		}
		if (Date instanceof Date) {
			System.out.println(Date);
		}
		if (Em instanceof Employee) {
			Employee E = (Employee) Em; // cast first
			System.out.println(E.GetName() + "--------->" +  E.GetSalary());
		}
		Oos.close();
		Ois.close();
		// use FileInputStream and FileOutputStream
		OutputStream Os = new FileOutputStream("Obj.ser"); // the suffix can be set as you want if you can restore it
		// increase the efficiency
		BufferedOutputStream BO = new BufferedOutputStream(Os);
		Oos = new ObjectOutputStream(BO);
		Oos.writeInt(10);
		Oos.writeBoolean(false);
		Oos.writeChar('I'); 
		a = "Thank"; 
		Oos.writeObject(a);
		D = new Date();
		Oos.writeObject(D); 
		Emp = new Employee("Dana", 8000);
		Oos.writeObject(Emp);
		Oos.flush();
		InputStream Is = new FileInputStream("Obj.ser"); // for PC to read
		BufferedInputStream BI = new BufferedInputStream(Is);
		Ois = new ObjectInputStream(BI);
		A = Ois.readInt();
		B = Ois.readBoolean();
		C = Ois.readChar();
		Stra = Ois.readObject();
		Date = Ois.readObject();
		Em = Ois.readObject();
		System.out.println(A + ", " + B + ", " + C);
		// System.out.println(Ois.readInt() + ", " + Ois.readBoolean() + ", " + Ois.readChar());
		if (Stra instanceof String) {
			System.out.println(Stra);
		}
		if (Date instanceof Date) {
			System.out.println(Date);
		}
		if (Em instanceof Employee) {
			Employee E = (Employee) Em; // cast first
			System.out.println(E.GetName() + "--------->" +  E.GetSalary());
		}
		Oos.close();
		Ois.close();
		BO.close();
		BI.close();
		Os.close();
		Is.close();
	}
}

@SuppressWarnings("serial")
// needs to implements the Serializable
// otherwise it will cause an error
class Employee implements Serializable { 
	// transient variable can be displayed
	private transient String Name; // this data does not need to be serialized
	private double Salary;
	public Employee(String Name, double Salary) {
		this.Name = Name;
		this.Salary = Salary;
	}
	public Employee() {} // default constructor
	public double GetSalary() {
		return this.Salary;
	}
	public String GetName() {
		return this.Name;
	}
}
