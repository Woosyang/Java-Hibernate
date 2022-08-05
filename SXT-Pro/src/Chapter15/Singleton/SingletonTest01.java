package Chapter15.Singleton;
import java.io.*;
import java.lang.reflect.*;

/**
 * Used With SingletonEager
 * Used With SingletonEnumerate
 * Used With SingletonLazy02
 * Used With SingletonStaticInnerClass
 * @author Woo
 *
 */
public class SingletonTest01 {
	public static void main(String[] args) throws Exception {
		// use static inner class
		SingletonStaticInnerClass S01 = SingletonStaticInnerClass.getInstance();
		SingletonStaticInnerClass S02 = SingletonStaticInnerClass.getInstance();
		// only create the instance one time
		System.out.println(S01 == S02); // true
		
		// use enumerate
		SingletonEnumerate S03 = SingletonEnumerate.Instance;
		SingletonEnumerate S04 = SingletonEnumerate.Instance;
		System.out.println(S03 == S04); // true
		
		// use lazy mode
		SingletonLazy02 S05 = SingletonLazy02.getInstance();
		SingletonLazy02 S06 = SingletonLazy02.getInstance();
		System.out.println(S05 == S06); // true
		
		
		// use reflection to break the lazy mode singleton (create multiple objects)
		// has to be commented out, otherwise the de-serialization part will not work
		/*
		Class<SingletonLazy02> Clazz = (Class<SingletonLazy02>) Class.forName("Chapter15.SingletonLazy02"); 
		// use getDeclaredConstructor + setAccessible(true) to break the singleton
		Constructor<SingletonLazy02> Con = Clazz.getDeclaredConstructor(null); 
		Con.setAccessible(true); 
		SingletonLazy02 S07 = Con.newInstance();
		// SingletonLazy02 S08 = Con.newInstance();
		// "S07 == S08" is false, not the same instance 
		// add 
		 	// if (Instance != null) {
			//		throw new RuntimeException();
			//	}
		// to prevent singleton from reflection, must create the SingletonLazy instance first
		System.out.println(S05 == S07); // exception happen
		*/
		
		// use de-serialization to break the lazy mode singleton (create multiple objects)
		// pre-requirement: SingletonLazy02 must implements Serializable Interface
		FileOutputStream Fos = new FileOutputStream("Text/A/a.txt");
		ObjectOutputStream Oos = new ObjectOutputStream(Fos);
		Oos.writeObject(S05); // write the data to a file
		Oos.close();
		Fos.close();
		ObjectInputStream Ois = new ObjectInputStream(new FileInputStream("Text/A/a.txt"));
		Object S08 = Ois.readObject();
		if (S08 instanceof SingletonLazy02) {
			S08 = (SingletonLazy02) S08;
		}
		Ois.close();
		// false, if there no readResovle() inside SingletonLazy02
		// add
		// private Object readResolve() throws ObjectStreamException { 
		// 		return Instance;
		// }
		// to avoid de-serialization
		System.out.println(S08 == S05); // false -> true 
	}
}
