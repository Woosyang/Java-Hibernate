package Chapter15.Builder;

/**
 * Director Interface Used By Client
 * Used With AirCraftBuilder
 * Implemented By SxtAirCraftDirector
 * @author Woo
 *
 */
public interface AirCraftDirector {
	// construct the aircraft object
	AirCraft directAirCraft();
}
