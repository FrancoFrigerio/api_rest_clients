package ar.com.frigeriofranco.practic.exceptions;


import lombok.Data;

@Data
public class RolesNotFoundException extends RuntimeException{
    private final static Long serialVersionUID = 1L;
    private String errorMessage;
    private String errorCode;

    public RolesNotFoundException(String messge) {
        this.errorMessage=messge;
    }
}
