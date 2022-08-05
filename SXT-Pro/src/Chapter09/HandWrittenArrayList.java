package Chapter09;

/**
 * Create my own ArrayList to know how ArrayList work
 * @author Woo
 *
 */

public class HandWrittenArrayList {
	private Object[] ElementData;
	private int Index = 0;
	private static final int Default_Capacity =  10;
	
	public HandWrittenArrayList() {
		ElementData = new Object[Default_Capacity]; // original capacity
	}
	
	public HandWrittenArrayList(int Capacity) {
		ElementData = new Object[Capacity];
	}
	
	public void Add(Object O) {
		ElementData[Index ++] = O;
	}
	
	public String toString() {
		// return Arrays.toString(ElementData); good
		// or you can use the StringBuilder to traverse all of the elements in
		// the array
		StringBuilder Sb = new StringBuilder();
		Sb.append("[");
		/*
		for (Object O : ElementData) {
			Sb.append(O + ", ");
		}
		*/
		for (int i = 0; i < Index; i ++) {
			Sb.append(ElementData[i] + ", ");
		}
		// Sb.append("]"); because there is a "," use set
		Sb.setCharAt(Sb.length() - 2, ']'); // Sb.length() - 1 -->  " ", so Sb.length() - 2 --> ","
		return Sb.toString();
	}
	
	public static void main(String[] args) {
		HandWrittenArrayList A = new HandWrittenArrayList(15);
		A.Add(1);
		A.Add(2);
		A.Add(3);
		System.out.println(A);
	}	
}
