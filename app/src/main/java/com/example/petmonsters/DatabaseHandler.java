package com.example.petmonsters;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.dynamicanimation.animation.SpringAnimation;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;

import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.PortUnreachableException;
import java.sql.Blob;
import java.sql.SQLException;
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

        /*JSONArray jsonArray = doc.getJSONArray("items");
        ArrayList<String> stringArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            stringArray.add(jsonArray.getString(i));
        }*/

        ArrayList<Item> itemsArray = new ArrayList<>();

        User user = new User(name, surname, email, username, password, age, itemsArray, money);

        return user;


    }

    public static Boolean createUser(User user) throws ExecutionException, InterruptedException {
        /*String items = "[";
        ArrayList<Item> itemArray = user.getItems();
        for (int i = 0; i < itemArray.size(); i++) {
            items += "\"" + i + "\":\"" + itemArray.get(i).getName() + "\"";
            if (i < itemArray.size() - 1) items += ", ";
        }
        items += "]";*/

        String userDocument = "\"name\":\"" + user.getName() + "\", \"surname\":\"" + user.getSurname() + "\", \"email\":\"" + user.getEmail() + "\", \"username\":\"" + user.getUsername() + "\", \"password\":\"" + user.getPassword() + "\", \"money\":\"" + user.getMoney() + "\", \"items\":\"[]\", \"age\":\"" + user.getAge() + "\"";
        String query = "{\"collection\":\"user\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"document\":{" + userDocument + "}}";
        String queryAction = "insertOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            System.out.println(jsonData);
            JSONObject jsonObject = new JSONObject(jsonData);
            jsonObject.getString("insertedId");
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    //String petDocument = "\"owner\":\"" + owner + "\", \"sprite\":\"" + sprite + "\", \"color\":\"" + color + "\", \"shoes\":\"" + shoes + "\", \"hat\":\"" + hat + "\", \"accesory\":\"" + accesory + "\", \"name\":\"" + name + "\"";

    public static Pet readPet(String _owner) throws ExecutionException, InterruptedException, JSONException {
        String query = "{\"collection\":\"pet_user\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"owner\":\"" + _owner + "\"}}";
        String queryAction = "findOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject doc = jsonObject.getJSONObject("document");

        String owner = doc.getString("owner");
        String sprite = doc.getString("sprite");
        int color = doc.getInt("color");
        String shoes = doc.getString("shoes");
        String hat = doc.getString("hat");
        String accesory = doc.getString("accesory");
        String name = doc.getString("name");

        Pet pet = new Pet(owner, sprite, color, shoes, hat, accesory, name);

        return pet;
    }

    public static ArrayList<Item> readItems() throws ExecutionException, InterruptedException, JSONException, SQLException {
        String query = "{\"collection\":\"item\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{}}";
        String queryAction = "findOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray doc = jsonObject.getJSONArray("documents");

        ArrayList<Item> items = new ArrayList<Item>();

        for(int i = 0; i < doc.length(); i++){
            String itemString = doc.getString(i);
            JSONObject itemObject = new JSONObject(itemString);
            int type = itemObject.getInt("type");
            String name  = itemObject.getString("name");
            String sprite = itemObject.getString("sprite");

            items.add(new Item(type,name,sprite));
        }

        return items;
    }



    public static Boolean checkLogin(String username, String password) throws JSONException, ExecutionException, InterruptedException {
        try {
            User user = readUser(username);
            return checkPassword(user, password);
        } catch (Exception e){
            return false;
        }
    }

    public static Boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public static Boolean checkPet(String username){
        try{
            Pet pet = readPet(username);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static ArrayList<Model> getModels() throws ExecutionException, InterruptedException, JSONException {
        ArrayList<Model> modelsArray = new ArrayList<>();

        String query = "{\"collection\":\"pet_selector\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\"}";
        String queryAction = "find";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray docs = jsonObject.getJSONArray("documents");

        for (int i = 0; i < docs.length(); i++){
            JSONObject doc = docs.getJSONObject(i);

            String name = doc.getString("name");
            JSONArray colorsArray = doc.getJSONArray("colors");
            ArrayList<String> colors = new ArrayList<>();
            for (int j = 0; j < colorsArray.length(); j++){
                colors.add(colorsArray.getString(j));
            }

            Model newModel = new Model(name, colors);
            modelsArray.add(newModel);
        }

        return modelsArray;
    }

    public static void setPet(Pet pet){
        //Guardar pet en base de datos
    }
}
