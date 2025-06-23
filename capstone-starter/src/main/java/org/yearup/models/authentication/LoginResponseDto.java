package org.yearup.models.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.yearup.models.User;

/*
    The acronym DTO is being used for "data transfer object". It means that this type of class is specifically
    created to transfer data between the client and the server. For example, CredentialsDto represents the data a client must
    pass to the server for a login endpoint, and TokenDto represents the object that's returned from the server
    to the client from a login endpoint.
 */
public class LoginResponseDto {

    // Properties that make up a login response
    private String token;
    private User user;

    // Injected constructor
    public LoginResponseDto(String token, User user) {
        this.token = token;
        this.user = user;
    }

    // Gets the authentication token for the logged-in user
    @JsonProperty("token")
    String getToken() {
        return token;
    }

    // Setter for the token
    void setToken(String token) {
        this.token = token;
    }

    // Gets the user object associated with the login response
    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    // Setter for the user based on the login response
    public void setUser(User user) {
        this.user = user;
    }
}