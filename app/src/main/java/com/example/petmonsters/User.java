package com.example.petmonsters;

import java.util.Date;

public class User {
    private String name;
    private String surname;
    private String email;
    //private Date birthdate;
    private String username;
    private String password;

    public User(String name, String surname, String email, String username, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

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

    //public void setBirthdate(Date birthdate){
        //birthdate = birthdate;
    //}
    //public Date getBirthdate(){
        //return birthdate;
    //}

    public void setUsername(String username){
        username = username;
    }
    public String getUsername(){
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    @Override
    public String toString() {
        return "Movie [\n  name=" + name + ",\n  surname=" + surname + ",\n  email=" + email + "username=" + username + "\n password=" + password + "\n]";
    }
}
