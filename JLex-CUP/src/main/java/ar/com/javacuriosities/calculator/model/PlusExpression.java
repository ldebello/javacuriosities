package ar.com.javacuriosities.calculator.model;

public class PlusExpression extends BinaryExpression {

	public PlusExpression(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}

	@Override
	public Object evaluate() {
		return null;
	}
}