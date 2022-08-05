package Chapter14;
import java.lang.annotation.*;

/**
 * Customize The Annotation With Only One Parameter
 * @author Woo
 *
 */
@Target(value= {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation01 {
	// usually define the parameter with the name "value" when this annotation only has one parameter inside
	String value(); 
}
