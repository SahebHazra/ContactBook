package com.contact.service;

import com.contact.model.Contact;
import com.contact.storage.ContactStorage;
import com.contact.util.InvalidEnteryException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ContactService {

    private static HashMap<Long, Contact> contactEntery;

    public ContactService(ContactStorage storage) {
        // need old records
        contactEntery = new HashMap<>();
    }

    public boolean addContact(String name, long number) throws InvalidEnteryException {
        if (name == null || name.isBlank())
            throw new InvalidEnteryException("Invalid Entery For Name : "+name);
        if (number <= 1000_000_000)
            throw new InvalidEnteryException("Invalid Entery For Number : " +number);
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

}
