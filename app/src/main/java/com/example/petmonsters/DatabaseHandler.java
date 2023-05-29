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

    public static Boolean createUser(User user) throws Exception {
        String userDocument = Constants.user_document_name_field + user.getName() + Constants.user_document_surname_field + user.getSurname() + Constants.user_document_email_field + user.getEmail() + Constants.user_document_username_field + user.getUsername() + Constants.user_document_password_field + user.getPassword() + Constants.user_document_money_field + user.getMoney() + Constants.user_document_items_age_field + user.getAge() + Constants.user_document_friends_field;
        String query = Constants.query_create_user + userDocument + Constants.query_create_user_end;
        String queryAction = Constants.query_action_insert_one;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            System.out.println(jsonData);
            JSONObject jsonObject = new JSONObject(jsonData);
            jsonObject.getString(Constants.json_key_inserted_id);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public static User readUser(String _username, Boolean getFriends) throws Exception {
        String query = Constants.query_read_user + _username + Constants.query_read_user_end;
        String queryAction = Constants.query_action_find_one;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject doc = jsonObject.getJSONObject(Constants.json_key_document);

        String name = doc.getString(Constants.json_key_name);
        String surname = doc.getString(Constants.json_key_surname);
        String email = doc.getString(Constants.json_key_email);
        String username = doc.getString(Constants.json_key_username);
        String password = doc.getString(Constants.json_key_password);
        int age = doc.getInt(Constants.json_key_age);
        int money = doc.getInt(Constants.json_key_money);
        JSONArray itemsArray = doc.getJSONArray(Constants.json_key_items);

        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < itemsArray.length(); i++){
            items.add(itemsArray.getString(i));
        }

        if(!getFriends){
            User user = new User(name, surname, email, username, password, age, items, null, money);
            return user;
        } else {
            JSONArray friendsArray = doc.getJSONArray(Constants.json_key_friends);
            ArrayList<User> usersArray = new ArrayList<>();
            for (int i = 0; i < friendsArray.length(); i++){
                String friendUsername = friendsArray.getString(i);
                usersArray.add(readUser(friendUsername, false));
            }
            User user = new User(name, surname, email, username, password, age, items, usersArray, money);
            return user;
        }
    }

    public static Boolean updateUser(String _username, ArrayList<String> _items) throws Exception {
        String items = "";
        for(int i = 0; i < _items.size(); i++){
            items += Constants.doblequote + _items.get(i) + Constants.doblequote;
            if (i < _items.size()-1) items += Constants.spaced_coma;
        }

        String query = Constants.query_update_user + _username + Constants.query_update_user_middle + items + Constants.query_update_user_end;
        String queryAction = Constants.query_action_update_one;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            int count = jsonObject.getInt(Constants.json_key_modified_count);
            if (count <= 0) return false;
            return true;
        }
        catch (Exception e) { return false; }
    }

    public static Boolean updateUserFriends(String _username, ArrayList<String> _friends) throws Exception {
        String friends = "";
        for(int i = 0; i < _friends.size(); i++){
            friends += Constants.doblequote + _friends.get(i) + Constants.doblequote;
            if (i != _friends.size()-1) friends += Constants.spaced_coma;
        }

        String query = Constants.query_update_user + _username + Constants.query_update_user_frends_middle + friends + Constants.query_update_user_end;
        String queryAction = Constants.query_action_update_one;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            int count = jsonObject.getInt(Constants.json_key_modified_count);
            if (count <= 0) return false;
            return true;
        }
        catch (Exception e) { return false; }
    }

    public static Boolean createPet(Pet pet) throws Exception {
        String tags = "";
        for (int i = 0; i < pet.getTags().size(); i++){
            tags += Constants.doblequote + pet.getTags().get(i) + Constants.doblequote;
            if (i < pet.getTags().size()-1) tags += Constants.spaced_coma;
        }

        String userDocument = Constants.pet_document_owner_field + pet.getOwner() + Constants.pet_document_sprite_field + pet.getSprite() + Constants.pet_document_color_field + pet.getColor() + Constants.pet_document_shoes_hat_name_field + pet.getName() + Constants.pet_document_tags_field + tags + Constants.pet_document_tags_field_end;
        String query = Constants.query_create_pet + userDocument + Constants.query_create_pet_end;
        String queryAction = Constants.query_action_insert_one;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            jsonObject.getString(Constants.json_key_inserted_id);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public static Boolean CreateRequest(Request request) throws Exception {
        String requestDocument = Constants.request_document_from_field + request.getFrom() + Constants.request_document_to_field + request.getTo() + Constants.request_document_to_field_end;
        String query = Constants.query_create_request + requestDocument + Constants.query_create_request_end;
        String queryAction = Constants.query_action_insert_one;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            jsonObject.getString(Constants.json_key_inserted_id);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public static ArrayList<Request> readRequest(String recipient) throws Exception {
        String query = Constants.query_read_request + recipient + Constants.query_read_request_end;
        String queryAction = Constants.query_action_find;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray doc = jsonObject.getJSONArray(Constants.json_key_documents);

        ArrayList<Request> requests = new ArrayList<>();

        for(int i = 0; i < doc.length(); i++){
            JSONObject req = doc.getJSONObject(i);
            requests.add(new Request(req.getString(Constants.json_key_from), req.getString(Constants.json_key_to)));
        }

        return requests;
    }

    public static Pet readPet(String _owner) throws Exception {
        String query = Constants.query_read_pet + _owner + Constants.query_read_pet_end;
        String queryAction = Constants.query_action_find_one;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject doc = jsonObject.getJSONObject(Constants.json_key_document);

        String owner = doc.getString(Constants.json_key_owner);
        String sprite = doc.getString(Constants.json_key_sprite);
        String color = doc.getString(Constants.json_key_color);
        String shoes = doc.getString(Constants.json_key_shoes);
        String hat = doc.getString(Constants.json_key_hat);
        String name = doc.getString(Constants.json_key_name);
        ArrayList<String> tags = new ArrayList<>();
        JSONArray tagsArray = doc.getJSONArray(Constants.json_key_tags);
        for (int i = 0; i < tagsArray.length(); i++){
            tags.add(tagsArray.getString(i));
        }

        Pet pet = new Pet(owner, sprite, color, shoes, hat, name, tags);

        return pet;
    }

    public static Boolean updatePet(String _owner, int _type, String _spriteName) throws Exception {
        String type = "";
        switch (_type){
            case 0:
                type = Constants.type_case_hat;
                break;
            case 1:
                type = Constants.type_case_shoes;
                break;
        }

        String query = Constants.query_update_pet + _owner + Constants.query_update_pet_middle_1 + type + Constants.query_update_pet_middle_2 + _spriteName + Constants.query_update_pet_end;
        String queryAction = Constants.query_action_update_one;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            int count = jsonObject.getInt(Constants.json_key_modified_count);
            if (count <= 0) return false;
            return true;
        }
        catch (Exception e) { return false; }
    }

    public static ArrayList<Item> readItems() throws Exception {
        String query = Constants.query_read_items;
        String queryAction = Constants.query_action_find;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray doc = jsonObject.getJSONArray(Constants.json_key_documents);

        ArrayList<Item> items = new ArrayList<Item>();

        for(int i = 0; i < doc.length(); i++){
            String itemString = doc.getString(i);
            JSONObject itemObject = new JSONObject(itemString);
            int type = itemObject.getInt(Constants.json_key_type);
            String name  = itemObject.getString(Constants.json_key_name);
            String sprite = itemObject.getString(Constants.json_key_sprite);
            int value = itemObject.getInt(Constants.json_key_value);
            JSONArray tags = itemObject.getJSONArray(Constants.json_key_tags);
            ArrayList<String> tagStrings = new ArrayList<>();
            for (int j = 0; j < tags.length(); j++){
                String tag = tags.getString(j);
                tagStrings.add(tag);
            }

            items.add(new Item(type,name,sprite, tagStrings, value));
        }

        return items;
    }

    public static ArrayList<String> readItemTags(String sprite) throws Exception {
        String query = Constants.query_read_item_tags + sprite + Constants.query_read_item_tags_end;
        String queryAction = Constants.query_action_find_one;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject doc = jsonObject.getJSONObject(Constants.json_key_document);

        JSONArray tags = doc.getJSONArray(Constants.json_key_tags);
        ArrayList<String> tagStrings = new ArrayList<>();
        for (int i = 0; i < tags.length(); i++){
            String tag = tags.getString(i);
            tagStrings.add(tag);
        }

        return tagStrings;
    }



    public static Boolean checkLogin(String username, String password) {
        try {
            User user = readUser(username, false);
            return checkPassword(user, password);
        }
        catch (Exception e) { return false; }
    }

    public static Boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public static Boolean checkPet(String username){
        try{
            Pet pet = readPet(username);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public static ArrayList<Model> getModels() throws Exception {
        ArrayList<Model> modelsArray = new ArrayList<>();

        String query = Constants.query_get_models;
        String queryAction = Constants.query_action_find;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray docs = jsonObject.getJSONArray(Constants.json_key_documents);

        for (int i = 0; i < docs.length(); i++){
            JSONObject doc = docs.getJSONObject(i);

            String name = doc.getString(Constants.json_key_name);
            JSONArray colorsArray = doc.getJSONArray(Constants.json_key_colors);
            ArrayList<String> colors = new ArrayList<>();
            for (int j = 0; j < colorsArray.length(); j++){
                colors.add(colorsArray.getString(j));
            }
            ArrayList<String> tags = new ArrayList<>();
            JSONArray tagsArray = doc.getJSONArray(Constants.json_key_tags);
            for (int j = 0; j < tagsArray.length(); j++){
                tags.add(tagsArray.getString(j));
            }

            Model newModel = new Model(name, colors, tags);
            modelsArray.add(newModel);
        }

        return modelsArray;
    }

    public static Boolean createMessage(Message message) throws Exception {
        String messageDocument = Constants.message_document_form + message.getFrom() + Constants.message_document_to + message.getTo() + Constants.message_document_title + message.getTitle() + Constants.message_document_message + message.getMessage() + Constants.message_document_message_end;
        String query = Constants.query_create_message + messageDocument + Constants.query_create_message_end;
        String queryAction = Constants.query_action_insert_one;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            jsonObject.getString(Constants.json_key_inserted_id);
            return true;
        }
        catch (Exception e) { return false; }
    }

    public static ArrayList<Message> readMessages(String _to) throws Exception {
        ArrayList<Message> messagesArray = new ArrayList<>();

        String query = Constants.query_read_message + _to + Constants.query_read_message_end;
        String queryAction = Constants.query_action_find;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray docs = jsonObject.getJSONArray(Constants.json_key_documents);

        for (int i = 0; i < docs.length(); i++){
            JSONObject doc = docs.getJSONObject(i);
            String from = doc.getString(Constants.json_key_from);
            String to = doc.getString(Constants.json_key_to);
            String title = doc.getString(Constants.json_key_title);
            String message = doc.getString(Constants.json_key_message);

            messagesArray.add(new Message(from, to, title, message));
        }

        return messagesArray;
    }

    public static Boolean deleteMessage(String from, String to, String title, String message) throws Exception {

        String query = Constants.query_delete_message + from + Constants.query_delete_message_middle_1 + to + Constants.query_delete_message_middle_2 + title + Constants.query_delete_message_middle_3 + message + Constants.query_delete_message_end;
        String queryAction = Constants.query_action_delete_one;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();


        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            int count = jsonObject.getInt(Constants.json_key_deleted_count);
            if (count <= 0) return false;
            return true;
        }
        catch (Exception e) { return false; }
    }

    public static Boolean deleteRequest(String from, String to) throws Exception {

        String query = Constants.query_delete_request + from + Constants.query_delete_request_middle + to + Constants.query_delete_request_end;
        String queryAction = Constants.query_action_delete_one;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();


        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            int count = jsonObject.getInt(Constants.json_key_deleted_count);
            if (count <= 0) return false;
            return true;
        }
        catch (Exception e) { return false; }
    }

    public static String getAdd(String theme) throws Exception {
        String query = Constants.query_get_add + theme + Constants.query_get_add_end;
        String queryAction = Constants.query_action_find_one;
        String jsonData = new AsyncConnectWithApi().execute(query, queryAction).get();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject doc = jsonObject.getJSONObject(Constants.json_key_document);

        String add = doc.getString(Constants.json_key_add);

        return add;
    }

}
