package com.contact;

import com.contact.model.Contact;
import com.contact.service.ContactService;
import com.contact.storage.ContactStorage;
import com.contact.util.InvalidEnteryException;

import java.io.IOException;
import java.util.Scanner;

public class App {
    private static boolean isEnd;
    private static final Scanner sc = new Scanner(System.in);

    public static void menu() {
        System.out.println("--------------------------------------");
        System.out.println("1. Add a new contact.");
        System.out.println("2. View all contacts.");
        System.out.println("3. Search contact by name.");
        System.out.println("4. Update contact details.");
        System.out.println("5. Delete a contact.");
        System.out.println("6. Save contacts in a text file.");
        System.out.println("7. Exit.");
    }

    public static String takeInput(String message) {
        System.out.print(message);
        String input = sc.nextLine();
        return input;
    }

    public static void runMenu(int option, ContactService service)
            throws InvalidEnteryException, NumberFormatException {
        switch (option) {
            case 1 -> {
                String name = takeInput("Enter Name : ");
                Long number = Long.parseLong(takeInput("Enter Number : "));
                if (service.addContact(name, number))
                    System.out.println("Added : " + name);
            }

            case 2 -> {
                System.out.println("Avilable Contacts are -> ");
                for (Contact contact : service.getAllContacts()) {
                    System.out.println(contact.getName() + " : " + contact.getNumber());
                }
            }
            case 7 -> {
                System.out.println("Good Bye!");
                isEnd = true;
            }
            default -> System.out.println("Pleace Enter between [1-7] ");
        }
    }

    public static void main(String args[]) {

        ContactService service = null;

        try {
            ContactStorage storage = new ContactStorage();
            service = new ContactService(storage);
        } catch (Exception e) {
            System.out.println("Fail to load Some Data");
        }

        if (service == null)
            return;

        while (!isEnd) {
            menu();
            System.out.print("\nChoose : ");
            try {
                int option = Integer.parseInt(sc.nextLine());
                runMenu(option, service);
            } catch (NumberFormatException | InvalidEnteryException e) {
                System.out.println(e);
            }
            System.out.println();
        }

    }
}