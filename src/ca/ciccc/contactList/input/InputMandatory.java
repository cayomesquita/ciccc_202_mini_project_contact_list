package ca.ciccc.contactList.input;

public class InputMandatory extends InputValidatorAbstract {

    protected InputMandatory() {
        super();
    }

    @Override
    public String getMessage() {
        return "You must enter this field.";
    }

    @Override
    public void evaluate(String input) {
        valid = input != null && !input.trim().isEmpty();
    }
}
