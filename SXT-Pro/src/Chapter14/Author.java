package Chapter14;
import java.lang.annotation.*;

/**
 * Customize The Annotation With Parameters
 * Used With TestJavaAssist02.java, Employee.java
 * @author Woo
 *
 */
@Target(value= {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
	String Name();
	int Year();
}
