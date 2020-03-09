package ca.ciccc.contactList.input;

public class InputBoolean extends InputValidatorAbstract {

    public static final String YES = "yes";
    public static final String N = "n";
    public static final String Y = "y";
    public static final String NO = "no";

    @Override
    public String getMessage() {
        return "Invalid boolean input.";
    }

    @Override
    public void evaluate(String input) {
        if (input != null && !input.isEmpty()) {
            valid = input.equalsIgnoreCase(YES) || input.equalsIgnoreCase(Y) ||
                    input.equalsIgnoreCase(NO) || input.equalsIgnoreCase(N);
        }
    }

    public static boolean valueOf(String input) {
        return input == null ? false : input.equalsIgnoreCase(YES) || input.equalsIgnoreCase(Y);
    }
}
