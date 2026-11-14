package com.contact.service;

import com.contact.model.Contact;
import com.contact.storage.ContactStorage;
import com.contact.util.InvalidEnteryException;

import java.util.Collection;
import java.util.HashMap;

public class ContactService {

    private static HashMap<Long, Contact> contactEntery;

    public ContactService(ContactStorage storage) {
        // need old records
        contactEntery = new HashMap<>();
    }

    public boolean addContact(String name, long number) throws InvalidEnteryException {
        if (name == null || name.isBlank())
            throw new InvalidEnteryException("Invalid Entery For Name : " + name);
        if (number <= 1000_000_000)
            throw new InvalidEnteryException("Invalid Entery For Number : " + number);
        if (contactEntery.containsKey(number)) {
            System.out.println("The number alredy exist");
            System.out.println("With name : " + contactEntery.get(number).getName());
        } else {
            Contact contact = new Contact(name, number);
            contactEntery.put(number, contact);
            // need to save
            return true;
        }
        return false;
    }

    public Collection<Contact> getAllContacts() {
        return contactEntery.values();
    }

    public void searchFor(String name) {
        System.out.println("Matched Enterys are");

        contactEntery.values().stream().filter(n -> n.getName().toLowerCase()
                .contains(name.toLowerCase())).forEach(System.out::println);
    }

    public void searchFor(long number) {
        if (contactEntery.containsKey(number))
            System.out.println("Found -> " + contactEntery.get(number));
        else
            System.out.println("The number wasn't saved yet");
    }

    public boolean updateName(String name, long number) throws InvalidEnteryException {
        if (contactEntery.containsKey(number)) {
            contactEntery.get(number).setName(name);
            // need to save and update

            return true;
        }
        return false;
    }

    public boolean updateNumber(long OGNumber, long newNumber) throws InvalidEnteryException {
        if (contactEntery.containsKey(OGNumber)) {
            String name = contactEntery.get(OGNumber).getName();
            contactEntery.put(newNumber, new Contact(name, newNumber));

            if (contactEntery.containsKey(newNumber)) {
                contactEntery.remove(OGNumber);
                // need to save and update

                return true;
            }
        }
        return false;
    }

    public boolean deleteContact(String name) {
        searchFor(name);
        System.out.println("Try to Delete the Contact by it's Respective Number");
        return false;
    }

    public boolean deleteContact(long number) {
        if (contactEntery.containsKey(number)) {
            contactEntery.remove(number);
            // need to update the records
            return true;
        }
        return false;
    }
}
