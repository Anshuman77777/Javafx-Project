/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dukandaar;

/**
 *
 * @author Anshu
 */
import com.mongodb.client.*;
import org.bson.Document;


public class Auth {
    private static final String DB_URI = "mongodb+srv://Anshuman:CodeVimarsh@dukandaar.5ohzx.mongodb.net/?retryWrites=true&w=majority&appName=Dukandaar";
    private static final String DB_NAME = "DUKANDAAR";

    public static boolean signup(String username, String password, int role, String brandOrInventory, String pincode,String info) {
        try (MongoClient mongoClient = MongoClients.create(DB_URI)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(role==0 ? "RETAILER" : "WHOLESALER");
            
            if (collection.find(new Document("USERNAME", username)).first() != null) {
                return false; // Username already exists
            }
            
            
            Document userDoc = new Document("USERNAME", username)
                    .append("PASSWORD", password)
                    .append("PINCODE", pincode);
            userDoc.append("BRANDNAME",brandOrInventory);
            userDoc.append("INFO", info);
           
            
            collection.insertOne(userDoc);
            return true;
        }
    }
    
    public static boolean login(String username, String password, int role) {
        try (MongoClient mongoClient = MongoClients.create(DB_URI)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(role==0 ? "RETAILER" : "WHOLESALER");
            
            Document userDoc = collection.find(new Document("USERNAME", username)).first();
            if (userDoc == null) {
                return false; // User not found
            }
            
            String stored = userDoc.getString("PASSWORD");
            if(stored.equals(password))return true;
            else return false;
        }
    }
    public static void main(String Args[])
    {
        System.out.println(login("Sarthak","1",0));
    }
}
