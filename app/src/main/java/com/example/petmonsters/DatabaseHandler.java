package com.example.petmonsters;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.activity.result.contract.ActivityResultContracts;
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

    public static Boolean createUser(User user) throws ExecutionException, InterruptedException {
        String userDocument = "\"name\":\"" + user.getName() + "\", \"surname\":\"" + user.getSurname() + "\", \"email\":\"" + user.getEmail() + "\", \"username\":\"" + user.getUsername() + "\", \"password\":\"" + user.getPassword() + "\", \"money\":\"" + user.getMoney() + "\", \"items\":[], \"age\":\"" + user.getAge() + "\", \"friends\":[]";
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

    public static User readUser(String _username, Boolean getFriends) throws ExecutionException, InterruptedException, JSONException {
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
        int money = doc.getInt("money");
        JSONArray itemsArray = doc.getJSONArray("items");

        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < itemsArray.length(); i++){
            items.add(itemsArray.getString(i));
        }

        if(!getFriends){
            User user = new User(name, surname, email, username, password, age, items, money, null);
            return user;
        } else {
            JSONArray friendsArray = doc.getJSONArray("friends");
            ArrayList<User> usersArray = new ArrayList<>();
            for (int i = 0; i < friendsArray.length(); i++){
                String friendUsername = friendsArray.getString(i);
                usersArray.add(readUser(friendUsername, false));
            }
            User user = new User(name, surname, email, username, password, age, items, money, usersArray);
            return user;
        }
    }

    public static Boolean updateUser(String _username, ArrayList<String> _items) throws ExecutionException, InterruptedException {
        String items = "";
        for(int i = 0; i < _items.size(); i++){
            items += "\"" + _items.get(i) + "\"";
            if (i < _items.size()-1) items += ", ";
        }

        String query = "{\"collection\":\"user\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"username\":\"" + _username + "\"}, \"update\":{\"$set\":{\"items\":[" + items + "]}}}";
        String queryAction = "updateOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            int count = jsonObject.getInt("modifiedCount");
            if (count <= 0) return false;
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static Boolean updateUserFriends(String _username, ArrayList<String> _friends) throws ExecutionException, InterruptedException {
        String friends = "";
        for(int i = 0; i < _friends.size(); i++){
            friends += "\"" + _friends.get(i) + "\"";
            if (i != _friends.size()-1) friends += ", ";
        }

        String query = "{\"collection\":\"user\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"username\":\"" + _username + "\"}, \"update\":{\"$set\":{\"friends\":[" + friends + "]}}}";
        String queryAction = "updateOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            int count = jsonObject.getInt("modifiedCount");
            if (count <= 0) return false;
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static Boolean createPet(Pet pet) throws ExecutionException, InterruptedException {
        String tags = "";
        for (int i = 0; i < pet.getTags().size(); i++){
            tags += "\"" + pet.getTags().get(i) + "\"";
            if (i < pet.getTags().size()-1) tags += ", ";
        }

        String userDocument = "\"owner\":\"" + pet.getOwner() + "\", \"sprite\":\"" + pet.getSprite() + "\", \"color\":\"" + pet.getColor() + "\", \"shoes\":\"\", \"hat\":\"\", \"name\":\"" + pet.getName() + "\", \"tags\":[" + tags + "]";
        String query = "{\"collection\":\"pet_user\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"document\":{" + userDocument + "}}";
        String queryAction = "insertOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            jsonObject.getString("insertedId");
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static Boolean CreateRequest(Request request) throws ExecutionException, InterruptedException {
        String requestDocument = "\"from\":\"" + request.getFrom() + "\", \"to\":\"" + request.getTo() + "\"";
        String query = "{\"collection\":\"request\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"document\":{" + requestDocument + "}}";
        String queryAction = "insertOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            jsonObject.getString("insertedId");
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static  ArrayList<Request> readRequest(String recipient) throws ExecutionException, InterruptedException, JSONException {
        String query = "{\"collection\":\"request\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"to\":\"" + recipient + "\"}}";
        String queryAction = "find";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray doc = jsonObject.getJSONArray("documents");

        ArrayList<Request> requests = new ArrayList<>();

        for(int i = 0; i < doc.length(); i++){
            JSONObject req = doc.getJSONObject(i);
            requests.add(new Request(req.getString("from"), req.getString("to")));
        }

        return requests;
    }

    public static Pet readPet(String _owner) throws ExecutionException, InterruptedException, JSONException {
        String query = "{\"collection\":\"pet_user\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"owner\":\"" + _owner + "\"}}";
        String queryAction = "findOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject doc = jsonObject.getJSONObject("document");

        String owner = doc.getString("owner");
        String sprite = doc.getString("sprite");
        String color = doc.getString("color");
        String shoes = doc.getString("shoes");
        String hat = doc.getString("hat");
        String name = doc.getString("name");
        ArrayList<String> tags = new ArrayList<>();
        JSONArray tagsArray = doc.getJSONArray("tags");
        for (int i = 0; i < tagsArray.length(); i++){
            tags.add(tagsArray.getString(i));
        }

        Pet pet = new Pet(owner, sprite, color, shoes, hat, name, tags);

        return pet;
    }

    public static Boolean updatePet(String _owner, int _type, String _spriteName) throws ExecutionException, InterruptedException {
        String type = "";
        switch (_type){
            case 0:
                type = "hat";
                break;
            case 1:
                type = "shoes";
                break;
        }

        String query = "{\"collection\":\"pet_user\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"owner\":\"" + _owner + "\"}, \"update\":{\"$set\":{\"" + type + "\":\"" + _spriteName + "\"}}}";
        String queryAction = "updateOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            int count = jsonObject.getInt("modifiedCount");
            if (count <= 0) return false;
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static ArrayList<Item> readItems() throws ExecutionException, InterruptedException, JSONException, SQLException {
        String query = "{\"collection\":\"item\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\"}";
        String queryAction = "find";
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
            JSONArray tags = itemObject.getJSONArray("tags");
            ArrayList<String> tagStrings = new ArrayList<>();
            for (int j = 0; j < tags.length(); j++){
                String tag = tags.getString(j);
                tagStrings.add(tag);
            }

            items.add(new Item(type,name,sprite, tagStrings));
        }

        return items;
    }

    public static ArrayList<String> readItemTags(String sprite) throws ExecutionException, InterruptedException, JSONException {
        String query = "{\"collection\":\"item\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"sprite\":\"" + sprite + "\"}}";
        String queryAction = "findOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject doc = jsonObject.getJSONObject("document");

        JSONArray tags = doc.getJSONArray("tags");
        ArrayList<String> tagStrings = new ArrayList<>();
        for (int i = 0; i < tags.length(); i++){
            String tag = tags.getString(i);
            tagStrings.add(tag);
        }

        return tagStrings;
    }



    public static Boolean checkLogin(String username, String password) throws JSONException, ExecutionException, InterruptedException {
        try {
            User user = readUser(username, false);
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
            ArrayList<String> tags = new ArrayList<>();
            JSONArray tagsArray = doc.getJSONArray("tags");
            for (int j = 0; j < tagsArray.length(); j++){
                tags.add(tagsArray.getString(j));
            }

            Model newModel = new Model(name, colors, tags);
            modelsArray.add(newModel);
        }

        return modelsArray;
    }

    public static Boolean createMessage(Message message) throws ExecutionException, InterruptedException {
        String messageDocument = "\"from\":\"" + message.getFrom() + "\", \"to\":\"" + message.getTo() + "\", \"title\":\"" + message.getTitle() + "\", \"message\":\""+ message.getMessage() + "\"";
        String query = "{\"collection\":\"message\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"document\":{" + messageDocument + "}}";
        String queryAction = "insertOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            jsonObject.getString("insertedId");
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static ArrayList<Message> readMessages(String _to) throws ExecutionException, InterruptedException, JSONException {
        ArrayList<Message> messagesArray = new ArrayList<>();

        String query = "{\"collection\":\"message\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"to\":\"" + _to + "\"}}";
        String queryAction = "find";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray docs = jsonObject.getJSONArray("documents");

        for (int i = 0; i < docs.length(); i++){
            JSONObject doc = docs.getJSONObject(i);
            String from = doc.getString("from");
            String to = doc.getString("to");
            String title = doc.getString("title");
            String message = doc.getString("message");

            messagesArray.add(new Message(from, to, title, message));
        }

        return messagesArray;
    }

    public static Boolean deleteMessage(String from, String to, String title, String message) throws ExecutionException, InterruptedException, JSONException {

        String query = "{\"collection\":\"message\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"from\":\"" + from + "\", \"to\":\"" + to + "\", \"title\":\"" + title + "\", \"message\":\"" + message + "\"}}";
        String queryAction = "deleteOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();


        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            int count = jsonObject.getInt("deletedCount");
            if (count <= 0) return false;
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static Boolean deleteRequest(String from, String to) throws ExecutionException, InterruptedException, JSONException {

        String query = "{\"collection\":\"request\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"from\":\"" + from + "\", \"to\":\"" + to + "\"}}";
        String queryAction = "deleteOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();


        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            int count = jsonObject.getInt("deletedCount");
            if (count <= 0) return false;
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static String getAdd(String theme) throws ExecutionException, InterruptedException, JSONException {
        String query = "{\"collection\":\"item\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"theme\":\"" + theme + "\"}}";
        String queryAction = "findOne";
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject doc = jsonObject.getJSONObject("document");

        String add = doc.getString("add");

        return add;
    }

}
