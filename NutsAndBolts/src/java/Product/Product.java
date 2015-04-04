/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Product;

import javax.json.Json;
import javax.json.JsonObject;




/**
 *
 * @author c0633648
 */
public class Product {
    
    public int productID;
    public String name;
    public String description;
    public int quantity;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product() {
      
    }
    
    public  Product (JsonObject json){
    
        this.productID = json.getInt("productID");
        this.name = json.getString("name");
        this.description = json.getString("description");
        this.quantity = json.getInt("quantity");
        
    }
                                                  
    public Product(int productID, String name, String description, int quantity) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }
    
    public  void consumeJSON(JsonObject json){
    
        this.productID = json.getInt("productID");
        this.name = json.getString("name");
        this.description = json.getString("description");
        this.quantity = json.getInt("quantity");
        
    }
    
    public JsonObject toJSON(){
         JsonObject json = Json.createObjectBuilder()
                    .add("productId", this.productID)
                    .add("name", this.name)
                    .add("description",  this.description)
                    .add("quantity", this.quantity)
                    .build();
         return json;
    }
    
    
    
    
    
    
    
    
    
}
