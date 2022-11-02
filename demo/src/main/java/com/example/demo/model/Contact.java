package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contact implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private Date dob;
    private int age;
    private String email;

    private boolean isActive;
    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    //https://stackoverflow.com/questions/49813666/table-dbname-hibernate-sequence-doesnt-exist
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public Contact(){
        id=0;
    }
    public Contact(int id,String name, int age, Date dob, String email) {
        this.id =id;
        this.name =name;
        this.age =age;
        this.dob =dob;
        this.email =email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
