package ca.ciccc.contactList.input;

public abstract class InputValidatorFactory {

    public static InputValidator createMandatoryValidator() {
        return new InputMandatory();
    }

    public static InputValidator createBooleanValidator() {
        return new InputBoolean();
    }

    public static InputValidator createPhoneNumberValidator() {
        return new InputPhoneNumber();
    }

    public static InputValidator createIntValidator() {
        return new InputInteger();
    }
}
