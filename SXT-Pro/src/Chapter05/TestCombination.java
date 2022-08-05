package Chapter05;

/**
 * Test the Combination
 * @author Woo
 *
 */

public class TestCombination {
	Cpu cpu;
	Memory memory;
	MainBoard mainBoard;
	public void work() {
		cpu.run();
		memory.store();
		mainBoard.connect();
	}
	public static void main(String[] args) {
		TestCombination PC = new TestCombination();
		PC.cpu = new Cpu(); // has to be declared
		PC.mainBoard = new MainBoard(); // has to be declared
		PC.memory = new Memory(); // has to be declared
		PC.work(); // if the member classes are not instantiated, it will be a NullPointer
	}
}

class Cpu {
	public void run() {
		System.out.println("quickly.........");
	}
}
class MainBoard {
	public void connect() {
		System.out.println("connect...........");
	}
}
class Memory {
	public void store() {
		System.out.println("store........");
	}
}