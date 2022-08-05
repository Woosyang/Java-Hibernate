package Chapter14;
import java.lang.annotation.*;

/**
 * Customize The Annotation For The Class Student
 * Class <-> Table
 * @author Woo
 *
 */
@Target(value = ElementType.TYPE) // only applies to class
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTable {
	String value(); // only one parameter
}
