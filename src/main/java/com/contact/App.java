package com.contact;

import com.contact.service.ContactService;
import com.contact.storage.ContactStorage;



public class App {

    public static void menu() {
        System.out.println("1. Add a new contact.");
        System.out.println("2. View all contacts.");
        System.out.println("3. Search contact by name.");
        System.out.println("4. Update contact details.");
        System.out.println("5. Delete a contact.");
        System.out.println("6. Save contacts in a text file.");
    }

    public static void runMenu(int option){
        
    }

    public static void main(String args[]) {

        try {
            ContactService conServ = new ContactService(new ContactStorage());
            conServ.addContact("ok", 1000000001l);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}