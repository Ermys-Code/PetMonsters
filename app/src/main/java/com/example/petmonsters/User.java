package com.example.petmonsters;

import java.util.ArrayList;

public class User {
    private String name;
    private String surname;
    private String email;
    private int age;
    private String username;
    private String password;
    private int money;
    private ArrayList<Item> items = new ArrayList<Item>();

    public User(String name, String surname, String email, String username, String password, int age, ArrayList<Item> items, int money){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.money = 0;
        this.age = age;
        this.items = items;
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

    public void setAge(int age){
        age = age;
    }
    public int getAge(){
        return age;
    }

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

    public void setMoney(int money){
        this.money = money;
    }
    public int getMoney(){
        return money;
    }

    public void setItems(ArrayList<Item> itemArray){
        this.items = itemArray ;
    }
    public ArrayList<Item> getItems(){
        return items;
    }

    @Override
    public String toString() {
        return "User [\n  name=" + name + ",\n  surname=" + surname + ",\n  email=" + email + "\n username=" + username + "\n password=" + password + "\n money=" + money + "\n]";
    }
}
