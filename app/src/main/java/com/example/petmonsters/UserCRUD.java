package com.example.petmonsters;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.Cursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class UserCRUD {

    private static MongoClientURI uri = new MongoClientURI("mongodb+srv://raulrtnataliamm:aaO5YMhSu9ZasKaV@clubnopenguin.cbdgr6l.mongodb.net/ClubNoPenguin");
    private static MongoClient mongoClient = new MongoClient(uri);
    private static MongoDatabase db = mongoClient.getDatabase(uri.getDatabase());

    public static User readUser(String username){

        //String uria = "mongodb://raulrtnataliamm:aaO5YMhSu9ZasKaV@clubnopenguin.cbdgr6l.mongodb.net:27017/test";

        //CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                //CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        //try (com.mongodb.client.MongoClient mongoClient = MongoClients.create(uria)) {
            //MongoDatabase database = mongoClient.getDatabase("ClubNoPenguin");
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

            MongoCollection<Document> collection = db.getCollection("user");
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");


            Document doc = collection.find(eq("username", username)).first();
            System.out.println("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");

            if (doc != null) {
                System.out.println("dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        //}
        //catch (Exception e){
            //System.out.println("NOCLIENTNOCLIENTNOCLIENTNOCLIENTNOCLIENTNOCLIENT");
        //}

        //.withCodecRegistry(pojoCodecRegistry)


        User user = new User("a", "a", "a", "a", "a");
        return user;
    }

    public static void createUser(User user){

    }
}
