package Chapter15.Builder;

/**
 * Build An AirCraft Using Builder Pattern
 * @author Woo
 *
 */
public class AirCraft {
	private OrbitalModule OrbitalModule;
	private Engine Engine;
	private EscapeTower EscapeTower;
	public OrbitalModule getOrbitalModule() {
		return OrbitalModule;
	}
	public void setOrbitalModule(OrbitalModule orbitalModule) {
		OrbitalModule = orbitalModule;
	}
	public Engine getEngine() {
		return Engine;
	}
	public void setEngine(Engine engine) {
		Engine = engine;
	}
	public EscapeTower getEscapeTower() {
		return EscapeTower;
	}
	public void setEscapeTower(EscapeTower escapeTower) {
		EscapeTower = escapeTower;
	}
	
	// can add any method you want, the vital part is the code above
	public void Lanuch() throws Exception {
		System.out.println("Building Completed..");
		int Time = 5;
		while (true) {
			Thread.sleep(1000);
			System.out.println(Time + "....");
			Time --;
			if (Time <= 0) {
				break;
			}
		}
		System.out.println("Fly Fly Fly");
	}
}

// component of the aircraft
class OrbitalModule {
	private String Name;
	
	public OrbitalModule(String Name) {
		this.Name = Name;
	}
	
	public String getName() {
		return Name;
	}
}

// component of the aircraft
class Engine {
	private String Name;
	
	public Engine(String Name) {
		this.Name = Name;
	}

	public String getName() {
		return Name;
	}
}

// component of the aircraft
class EscapeTower {
	private String Name;
	
	public EscapeTower(String Name) {
		this.Name = Name;
	}
	
	public String getName() {
		return Name;
	}
}