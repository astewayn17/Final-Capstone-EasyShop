package org.yearup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.yearup.models.authentication.Authority;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {

   // These properties define a user
   private int id;
   private String username;
   @JsonIgnore
   private String password;
   @JsonIgnore
   private boolean activated;
   private Set<Authority> authorities = new HashSet<>();

   // Constructor for a bare user but always activated
   public User() { this.activated = true; }

   // Constructor
   public User(int id, String username, String password, String authorities) {
      this.id = id;
      this.username = username;
      this.password = password;
      if(authorities != null) this.setAuthorities(authorities);
      this.activated = true;
   }

   // Getter and setters for these properties
   public int getId() { return id; }
   public void setId(int id) { this.id = id; }
   public String getUsername() { return username; }
   public void setUsername(String username) { this.username = username; }
   public String getPassword() { return password; }
   public void setPassword(String password) { this.password = password; }
   public boolean isActivated() { return activated; }
   public void setActivated(boolean activated) { this.activated = activated; }
   public Set<Authority> getAuthorities() { return authorities; }
   public void setAuthorities(Set<Authority> authorities) { this.authorities = authorities; }

   // Sets authorities for the user based on a comma-separated string
   public void setAuthorities(String authorities) {
      String[] roles = authorities.split(",");
      for(String role : roles) {
         addRole(role);
      }
   }

   // Adds a single role to the user's authority list
   public void addRole(String role) {
      String authority = role.contains("ROLE_") ? role : "ROLE_" + role;
      this.authorities.add(new Authority(authority));
   }

   // Checks equality between this user and another object
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return id == user.id &&
              activated == user.activated &&
              Objects.equals(username, user.username) &&
              Objects.equals(password, user.password) &&
              Objects.equals(authorities, user.authorities);
   }

   // Returns hash code based on their properties
   @Override
   public int hashCode() {
      return Objects.hash(id, username, password, activated, authorities);
   }

   // Returns a string representation of the user
   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", username='" + username + '\'' +
              ", activated=" + activated +
              ", authorities=" + authorities +
              '}';
   }

   // Returns the first role assigned to the user in uppercase
   @JsonIgnore
   public String getRole() {
      if(authorities.size() > 0) {
         for(Authority role: authorities) {
            return role.getName().toUpperCase();
         }
      }
      return "ROLE_USER";
   }
}