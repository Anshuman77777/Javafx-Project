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
    {String connectionString="mongodb+srv://Anshuman:CodeVimarsh@dukandaar.5ohzx.mongodb.net/?retryWrites=true&w=majority&appName=Dukandaar";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // Get database (MongoDB creates it if it doesn't exist)
            MongoDatabase database = mongoClient.getDatabase("test");

            // Run a simple command to check connection
            Document ping = database.runCommand(new Document("ping", 1));
            System.out.println("Connected successfully to MongoDB! Response: " + ping.toJson());
        } catch (Exception e) {
            System.err.println("Failed to connect to MongoDB: " + e.getMessage());
        }

        
    }
   
    
}
