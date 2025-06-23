package org.yearup.data;

import org.yearup.models.Profile;

// Data Access Object (DAO) interface for managing user profiles
// Defines operations related to creating profile records in the database
public interface ProfileDao {
    Profile create(Profile profile);
}