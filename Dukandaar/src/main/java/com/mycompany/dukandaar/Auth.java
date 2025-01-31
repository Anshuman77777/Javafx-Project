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
import java.util.HashMap;
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
        public static boolean updateWholesalerInventory(String username, HashMap<String, Integer> inventory) {
        try (MongoClient mongoClient = MongoClients.create(DB_URI)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection("WHOLESALER");
            
            Document updatedInventory = new Document();
            for (String key : inventory.keySet()) {
                updatedInventory.append(key, inventory.get(key));
            }
            
            Document updateQuery = new Document("$set", new Document("INVENTORY", updatedInventory));
            
            return collection.updateOne(new Document("USERNAME", username), updateQuery).getModifiedCount() > 0;
        }
        }
        public static HashMap<String, Integer> getWholesalerInventory(String username) {
        try (MongoClient mongoClient = MongoClients.create(DB_URI)) {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection("WHOLESALER");
            
            Document userDoc = collection.find(new Document("USERNAME", username)).first();
            if (userDoc == null || !userDoc.containsKey("INVENTORY")) {
                return null; // User not found or no inventory
            }
            
            Document inventoryDoc = (Document) userDoc.get("INVENTORY");
            HashMap<String, Integer> inventory = new HashMap<>();
            for (String key : inventoryDoc.keySet()) {
                inventory.put(key, inventoryDoc.getInteger(key));
            }
            
            return inventory;
        }
    }
        
    public static void main(String Args[])
    {
       System.out.println(getWholesalerInventory("A"));
    }
}
