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
import java.util.Map;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class analysiscontroller {
    @FXML
    private TextField pincodeField;
    @FXML
    private BarChart<String, Number> barChart;

    private static final String CONNECTION_STRING = "mongodb+srv://Anshuman:CodeVimarsh@dukandaar.5ohzx.mongodb.net/?retryWrites=true&w=majority&appName=Dukandaar";
    private static final String DATABASE_NAME = "DUKANDAAR";
    private static final String COLLECTION_NAME = "DATA";

    
@FXML
private void handleShowGraph() {
    String pincode = pincodeField.getText().trim();
    if (pincode.isEmpty()) return;
    
    try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        
        barChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        
        // Get and configure the CategoryAxis
        CategoryAxis xAxis = (CategoryAxis) barChart.getXAxis();
        xAxis.setLabel("Product Name");
        xAxis.getCategories().clear();
        
        // Improve label visibility
        xAxis.setTickLabelRotation(90); // Vertical labels
        xAxis.setTickLabelGap(10);
        
        // Set CSS style for label wrapping
        barChart.setStyle("-fx-bar-gap: 15; -fx-category-gap: 20;");
        xAxis.setStyle("-fx-tick-label-font-size: 10px;");
        
        // Adjust chart padding
        barChart.setPadding(new Insets(10, 10, 50, 10)); // Extra bottom padding for labels
        
        // Map to store aggregated product quantities
        Map<String, Integer> productQuantityMap = new HashMap<>();
        FindIterable<Document> results = collection.find(new Document("PINCODE", pincode));
        
        for (Document doc : results) {
            String productName = doc.getString("PRODUCTNAME");
            int quantity = doc.getInteger("QUANTITY");
            // Truncate long product names
            if (productName.length() > 20) {
                productName = productName.substring(0, 17) + "...";
            }
            productQuantityMap.put(productName, productQuantityMap.getOrDefault(productName, 0) + quantity);
        }

        // Add data to chart
        for (Map.Entry<String, Integer> entry : productQuantityMap.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            xAxis.getCategories().add(entry.getKey());
        }
        
        barChart.getData().add(series);
        
        // Add tooltips for truncated names
        for (XYChart.Data<String, Number> data : series.getData()) {
            Tooltip tooltip = new Tooltip(data.getXValue());
            Tooltip.install(data.getNode(), tooltip);
        }
        
        // Force layout refresh
        barChart.layout();
        
        // Ensure the chart takes up available space
        barChart.setMinHeight(400);
        barChart.setMinWidth(600);
    }
}




}

