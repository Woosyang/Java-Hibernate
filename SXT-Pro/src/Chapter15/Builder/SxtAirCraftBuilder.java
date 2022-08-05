package Chapter15.Builder;

/**
 * Builder Pattern Is Used In The StringBuilder, PreparedStatement Of SQL,
 * DomBuilder And SaxBuilder In JDOM
 * 
 * Used With The Component Fields(Class) Of The AirCraft-
 * Engine, OrbitalModule, EscapeTower
 * Called by SxtAirCraftDirector
 * @author Woo
 *
 */
public class SxtAirCraftBuilder implements AirCraftBuilder {

	@Override
	public Engine buildEngine() {
		System.out.println("Building the Sxt Engine");
		return new Engine("Sxt Engine");
	}

	@Override
	public OrbitalModule buildOrbitalModule() {
		System.out.println("Building the Sxt OrbitalModule");
		return new OrbitalModule("Sxt OrbitalModule");
	}

	@Override
	public EscapeTower buildEscapeTower() {
		System.out.println("Building the Sxt EscapeTower");
		return new EscapeTower("Sxt EscapeTower");
	}
}
