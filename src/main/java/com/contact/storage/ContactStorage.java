package com.contact.storage;

import com.contact.model.Contact;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ContactStorage {
    private final String TXT = "LocalContacts.txt";

    public ContactStorage() {

    }

    public boolean saveInTextFile(List<Contact> contacts) throws IOException {
        File file = new File(TXT);
        file.createNewFile();
        try (FileWriter writer = new FileWriter(file)) {
            String newLine = System.lineSeparator();
            writer.write(" Name   :   Number " + newLine);
            if (!contacts.isEmpty()) {
                for (Contact contact : contacts) {
                    writer.write(contact.getName() + " | " + contact.getNumber() + newLine);
                }
                return true;
            } else
                System.out.println("There are No Contacts in Live Memory");
        }
        return false;
    }
}
