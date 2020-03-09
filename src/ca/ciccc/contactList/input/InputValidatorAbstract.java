package ca.ciccc.contactList.input;

import java.util.Objects;

public abstract class InputValidatorAbstract implements InputValidator {

    protected String input;

    protected boolean valid = true;

    protected InputValidatorAbstract() {
        super();
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputValidatorAbstract that = (InputValidatorAbstract) o;
        return input.equals(that.input);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input);
    }

    @Override
    public String toString() {
        return "InputValidatorAbstract{" +
                "input='" + input + '\'' +
                '}';
    }
}
