package Chapter14;
import java.lang.annotation.*;

/**
 * Customize The Annotation For The Method Of The Student
 * @author Woo
 *
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyMethod {
	String value();
}
