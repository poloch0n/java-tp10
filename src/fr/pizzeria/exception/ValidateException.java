package fr.pizzeria.exception;

public class ValidateException extends PizzaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public ValidateException(String msg) {
		super(msg);
		showText(msg);
	}
}
