package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;
import java.security.Principal;

@RestController
@RequestMapping("/profile")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ProfileController {

    // A profile requires
    private ProfileDao profileDao;
    private UserDao userDao;

    // Injected constructor for the profile controller
    @Autowired
    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    // GET the profile for the current user. Gets the user info from their user ID
    @GetMapping
    public Profile getProfile(Principal principal) {
        try {
            String userName = principal.getName();
            User user = userDao.getByUserName(userName);
            int userId = user.getId();
            Profile profile = profileDao.getByUserId(userId);
            if (profile == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
            }
            return profile;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    // PUT update the profile for the current user
    @PutMapping
    public void updateProfile(Principal principal, @RequestBody Profile profile) {
        try {
            String userName = principal.getName();
            User user = userDao.getByUserName(userName);
            int userId = user.getId();
            // Ensure the profile userId matches the authenticated user
            // This prevents users from updating other users' profiles
            profile.setUserId(userId);
            // Check if profile exists
            Profile existingProfile = profileDao.getByUserId(userId);
            if (existingProfile == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
            }
            profileDao.update(userId, profile);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}