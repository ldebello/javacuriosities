package ar.com.javacuriosities.calculator.model;

public class EnclosedExpression extends Expression {

	private Expression expr;

	public EnclosedExpression(Expression expr) {
		this.expr = expr;
	}

	@Override
	public Object evaluate() {
		return expr.evaluate();
	}
	
	@Override
	public String toString() {
		return "(" + expr.toString() + ")";
	}
}