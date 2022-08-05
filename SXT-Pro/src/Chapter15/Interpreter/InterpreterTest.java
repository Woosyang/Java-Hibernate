package Chapter15.Interpreter;

/**
 * Test The Interpreter Pattern
 * Used with Expression
 * Used to describe how to construct a simple language interpreter, mainly used for 
 * the design of compilers and the development of interpreters using object-oriented 
 * languages.
 * @author Woo
 *
 */
/*
 * 
 * Interpreter pattern provides a way to evaluate language grammar or expression. This 
 * type of pattern comes under behavioral pattern. This pattern involves implementing 
 * an expression interface which tells to interpret a particular context. This pattern 
 * is used in SQL parsing, symbol processing engine etc.
 * 
 */
public class InterpreterTest {
	// Rule: Robert and John are male
	public static Expression getMaleExpression() {
		Expression Robert = new TerminalExpression("Robert");
	    Expression John = new TerminalExpression("John");
	    return new OrExpression(Robert, John);		
	}

	// Rule: Julie is a married women
	public static Expression getMarriedWomanExpression() {
		Expression Julie = new TerminalExpression("Julie");
		Expression Married = new TerminalExpression("Married");
		return new AndExpression(Julie, Married);		
	}

	public static void main(String[] args) {
		Expression isMale = getMaleExpression();
		Expression isMarriedWoman = getMarriedWomanExpression();
		System.out.println("John is male? " + isMale.Interpret("John")); // true
		// true
	    System.out.println("Julie is a married women? " + isMarriedWoman.Interpret("Married Julie"));
	}
}
