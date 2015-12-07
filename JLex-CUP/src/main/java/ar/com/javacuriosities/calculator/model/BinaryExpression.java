package ar.com.javacuriosities.calculator.model;

public abstract class BinaryExpression extends Expression{
	protected Expression lhs;
	
	protected Expression rhs;

	public BinaryExpression(Expression lhs, Expression rhs) {
		super();
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	public abstract Object evaluate();
}
