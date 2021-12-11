package ar.com.frigeriofranco.practic.exceptions;

public class ElementNotFound extends RuntimeException{
    public ElementNotFound(String message) {
        super(message);
    }
}
