package ca.ciccc.contactList;

import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class Contact {

    public static final String MOBILE = "mobile";

    private String name;
    private String nickName;
    private String email;
    private String address;
    private List<PhoneNumber> phones;

    public static ContactBuilder builder() {
        return ContactBuilder.getInstance();
    }

    private Contact() {
        super();
        phones = new ArrayList();
    }

    public List<PhoneNumber> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneNumber> phones) {
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name) &&
                checkPhoneMobile(this, contact);
    }

    private boolean checkPhoneMobile(Contact contact, Contact other) {
        Optional<PhoneNumber> mobile = contact.phones.stream()
                .filter(phoneNumber -> MOBILE.equalsIgnoreCase(phoneNumber.getType()))
                .findFirst();
        Optional<PhoneNumber> mobileOther = other.phones.stream()
                .filter(phoneNumber -> MOBILE.equalsIgnoreCase(phoneNumber.getType()))
                .findFirst();
        return mobile.equals(mobileOther);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phones);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("<" + this.name + "> ");
        String attributes = Arrays.stream(this.getClass().getDeclaredFields())
                .filter(field -> field.getName() != "name")
                .filter(field -> !Modifier.isStatic(field.getModifiers())) // not static
                .map(field -> {
                    try {
                        return field.get(this) == null ? "" : field.getName() + "=" + field.get(this);
                    } catch (IllegalAccessException e) {
                        return "";
                    }
                })
                .filter(field -> !field.isEmpty())
                .collect(Collectors.joining(", "));
        if (!attributes.isEmpty()) {
            sb.append("(");
            sb.append(attributes);
            sb.append(")");
        }
        return sb.toString();
    }

    public static class ContactBuilder {

        private static ContactBuilder instance = new ContactBuilder();

        private String name;
        private String nickName;
        private String email;
        private String address;
        private Map<String, String> phones = new HashMap<>();

        public static ContactBuilder getInstance() {
            return instance.reset();
        }

        public ContactBuilder name(String name) {
            if (name != null && !name.trim().isEmpty()) {
                this.name = name;
            }
            return this;
        }

        public ContactBuilder nickName(String nickName) {
            if (nickName != null && !nickName.trim().isEmpty()) {
                this.nickName = nickName;
            }
            return this;
        }

        public ContactBuilder email(String email) {
            if (email != null && !email.trim().isEmpty()) {
                this.email = email;
            }
            return this;
        }

        public ContactBuilder address(String address) {
            if (address != null && !address.trim().isEmpty()) {
                this.address = address;
            }
            return this;
        }

        public ContactBuilder phoneNumber(String type, String number) {
            this.phones.put(type, number);
            return this;
        }

        public ContactBuilder reset() {
            this.address = null;
            this.email = null;
            this.name = null;
            this.nickName = null;
            this.phones.clear();
            return this;
        }

        public Contact create() {
            Contact contact = new Contact();
            contact.name = this.name;
            contact.nickName = this.nickName;
            contact.address = this.address;
            contact.email = this.email;
            contact.phones.addAll(this.phones.entrySet().stream()
                    .map(entry -> new PhoneNumber(entry.getValue(), entry.getKey()))
                    .collect(Collectors.toList()));
            return contact;
        }
    }
}
