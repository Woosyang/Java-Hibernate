package Chapter15.Builder;

/**
 * Used With SxtAirCraftBuilder
 * Needs To Have The AirCraftBuilder As The Field
 * @author Woo
 *
 */
public class SxtAirCraftDirector implements AirCraftDirector {
	// need the AirCraftBuilder(interface) as a field
	private AirCraftBuilder Builder;
	
	public SxtAirCraftDirector(AirCraftBuilder builder) {
		Builder = builder;
	}

	@Override
	// Builder needs to be instantiated in the Client Part
	public AirCraft directAirCraft() {
		// gather the components of the aircraft
		// the method right now is still an abstract method
		Engine E = Builder.buildEngine(); 
		OrbitalModule Om = Builder.buildOrbitalModule();
		EscapeTower Et = Builder.buildEscapeTower();
		
		// put the components together -> construct the aircraft
		// the method right now is still an abstract method
		AirCraft Craft = new AirCraft();
		Craft.setEngine(E);
		Craft.setOrbitalModule(Om);
		Craft.setEscapeTower(Et);
		
		return Craft;
	}

}
