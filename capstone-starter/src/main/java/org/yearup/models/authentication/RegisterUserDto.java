package org.yearup.models.authentication;

import javax.validation.constraints.NotEmpty;
/*
    The acronym DTO is being used for "data transfer object". It means that this type of class is specifically
    created to transfer data between the client and the server. For example, CredentialsDto represents the data a client must
    pass to the server for a login endpoint, and TokenDto represents the object that's returned from the server
    to the client from a login endpoint.
 */
public class RegisterUserDto {

    // The username chosen by the user. Not allowed to be empty
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @NotEmpty(message = "Please select a role for this user.")
    private String role;

    // Method to get the username
    public String getUsername() {
        return username;
    }

    // Method to set the username
    public void setUsername(String username) {
        this.username = username;
    }

    // Method to get the password
    public String getPassword() {
        return password;
    }

    // Method to set the password
    public void setPassword(String password) {
        this.password = password;
    }

    // Method to get the confirmed password
    public String getConfirmPassword() {
        return confirmPassword;
    }

    // Method to set the confirmed password
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    // Method to get the role
    public String getRole() {
        return role;
    }

    // Method to set the role
    public void setRole(String role) {
        this.role = role;
    }
}