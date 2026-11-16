package com.contact;

import com.contact.service.ContactService;
import com.contact.storage.ContactStorage;
import com.contact.util.InvalidEnteryException;

import java.io.IOException;
import java.sql.SQLException;
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

    public static void main(String args[]) {

        ContactService service = null;

        try {
            ContactStorage storage = new ContactStorage();
            service = new ContactService(storage);
            storage.loadDriver();
            System.out.println("hi");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
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
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println();
        }

    }

    public static String takeInput(String message) {
        System.out.print(message);
        String input = sc.nextLine();
        return input;
    }

    public static void runMenu(int option, ContactService service)
            throws InvalidEnteryException, NumberFormatException, IOException {
        switch (option) {
            case 1 -> {
                String name = takeInput("Enter Name : ");
                Long number = Long.parseLong(takeInput("Enter Number : "));
                if (service.addContact(name, number))
                    System.out.println("Added : " + name);
                else
                    System.out.println("Fail to Add");
            }

            case 2 -> {
                var contacts = service.getAllContacts();
                if (contacts.isEmpty()) {
                    System.out.println("No contacts available.");
                } else {
                    System.out.println("Avilable Contacts are -> ");
                    contacts.forEach(System.out::println);
                }
            }

            case 3 -> {
                System.out.println("1. Search by Name");
                System.out.println("2: Search by Number ");
                String type = takeInput("TYPE : ");

                if (type.equals("1")) {
                    String name = takeInput("Enter Name : ");
                    service.searchFor(name);
                } else if (type.equals("2")) {
                    Long number = Long.parseLong(takeInput("Enter Number : "));
                    service.searchFor(number);
                } else
                    System.out.println("Not a Valid Type");
            }

            case 4 -> {
                System.out.println("1. Update the Name");
                System.out.println("2: Update the Number ");
                String type = takeInput("TYPE : ");

                Long OGNumber = Long.parseLong(takeInput("Enter originalNumber : "));
                if (type.equals("1")) {
                    String name = takeInput("Enter newName : ");
                    if (service.updateName(name, OGNumber))
                        System.out.println("Updated Sucessfully");
                    else
                        System.out.println("Failed to Update!");

                } else if (type.equals("2")) {
                    Long newNumber = Long.parseLong(takeInput("Enter newNumber : "));
                    if (service.updateNumber(OGNumber, newNumber))
                        System.out.println("Updated Sucessfully");
                    else
                        System.out.println("Failed to Update!");

                } else
                    System.out.println("Not a Valid Type");
            }

            case 5 -> {
                System.out.println("1. Delete Contact of Name");
                System.out.println("2: Delete Contact of Number ");
                String type = takeInput("TYPE : ");

                if (type.equals("1")) {
                    String name = takeInput("Enter Name : ");
                    if (service.deleteContact(name))
                        System.out.println("Deleted Sucessfully");
                    else
                        System.out.println("Failed to Delete!");

                } else if (type.equals("2")) {
                    Long number = Long.parseLong(takeInput("Enter Number : "));
                    if (service.deleteContact(number))
                        System.out.println("Deleted Sucessfully");
                    else
                        System.out.println("Failed to Delete!");

                } else
                    System.out.println("Not a Valid Type");

            }

            case 6 -> service.saveInTextFile();

            case 7 -> {
                service.saveInTextFile();
                System.out.println("Good Bye!");
                isEnd = true;
            }

            default -> System.out.println("Pleace Enter between [1-7] ");
        }
    }
}