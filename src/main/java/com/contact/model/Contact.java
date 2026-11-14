package com.contact.model;

import com.contact.util.InvalidEnteryException;

public class Contact {
    private String name;
    private long number;

    public Contact(String name, long number) throws InvalidEnteryException {
        setName(name);
        setNumber(number);
    }

    public void setName(String name) throws InvalidEnteryException {
        if (name == null || name.isBlank())
            throw new InvalidEnteryException("Invalid Entery For Name : "+name);
        this.name = name;
    }

    public void setNumber(long number) throws InvalidEnteryException {
        if (number <= 1000_000_000)
            throw new InvalidEnteryException("Invalid Entery For Number : "+number);
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public long getNumber() {
        return number;
    }

    @Override 
    public String toString() {
        return name+" : "+number;
    }
}
