package com.india;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "patient")
public class Patient implements Serializable {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private int id;

   @Column(name = "Nom")
   private String firstName;

   @Column(name = "Prenom")
   private String lastName;

   @Column(name = "maladie")
   private String maladie;
   @Column(name = "age")
   private int age;
   

   public Patient() {}
   
   public int getId() {
      return id;
   }
   
   public void setId( int id ) {
      this.id = id;
   }
   
   public String getFirstName() {
      return firstName;
   }
   
   public void setFirstName( String first_name ) {
      this.firstName = first_name;
   }
   
   public String getLastName() {
      return lastName;
   }
   
   public void setLastName( String last_name ) {
      this.lastName = last_name;
   }
   
   public String getMaladie() {
      return maladie;
   }
   
   public void setMaladie( String maladie) {
      this.maladie= maladie;
   }
   public int getAge() {
	      return age;
	   }
	   
	   public void setAge( int age) {
	      this.age= age;
	   }
	   
}