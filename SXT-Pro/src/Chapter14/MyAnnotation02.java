package Chapter14;
import java.lang.annotation.*;

/**
 * Customize The Annotation with Meta Annotations( @Target, @Retention, @Documented, @Inherited )
 * @author Woo
 *
 */
@Target(value= {ElementType.METHOD, ElementType.TYPE})
// @Target -> describe the scope of the annotation's usage

@Retention(RetentionPolicy.RUNTIME)
// @Retentiion -> describe the life cycle of the annotation
// SOURCE: only valid in Src, can be read by using reflection
// CLASS: only valid in Class
// RUNTIME: only valid in running the program, use RUNTIME then this annotation is valid for both SOURCE and CLASS
public @interface MyAnnotation02 {
	// type  parameter name
	String StudentName() default ""; // usually define the string with "" as default
	
	// usually define the integer with 0 or -1
	int Age() default 0; 
	int ID() default  -1;
	
	// usually define the array with "{}" 
	String[] Schools() default {"NYU", "SIT", "MIT"};
}
