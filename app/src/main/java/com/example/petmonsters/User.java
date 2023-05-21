package com.example.petmonsters;

import java.util.Date;

public class User {
    private String name;
    private String surname;
    private String email;
    private Date birthdate;
    private String username;

    public void setName(String name){
        name = name;
    }
    public String getName(){
        return name;
    }

    public void setSurname(String surname){
        surname = surname;
    }
    public String getSurname(){
        return surname;
    }

    public void setEmail(String email){
        email = email;
    }
    public String getEmail(){
        return email;
    }

    public void setBirthdate(Date birthdate){
        birthdate = birthdate;
    }
    public Date getBirthdate(){
        return birthdate;
    }

    public void setUsername(String username){
        username = username;
    }
    public String getUsername(){
        return username;
    }
}
