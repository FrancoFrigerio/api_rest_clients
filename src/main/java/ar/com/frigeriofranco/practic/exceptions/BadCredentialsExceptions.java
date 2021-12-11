package ar.com.frigeriofranco.practic.exceptions;


public class BadCredentialsExceptions extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public BadCredentialsExceptions(String msg) {
        super(msg);
    }
}
