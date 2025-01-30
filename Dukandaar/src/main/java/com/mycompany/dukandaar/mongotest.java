/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dukandaar;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Anshu
 */
public class mongotest {
    public static void main(String args[])
    {updateStockValues("RELIANCE",2800);

        
    }
    public static void updateStockValues(String name, double newValue) {
    // MongoDB Atlas connection string
    String connectionString = "mongodb+srv://sanshuman4422:umangbsdk@cluster0.pi8si.mongodb.net/";

    // Create a MongoDB client and connect to the database
    try (MongoClient mongoClient = MongoClients.create(connectionString)) {
        MongoDatabase database = mongoClient.getDatabase("BEARBULLZ"); 
        MongoCollection<Document> collection = database.getCollection("STOCKS"); 

        // Find the stock document by name
        Document stock = collection.find(Filters.eq("NAME", name)).first();

        if (stock != null) {
            // Get the current values
            double previousValue5 = stock.getDouble("VALUE5");
            double previousValue4 = stock.getDouble("VALUE4");
            double previousValue3 = stock.getDouble("VALUE3");
            double previousValue2 = stock.getDouble("VALUE2");
            double previousValue1 = stock.getDouble("VALUE1");

            // Calculate the new CHANGE_PERC
            double newChangePerc = ((newValue - previousValue5) / previousValue5) * 100;

            // Create the updates
            Bson updates = Updates.combine(
                    Updates.set("VALUE5", newValue),          // New current value
                    Updates.set("VALUE4", previousValue5),    // Previous VALUE5 becomes VALUE4
                    Updates.set("VALUE3", previousValue4),    // Previous VALUE4 becomes VALUE3
                    Updates.set("VALUE2", previousValue3),    // Previous VALUE3 becomes VALUE2
                    Updates.set("VALUE1", previousValue2),    // Previous VALUE2 becomes VALUE1
                    Updates.set("CHANGE_PERC", newChangePerc) // Update CHANGE_PERC
            );

            collection.updateOne(Filters.eq("NAME", name), updates);
            System.out.println("Updated stock: " + name);
        } else {
            System.out.println("Stock not found: " + name);
        }
    }
}
    
}
