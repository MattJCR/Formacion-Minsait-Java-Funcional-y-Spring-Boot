package com.example.demo.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerForm {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Only alphanumeric characters are allowed")
    @Size(min = 2, max = 30)
    private String firstName;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Only alphanumeric characters are allowed")
    @Size(min = 2, max = 30)
    private String lastName;
    @NotNull
    @Size(min = 2, max = 30)
    private String email;

    // getters y setters
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return "Customer(Name: " + this.firstName + ", LastName: " + this.lastName + ", email: " + this.email + ")";
    }
}
