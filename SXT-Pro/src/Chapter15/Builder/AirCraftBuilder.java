package Chapter15.Builder;

/**
 * Builder Pattern
 * Implemented By SxtAirCraftBuilder
 * called by AirCraftDirector
 * @author Woo
 *
 */
public interface AirCraftBuilder {
	Engine buildEngine();
	OrbitalModule buildOrbitalModule();
	EscapeTower buildEscapeTower();
}
