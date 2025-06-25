package org.yearup.data.mysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.yearup.models.Profile;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MySqlProfileDaoTest extends BaseDaoTestClass {

    private MySqlProfileDao profileDao;

    @BeforeEach
    public void setup() {
        profileDao = new MySqlProfileDao(dataSource);
    }

    // Just checks if the profile exists
    @Test
    public void getByUserId_should_returnTheCorrectProfile() {
        // Act
        Profile result = profileDao.getByUserId(1);
        // Assert
        assertNotNull(result);
    }

    // Updating the first name of the profile for user ID 1
    @Test
    public void update_should_updateTheProfile() {
        // Arrange
        Profile updatedProfile = new Profile();
        updatedProfile.setFirstName("UpdatedName");
        // Act
        profileDao.update(1, updatedProfile);
        Profile result = profileDao.getByUserId(1);
        // Assert
        assertEquals("UpdatedName", result.getFirstName());
    }
}