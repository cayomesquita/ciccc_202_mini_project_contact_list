package ca.ciccc.contactList.input;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputPhoneNumber extends InputValidatorAbstract {

    private final static String REGEX = "(\\+[\\d]{1,2} )?(\\d{3}|\\(\\d{3}\\))[ -]\\d{3}[ -]\\d{4}";

    @Override
    public String getMessage() {
        return "Invalid phone number input. Valid patterns:(111 111 1111, 111-111-1111,(111) 111 1111";
    }

    @Override
    public void evaluate(String input) {
        if (input != null && !input.isEmpty()) {
            Pattern pattern = Pattern.compile(REGEX);
            Matcher match = pattern.matcher(input);
            valid = match.matches();
        }
    }
}
