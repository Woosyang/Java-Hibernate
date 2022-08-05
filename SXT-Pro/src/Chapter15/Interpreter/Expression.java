package Chapter15.Interpreter;

/**
 * Example Of Interpreter Pattern
 * Not so popular
 * @author Woo
 *
 */
public interface Expression {
	public boolean Interpret(String context);
}

// concrete class
class TerminalExpression implements Expression {
	private String Data;
	public TerminalExpression(String data) {
		Data = data; 
	}
	@Override
	public boolean Interpret(String context) {
	   if (context.contains(Data)){
	         return true;
	   }
	   return false;
	}
}

class OrExpression implements Expression {
	 private Expression Expr01;
	 private Expression Expr02;
	 public OrExpression(Expression expr01, Expression expr02) { 
		 Expr01 = expr01;
	     Expr02 = expr02;
	 }
	 @Override
	 public boolean Interpret(String context) {		
		 return Expr01.Interpret(context) || Expr02.Interpret(context);
	 }
}

class AndExpression implements Expression {
	 private Expression Expr01;
	 private Expression Expr02;
	 public AndExpression(Expression expr01, Expression expr02) { 
		 Expr01 = expr01;
	     Expr02 = expr02;
	 }
	 @Override
	 public boolean Interpret(String context) {		
		 return Expr01.Interpret(context) && Expr02.Interpret(context);
	 }
}