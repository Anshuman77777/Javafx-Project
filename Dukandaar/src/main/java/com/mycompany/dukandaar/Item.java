/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dukandaar;

import org.bson.Document;

/**
 *
 * @author Anshu
 */


import org.bson.Document;

public class Item {
    private int quantity;
    private double price;

    public Item(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public Document toDocument() {
        return new Document("PRICE", price).append("QUANTITY", quantity);
    }

    public static Item fromDocument(Document doc) {
        return new Item(doc.getInteger("QUANTITY"), doc.getDouble("PRICE"));
    }
}

