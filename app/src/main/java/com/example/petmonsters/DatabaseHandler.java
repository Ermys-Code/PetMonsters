package com.example.petmonsters;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;

import androidx.dynamicanimation.animation.SpringAnimation;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class DatabaseHandler {

    public static User readUser(String _username) throws ExecutionException, InterruptedException, JSONException {
        String query = "{\"collection\":\"user\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"username\":\"" + _username + "\"}}";
        String queryAction = "findOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject doc = jsonObject.getJSONObject("document");

        String name = doc.getString("name");
        String surname = doc.getString("surname");
        String email = doc.getString("email");
        String username = doc.getString("username");
        String password = doc.getString("password");
        int age = doc.getInt("age");
        int money = doc.getInt("age");

        JSONArray jsonArray = doc.getJSONArray("items");
        ArrayList<String> stringArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            stringArray.add(jsonArray.getString(i));
        }

        ArrayList<Item> itemsArray = new ArrayList<>();

        User user = new User(name, surname, email, username, password, age, itemsArray, money);

        return user;

    }

    public static void createUser(User user){

    }

    public static Boolean checkLogin(String username, String password) throws JSONException, ExecutionException, InterruptedException {
        try {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            User user = readUser(username);
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            if (!checkUsername(user, username)) return false;
            System.out.println("ccccccccccccccccccccccccccccccccccccccccccccc");
            if (!checkPassword(user, password)) return false;
            System.out.println("ddddddddddddddddddddddddddddddddddddddddddddd");
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static Boolean checkUsername(User user, String username) {
        System.out.println("ddddddddddddddddddddddddddddd = " + user.getUsername() + " --- " + username);
        return user.getUsername().equals(username);
    }

    public static Boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public static ArrayList<Model> getModels(){
        ArrayList<Model> modelsArray = new ArrayList<>();

        /*MongoCollection<Document> collection = db.getCollection(Constants.collection_user);
        FindIterable<Document> docs = collection.find();
        Iterator it = docs.iterator();
        while (it.hasNext()) {
            //transformar it.next() a objeto de class Model
            //Añadir Model a array
        }*/

        return modelsArray;
    }

    public static void setPet(Pet pet){
        //Guardar pet en base de datos
    }
}
