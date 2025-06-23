package org.yearup.models.authentication;

import java.util.Objects;

public class Authority {

   // Property for the name of the role
   private String name;

   // Getter for the name
   public String getName() {
      return name;
   }

   // Setter for the name
   public void setName(String name) {
      this.name = name;
   }

   // Constructs a new Authority with the given name
   public Authority(String name) {
      this.name = name;
   }

   // Compares this Authority to another to one to see if they are equal based on name
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Authority authority = (Authority) o;
      return name.equals(authority.name);
   }

   // Returns the hash code based on the authority name
   @Override
   public int hashCode() {
      return Objects.hash(name);
   }

   // Returns a string of the authority object
   @Override
   public String toString() {
      return "Authority{" +
         "name=" + name +
         '}';
   }
}