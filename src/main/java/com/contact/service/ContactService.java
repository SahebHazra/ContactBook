// package com.contact.service;

// import model.Contact;

// import java.util.ArrayList;
// import java.util.HashMap;

// public class ContactService {
//     private static HashMap<Long, Contact> contactEntery;

//     public ContactService() {
//         contactEntery = new HashMap<>();
//     }

//     public void addContact(String name, long number) {
//         if (name == null || name.isBlank())
//             throw new InvalidEnteryException("Invalid Entery For Name");
//         if (number <= 1000_000_000)
//             throw new InvalidEnteryException("Invalid Entery For Number");
//         if (contactEntery.containsKey(number)) {
//             System.out.println("The number alredy exist");
//             System.out.println("With name : " + contactEntery.get(number).getName());
//         }
//     }

// }
