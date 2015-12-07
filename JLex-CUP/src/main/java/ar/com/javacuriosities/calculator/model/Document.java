package ar.com.javacuriosities.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Document {
	private List<Expression> expressions = new ArrayList<>();
	
	public Document() {
	}
	
	public Document(Document prevDocument) {
		expressions = new ArrayList<>(prevDocument.expressions);
	}
	
	public void addExpression(Expression expr) {
		expressions.add(expr);
	}
}