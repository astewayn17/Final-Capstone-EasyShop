package org.yearup.models.authentication;
/*
    The acronym DTO is being used for "data transfer object". It means that this type of class is specifically
    created to transfer data between the client and the server. For example, LoginDto represents the data a
    client must pass to the server for a login endpoint, and LoginResponseDto represents the object that's returned
    from the server to the client from a login endpoint.
 */
public class LoginDto {

   // Properties that define a log in
   private String username;
   private String password;

   // Getter for username
   public String getUsername() {
      return username;
   }

   // Setter for username
   public void setUsername(String username) {
      this.username = username;
   }

   // Getter for password
   public String getPassword() {
      return password;
   }

   // Setter for password
   public void setPassword(String password) {
      this.password = password;
   }

   // Returns a string of the authority object
   @Override
   public String toString() {
      return "LoginDTO{" +
              "username='" + username + '\'' +
              ", password='" + password + '\'' +
              '}';
   }
}