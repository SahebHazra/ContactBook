package com.contact.storage;

import com.contact.model.Contact;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ContactStorage {
    private final String TXT = "LocalContacts.txt";

    public ContactStorage() {

    }

    // public void loadDriver() throws ClassNotFoundException, SQLException {
    //     Class.forName("com.mysql.cj.jdbc.Driver");

    //     String url = "jdbc:mysql://localhost:3306/demodb";
    //     String user = "root";
    //     String password = "saheb";
    //     Connection connection = DriverManager.getConnection(url, user, password);

    //     Statement statement = connection.createStatement();

    //     String quary = """
    //             INSERT INTO users
    //             VALUES("saheb",24,"active");
    //             """;
    //     statement.executeUpdate(quary);

    //     statement.close();
    //     connection.close();

    // }

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
