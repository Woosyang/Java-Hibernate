package Chapter15.Builder;

/**
 * Test The Builder Pattern
 * Used With AirCraftDirector(called), AirCraft(called), AirCraftBuilder(parameter) 
 * @author Woo
 *
 */
/*
 * Builder Pattern separate the construction and the assembly of the sub object of
 * the product object. This pattern applies to the case that creating an object whose
 * constructing process is very complex.
 * 
 * Due to the decoupling between the construction and assembly, different builder with
 * the same director can create different object and vice versa(different director with
 * the same builder can create different object).
 */
public class BuilderTest {
	public static void main(String[] args) throws Exception {
		// if the builder inside SxtAirCraftDirector is different but the Director is 
		// the same, the product going to be created will be different because the 
		// parameter builder is just an interface
		
		// if the director is different but the parameter builder inside the Director
		// is the same, the product going to be created will also be different because
		// the called object director is just an interface
		
		AirCraftDirector Director = new SxtAirCraftDirector(new SxtAirCraftBuilder());
		// render an aircraft to the client
		AirCraft AirCraft = Director.directAirCraft();
		
		// get the name of the engine of the aircraft
		System.out.println(AirCraft.getEngine().getName()); 
		AirCraft.Lanuch();
	}
}
