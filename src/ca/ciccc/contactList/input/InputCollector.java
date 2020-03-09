package ca.ciccc.contactList.input;

import java.util.Optional;
import java.util.Scanner;

public class InputCollector {

    private static final Scanner console = new Scanner(System.in);
    private static final InputValidator[] NO_VALIDATORS = new InputValidator[0];

    public static String getUserInput(String question, boolean mandatory) {
        String input = null;
        boolean loopFlag = false;
        do {
            System.out.print(question);
            input = console.nextLine().trim();
            loopFlag = mandatory && (input == null || input.trim().isEmpty());
            if (loopFlag) {
                System.out.println("You must enter this field.");
            }
        } while (loopFlag);
        return input;
    }

    public static Optional<String> getUserInput(String question, InputValidator... validators) {
        String input = null;
        boolean loopFlag = false;
        do {
            System.out.print(question);
            input = console.nextLine().trim();
            for (InputValidator validator : validators) {
                validator.evaluate(input);
                loopFlag = !validator.isValid();
                if (loopFlag) {
                    System.out.println(validator.getMessage());
                    break;
                }
            }
        } while (loopFlag);
        return Optional.ofNullable(input);
    }

    public static Optional<String> getUserInput(String question) {
        return getUserInput(question, NO_VALIDATORS);
    }

    public static Integer getUserInputInt(String question) {
        return Integer.valueOf(getUserInput(question, InputValidatorFactory.createMandatoryValidator(), InputValidatorFactory.createIntValidator()).get());
    }
}
