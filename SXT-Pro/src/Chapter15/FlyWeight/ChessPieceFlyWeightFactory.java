package Chapter15.FlyWeight;
import java.util.*;

/**
 * Example Of FlyWeight Pattern
 * @author Woo
 *
 */
// fly weight factory
// create and manage fly weight instance, fly weight pool is designed as key-value pairs
// and this pool is usually a hash map
public class ChessPieceFlyWeightFactory {
	// fly weight pool
	//          color   chess piece(object)
	private static Map<String, ChessPieceFlyWeight> M = new HashMap<>();
	
	// return a chess piece
	public static ChessPieceFlyWeight getChessPiece(String color) {
		if (M.get(color) == null) { 
			ChessPieceFlyWeight chessPiece = new ConcreteChessPiece();
			chessPiece.setColor(color);
			M.put(color, chessPiece);
		}
		return M.get(color); // the chess piece already existed, can be reused
	}
}
