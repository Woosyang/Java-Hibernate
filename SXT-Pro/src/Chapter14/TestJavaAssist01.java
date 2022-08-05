package Chapter14;
import javassist.*;

/**
 * Use Javaassit To Create A New Class
 * @author Woo
 *
 */
public class TestJavaAssist01 {
	public static void main(String[] args) throws Exception {
		ClassPool Pool = ClassPool.getDefault();
		// C is the class going to be created
		CtClass C = Pool.makeClass("Chapter14.Employee");
		
		// create the field
		CtField F1 = CtField.make("private int EmpNo;", C);
		CtField F2 = CtField.make("private String Name;", C);
		C.addField(F1);
		C.addField(F2);
		
		// create the method
		CtMethod M1 = CtMethod.make("public int GetEmpNo() {return EmpNo;}", C);
		CtMethod M2 = CtMethod.make("public void SetEmpNo(int EmpNo) { this.EmpNo = EmpNo;}", C);
		C.addMethod(M1);
		C.addMethod(M2);
		
		// create the constructor                              int EmpNo          String Name
		CtConstructor Con = new CtConstructor(new CtClass[] {CtClass.intType, Pool.get("java.lang.String")}, C);
		Con.setBody("{this.EmpNo = EmpNo; this.Name = Name; System.out.println();}"); // content of the constructor
		C.addConstructor(Con);
		C.writeFile("Text"); // write the class file to the Text folder, use JD-GUI to take a look
		System.out.println("Create Successfully.");
	}
}
