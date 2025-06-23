package org.yearup.security;

import org.springframework.security.core.AuthenticationException;

// This is a custom exception when the user attempts to authenticate their account.
// Typically handled by spring's security authentication to give a meaningful error response
public class UserNotActivatedException extends AuthenticationException {

    private static final long serialVersionUID = -1126699074574529145L;

    public UserNotActivatedException(String message) {
        super(message);
    }
}