package ca.ciccc.contactList.input;

public interface InputValidator {

    boolean isValid();

    String getMessage();

    void evaluate(String input);
}
