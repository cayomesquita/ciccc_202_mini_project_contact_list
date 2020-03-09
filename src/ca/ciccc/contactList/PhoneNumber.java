package ca.ciccc.contactList;

import java.util.Objects;

public class PhoneNumber {
    String type;
    String number;

    public PhoneNumber(String number, String type) {
        this.type = type.toLowerCase();
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return type.equals(that.type) &&
                number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, number);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", number, type);
    }
}
