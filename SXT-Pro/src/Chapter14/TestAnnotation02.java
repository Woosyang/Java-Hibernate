package Chapter14;
import java.lang.annotation.*;
import java.lang.reflect.*;

/**
 * Use Reflection To Read The Info Of The Annotation
 * Simulate The Progress Of Processing The Info Of The Annotation
 * Used With Student, Table, Field
 * @author Woo
 *
 */
public class TestAnnotation02 {
	public static void main(String[] args) {
		try {
			// right now Clazz got all the info of the Student.java
			Class Clazz = Class.forName("Chapter14.Student");
			// get all the annotations applying to the class
			Annotation[] Ant = Clazz.getAnnotations(); // get all the annotations
			for (Annotation A : Ant) {
				System.out.println(A); // print out all of the annotations
			}
			
			// get the specific field of the annotation applying to the class, eg value() -> Tb_Student
			MyTable Mt = (MyTable)Clazz.getAnnotation(MyTable.class);
			System.out.println(Mt.value()); // Annotation Table only got one field
			
			// get the specific annotation of the field from the class, eg SName
			Field F = Clazz.getDeclaredField("SName"); // ID, Age
			MyField Mf = F.getAnnotation(MyField.class); // reflection
			System.out.println("ColName: " + Mf.ColName() + " --- " + "Type: " + Mf.Type() + " --- " + "Len: " + Mf.Length());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
