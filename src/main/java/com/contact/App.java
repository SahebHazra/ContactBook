package com.contact;

import com.contact.model.Contact;

public class App {
    public static void main(String args[]) {

        // 1. Add a new contact.
        // 2. View all contacts.
        // 3. Search contact by name.
        // 4. Update contact details.
        // 5. Delete a contact.
        // 6. Save contacts in a text file.

        try {
            Contact con = new Contact("ok", 0456);
            System.out.println(con);
        } catch (Exception e) {
            
        }
        

    }
}