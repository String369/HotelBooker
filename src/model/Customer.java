package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    // Constructor
    public Customer(final String firstName, final String lastName, final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (!Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}").matcher(email).matches()) {
            throw new IllegalArgumentException("Given email pattern is invalid type");
        }
        this.email = email;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    //Setters
    public String setFirstName(String firstName) {
        this.firstName = firstName;
        return firstName;
    }

    public String setLastName(String lastName) {
        this.lastName = lastName;
        return lastName;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    // toString method for better description
    @Override
    public String toString(){
        return "First Name : " + this.firstName + "\nLast Name : "+this.lastName+"\nemail : "+ this.email;
    }

}
