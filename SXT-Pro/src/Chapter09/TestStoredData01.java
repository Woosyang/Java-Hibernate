package Chapter09;
import java.util.*;
/**
 * Use the Collection to store the data of form
 * ORM -> Object Relational Mapping
 * @author Woo
 *
 */
/*
Eg.Form:
ID		Gender	Name	Age		Salary		Enrolling Date
1024	Male	James	21		4000		2015.8.10
2048	Male	Keegan	21		5000		2015.8.10
0256	Male	Ted		26		10000		2018.8.10

Each column stored by a Map
Each row stored by a List
*/

public class TestStoredData01 {
	public static void main(String[] args) {
		// each map can store multiple key - value pairs
		Map<String, Object> Row1 = new HashMap<>();
		Row1.put("ID", 1024);
		Row1.put("Gender", "Male");
		Row1.put("Name", "James");
		Row1.put("Age", 21);
		Row1.put("Salary", 4000);
		Row1.put("Enrolling Date", "2015.8.10");
		
		Map<String, Object> Row2 = new HashMap<>();
		Row2.put("ID", 2048);
		Row2.put("Gender", "Male");
		Row2.put("Name", "Keegan");
		Row2.put("Age", 21);
		Row2.put("Salary", 5000);
		Row2.put("Enrolling Date", "2015.8.10");
		
		Map<String, Object> Row3 = new HashMap<>();
		Row3.put("ID", 2560);
		Row3.put("Gender", "Male");
		Row3.put("Name", "Ted");
		Row3.put("Age", 26);
		Row3.put("Salary", 10000);
		Row3.put("Enrolling Date", "2018.8.10");
		
		// do not forget to add <T, E> of the Map
		List<Map<String, Object>> L = new ArrayList<>();
		L.add(Row1);
		L.add(Row2);
		L.add(Row3);
		// System.out.print("ID" + "\t" + "Gender" + "\t" + "Name" + "\t" + "Age" + "\t" + "Salary" + "\t" + "Enrolling Date");
		// System.out.println();
		// traverse each map in the ArrayList
		for (Map<String, Object> m : L) {
			// get each map in the List
			Set<String> Key = m.keySet();
			// traverse each key inside Set
			for (String key : Key) {
				System.out.print(key + ": " + m.get(key) + "\t");
			}
			/*
			 or you can do:
			 for (Iterator<String> a = Key.iterator(); a.hasNext();) {
			 		String b = a.next();
			 		System.out.print(b + ":" + m.get(b) + "\t");
			 	}
			*/
			// outcome will be disordered
			System.out.println();
		}
	}
}
