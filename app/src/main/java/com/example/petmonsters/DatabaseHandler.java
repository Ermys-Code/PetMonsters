package com.example.petmonsters;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;

public class DatabaseHandler {

    private static MongoClientURI uri = new MongoClientURI(Constants.ddbb_uri);
    private static MongoClient mongoClient = new MongoClient(uri);
    private static MongoDatabase db = mongoClient.getDatabase(uri.getDatabase());

    public static User readUser(String username){
        MongoCollection<Document> collection = db.getCollection(Constants.collection_user);
        Document doc = collection.find(eq(Constants.user_parameter_username, username)).first();

        if (doc != null) {
            System.out.println(doc.toJson());
        } else {
            System.out.println(Constants.no_documents_found);
        }

        User user = new User("a", "a", "a", "a", "a", 0, null);
        return user;
    }

    public static void createUser(User user){

    }

    public static Boolean checkUsername(String username){
        MongoCollection<Document> collection = db.getCollection(Constants.collection_user);
        Document doc = collection.find(eq(Constants.user_parameter_username, username)).first();
        //if (doc != null) return true;
        //else return false;
        return true;
    }

    public static Boolean checkPassword(String username, String password){
        MongoCollection<Document> collection = db.getCollection(Constants.collection_user);
        Document doc = collection.find(eq(Constants.user_parameter_username, username)).first();
        //if (doc.getValue("password") == password) return true;
        //else return false;
        return true;
    }

    public static ArrayList<Model> getModels(){
        ArrayList<Model> modelsArray = new ArrayList<>();

        MongoCollection<Document> collection = db.getCollection(Constants.collection_user);
        FindIterable<Document> docs = collection.find();
        Iterator it = docs.iterator();
        while (it.hasNext()) {
            //transformar it.next() a objeto de class Model
            //Añadir Model a array
        }

        return modelsArray;
    }

    public static void setPet(Pet pet){
        //Guardar pet en base de datos
    }
}
