package ar.com.javacuriosities.ws.service;

import ar.com.javacuriosities.ws.exceptions.AddNumbersException;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class CalculatorImpl {

    @WebMethod(action = "addNumbers")
    public int addNumbers(int number1, int number2) throws AddNumbersException {
        if (number1 < 0 || number2 < 0) {
            throw new AddNumbersException("Negative number can't be added!", "Numbers: " + number1 + ", " + number2);
        }
        return number1 + number2;
    }
}
