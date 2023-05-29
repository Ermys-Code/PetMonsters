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
    private ArrayList<String> items = new ArrayList<String>();
    private ArrayList<User> friends = new ArrayList<>();

    public User(String name, String surname, String email, String username, String password, int age, ArrayList<String> items, ArrayList<User> friends, int money){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.money = money;
        this.age = age;
        this.items = items;
        this.friends = friends;
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

    public void setItems(ArrayList<String> itemArray){
        this.items = itemArray ;
    }
    public ArrayList<String> getItems(){
        return items;
    }

    public void setFriends(ArrayList<User> friendsArray){
        this.friends = friendsArray;
    }
    public ArrayList<User> getFriends() {
        return friends;
    }
}