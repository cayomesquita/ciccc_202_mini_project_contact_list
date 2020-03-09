package ca.ciccc.contactList.input;

public class InputInteger extends InputValidatorAbstract {
    @Override
    public String getMessage() {
        return "Invalid phone number input.";
    }

    @Override
    public void evaluate(String input) {
        if (input != null && !input.isEmpty()) {
            try {
                Integer.valueOf(input);
                valid = true;
            } catch (NumberFormatException ex) {
                valid = false;
            }
        }
    }

}
