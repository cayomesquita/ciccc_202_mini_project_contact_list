package ca.ciccc.contactList;

import ca.ciccc.contactList.input.InputBoolean;
import ca.ciccc.contactList.input.InputCollector;
import ca.ciccc.contactList.input.InputValidator;
import ca.ciccc.contactList.input.InputValidatorFactory;

public class ContactListApp {

    public static final String QUIT_COMMAND = "quit";
    public static final String QUIT_COMMAND2 = "5";
    public static final String LIST_COMMAND = "1";
    public static final String NEW_COMMAND = "2";
    public static final String REMOVE_COMMAND = "3";
    public static final String UPDATE_COMMAND = "4";
    public static final String BYE = "Bye!";
    public static final String PHONE_MOBILE = Contact.MOBILE;

    private DisplayController display;
    private ContactList contactList;

    public ContactListApp() {
        display = new DisplayController();
        contactList = new ContactList();
    }

    public void run() {
        String input = "";
        while (!QUIT_COMMAND.equalsIgnoreCase(input)) {
            display.showMenu();
            input = InputCollector.getUserInput("Enter your option: ").get();
            switch (input) {
                case NEW_COMMAND:
                    boolean added = false;
                    Contact newContact = createNewContact();
                    if (!contactList.contains(newContact)) {
                        added = contactList.addContact(newContact);
                    }
                    System.out.println(added ? "Suceesfully add a new contact!" : "Contact already exist!");
                    break;
                case LIST_COMMAND:
                    contactList.printAllContacts();
                    break;
                case REMOVE_COMMAND:
                    removeContact();

                    break;
                case UPDATE_COMMAND:
                    updateContact();
                    break;
                case QUIT_COMMAND2:
                    input = QUIT_COMMAND;
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
        System.out.println(BYE);
    }


    public boolean removeContact() {
        contactList.printAllContacts();
        if (contactList.isEmpty()) {
            System.out.println("No contacts to remove!");
        }
        while (true) {
            int contactsSize = contactList.size();
            Integer index = InputCollector.getUserInputInt("Enter the index of the contact to remove: ");
            if (index < 0 || index >= contactsSize) {
                invalidInputIndexContactList(contactsSize);
                continue;
            }
            Contact removed = contactList.removeContactByID(index.intValue());
            if (removed != null) {
                System.out.printf("Successfully removed %s\n", removed.getName());
            } else {
                System.out.println("No contact removed!");
            }
            return true;
        }
    }

    public boolean updateContact() {
        contactList.printAllContacts();
        if (contactList.isEmpty()) {
            System.out.println("No contacts to update!");
        }
        while (true) {
            int limitIndex = contactList.size();
            Integer index = InputCollector.getUserInputInt("Enter the index of the contact to update: ");
            if (index < 0 || index >= limitIndex) {
                invalidInputIndexContactList(limitIndex);
                continue;
            }
            Contact contact = contactList.getContactByIndexID(index.intValue());


            System.out.println("**** Update contact ****");
            Contact contactAux = fillContactFields();
            if (contactList.contains(contactAux)) {
                System.out.println("Contact already exist!");
                return false;
            } else {
                contact.setName(contactAux.getName());
                contact.setNickName(contactAux.getNickName());
                contact.setEmail(contactAux.getEmail());
                contact.setAddress(contactAux.getAddress());
                contact.setPhones(contactAux.getPhones());

                System.out.printf("Successfully %s updated\n", contact.getName());
            }
            return true;
        }
    }

    private void invalidInputIndexContactList(int contactsSize) {
        System.out.printf("Invalid Input. Enter number between 0 and %d.\n", contactsSize - 1);
    }

    private Contact createNewContact() {
        System.out.println("**** Crete new contact ****");
        return fillContactFields();
    }

    private Contact fillContactFields() {
        final Contact.ContactBuilder builder = Contact.builder();
        InputCollector.getUserInput("Full name: ", InputValidatorFactory.createMandatoryValidator()).ifPresent(name -> builder.name(name));
        InputCollector.getUserInput("Nickname: ").ifPresent(nickName -> builder.nickName(nickName));
        InputCollector.getUserInput("E-mail: ").ifPresent(email -> builder.email(email));

        boolean atLeastOnePhoneMobileFlag = false;
        boolean addPhoneFlag = questionAddPhoneNumber();
        do {
            if (addPhoneFlag) {
                String type = InputCollector.getUserInput("Phone number type: ", InputValidatorFactory.createMandatoryValidator()).get();
                InputValidator phoneNumberValidator = InputValidatorFactory.createPhoneNumberValidator();
                String number = InputCollector.getUserInput("Phone number: ", InputValidatorFactory.createMandatoryValidator(),
                        phoneNumberValidator).get();
                builder.phoneNumber(type, number);

                atLeastOnePhoneMobileFlag = atLeastOnePhoneMobileFlag || PHONE_MOBILE.equalsIgnoreCase(type);
                addPhoneFlag = questionAddPhoneNumber();
            } else if (!atLeastOnePhoneMobileFlag) {
                System.out.println("You must have at least one Phone number mobile.");
                addPhoneFlag = questionAddPhoneNumber();
            }
        } while (addPhoneFlag || !atLeastOnePhoneMobileFlag);

        InputCollector.getUserInput("Address: ").ifPresent(address -> builder.address(address));
        return builder.create();
    }

    private boolean questionAddPhoneNumber() {
        InputBoolean booleanValidator = (InputBoolean) InputValidatorFactory.createBooleanValidator();
        return booleanValidator.valueOf(InputCollector.getUserInput("Add Phone number(y/n): ", InputValidatorFactory.createMandatoryValidator(),
                booleanValidator).get());
    }

}
