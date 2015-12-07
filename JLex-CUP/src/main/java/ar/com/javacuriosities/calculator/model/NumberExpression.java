package ar.com.javacuriosities.calculator.model;

public class NumberExpression extends Expression {

	private final Integer value;

	public NumberExpression(Integer value) {
		super();
		this.value = value;
	}

	@Override
	public Integer evaluate() {
		return value;
	}
}
