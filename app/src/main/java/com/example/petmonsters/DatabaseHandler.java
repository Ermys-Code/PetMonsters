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

    private static MongoClientURI uri = new MongoClientURI("mongodb+srv://raulrtnataliamm:aaO5YMhSu9ZasKaV@clubnopenguin.cbdgr6l.mongodb.net/ClubNoPenguin");
    private static MongoClient mongoClient = new MongoClient(uri);
    private static MongoDatabase db = mongoClient.getDatabase(uri.getDatabase());

    public static User readUser(String username){
        MongoCollection<Document> collection = db.getCollection("user");
        Document doc = collection.find(eq("username", username)).first();

        if (doc != null) {
            System.out.println(doc.toJson());
        } else {
            System.out.println("No matching documents found.");
        }

        User user = new User("a", "a", "a", "a", "a");
        return user;
    }

    public static void createUser(User user){

    }

    public static ArrayList<Model> getModels(){
        ArrayList<Model> modelsArray = new ArrayList<>();

        MongoCollection<Document> collection = db.getCollection("user");
        FindIterable<Document> docs = collection.find();
        Iterator it = docs.iterator();
        while (it.hasNext()) {
            //transformar it.next() a objeto de class Model
            //Añadir Model a array
        }

        return modelsArray;
    }
}
