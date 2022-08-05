package Chapter14;
import java.lang.annotation.*;

/**
 * Customize The Annotation For The Field Of The Student
 * @author Woo
 *
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyField { // be careful to the naming conflict(Field from lang.reflect)
	// each field got 3 descriptions
	// eg: 'ID' int(10), 10 -> length
	String ColName();
	String Type();
	int Length();
}
