package ca.ciccc.contactList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class ContactList {

    ArrayList<Contact> contactList;

    public ContactList() {
        contactList = new ArrayList<>();
        contactList.addAll(fakeGeneratedContacts());
    }

    private ArrayList<Contact> fakeGeneratedContacts() {
        ArrayList<Contact> list = new ArrayList<>();
        list.add(Contact.builder()
                .name("Carlos Alberto Nobrega")
                .nickName("Carlitos")
                .email("carlos@praca.com")
                .address("Praca eh nossa")
                .phoneNumber("mobile","123-123-1234")
                .phoneNumber("work","123-123-1234")
                .create());
        list.add(Contact.builder()
                .name("Chico butica")
                .nickName("Butica")
                .email("chico@butica.com")
                .phoneNumber("mobile","123-123-1234")
                .create());
        list.add(Contact.builder()
                .name("Denise Deni")
                .nickName("Dani")
                .phoneNumber("mobile","123-123-1234")
                .email("denise@deni.com")
                .create());
        list.add(Contact.builder()
                .name("Nobody From Hollywod")
                .phoneNumber("mobile","123-123-1234")
                .create());
        return list;
    }

    public boolean addContact(Contact newContact) {
        return this.contactList.add(newContact);
    }


    public void printAllContacts() {
        System.out.println("**** Contact list ****");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.printf("%2d. %s\n", i, contactList.get(i).toString());
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return contactList.isEmpty();
    }

    public int size() {
        return contactList.size();
    }

    public Contact removeContactByID(int indexID) {
        return contactList.remove(indexID);
    }

    public Contact getContactByIndexID(int indexId) {
        return contactList.get(indexId);
    }

    public boolean contains(Contact contact) {
        return contactList.contains(contact);
    }
}
