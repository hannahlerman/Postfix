package evaluator.arith;

import language.Operand;
import language.Operator;
import language.arith.DivOperator;
import language.arith.MultOperator;
import language.arith.NegateOperator;
import language.arith.PlusOperator;
import language.arith.SubOperator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions.
 *
 */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack;
	
	/**
	 * Constructs an {@link ArithPostfixEvaluator}
	 */
	public ArithPostfixEvaluator(){
		
		stack = new LinkedStack<Operand<Integer>>();
	
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
		ArithPostfixParser parser = new ArithPostfixParser(expr);
		for (Token<Integer> token : parser) {
			Type type = token.getType();
			switch(type){ 
			case OPERAND:
				stack.push(token.getOperand());
				break;
			case OPERATOR:
				switch(token.getOperator().toString()){
				case "+":
					Operator<Integer> plus = new PlusOperator();
					plus.setOperand(1, stack.pop());
					plus.setOperand(0, stack.pop());
					stack.push(plus.performOperation());
					break;
	
				case "-":
					Operator<Integer> sub = new SubOperator();
					sub.setOperand(1, stack.pop());
					sub.setOperand(0, stack.pop());
					stack.push(sub.performOperation());
					break;
				case "/":
					Operator<Integer> div = new DivOperator();
					div.setOperand(1, stack.pop());
					div.setOperand(0, stack.pop());
					stack.push(div.performOperation());
					break;
				case "*":
					Operator<Integer> mult = new MultOperator();
					mult.setOperand(1, stack.pop());
					mult.setOperand(0, stack.pop());
					stack.push(mult.performOperation());
					break;
				case "!":
					Operator<Integer> neg = new NegateOperator();
					neg.setOperand(0, stack.pop());
					stack.push(neg.performOperation());
				    break;
				
				default:
					throw new IllegalStateException();
				}
				break;
				default:
				throw new IllegalStateException("Parser returned an invalid Type: " + type);
			}						
		}		

			if(stack.size() != 1){
				throw new IllegalPostfixExpressionException();
			}
		
		return stack.pop().getValue();
	
	}
}
