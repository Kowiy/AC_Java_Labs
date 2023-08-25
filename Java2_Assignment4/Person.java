package Jsonproj.Labassignment;

/*
Class to hold the details of a person residing at an address
 */

public class Person {
   private String firstName; //person's firstname
   private String lastName; //person's lastname
   private String spouseFirstName; //spouse's firstname
   private String spouseLastName; //spouse's lastname

   
   public Person(String firstName, String lastName, String spouseFirstName, String spouseLastName) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.spouseFirstName = spouseFirstName;
       this.spouseLastName = spouseLastName;
   }
   //Getters and Setters for the variables
   public String getFirstName() {
       return firstName;
   }

   public void setFirstName(String firstName) {
       this.firstName = firstName;
   }

   public String getLastName() {
       return lastName;
   }

   public void setLastName(String lastName) {
       this.lastName = lastName;
   }

   public String getSpouseFirstName() {
       return spouseFirstName;
   }

   public void setSpouseFirstName(String spouseFirstName) {
       this.spouseFirstName = spouseFirstName;
   }

   public String getSpouseLastName() {
       return spouseLastName;
   }

   public void setSpouseLastName(String spouseLastName) {
       this.spouseLastName = spouseLastName;
   }
}
