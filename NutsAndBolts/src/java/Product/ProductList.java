/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import Connection.DBConnect;
import Servlet.Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;



/**
 *
 * @author c0633648
 */

@Singleton
public class ProductList {

    public List <Product> productList = new ArrayList<Product>();

    public ProductList() {
        Product product = new Product();

        try (Connection conn = DBConnect.getConnection()) {
            String query = "SELECT * FROM product";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                product.productID = rs.getInt("productID");
                product.name = rs.getString("name");
                product.description = rs.getString("description");
                product.quantity = rs.getInt("quantity");

                productList.add(product);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    public Product getByID( int id){
    
        Product data = null;
        for(Product product: productList){
            if(product.getProductID() == id){
                data = product;
            }
        }
        
        return data;
    
    }
    
    public void add(Product product) throws Exception {

        int data = doUpdate("INSERT INTO product (productID, name,description,quantity) VALUES (?,?,?,?)", String.valueOf(product.productID), product.name , product.description,String.valueOf(product.quantity));
        
        if(data > 0){
            productList.add(product);
            
        }else{
            throw new Exception("Error Occure While Incerting new Product Detail");
        }
        
    }

    public void remove(Product product) throws Exception {
     
       remove(product.productID);

    }

    public void remove(int id) throws Exception {

       int data =  doUpdate("DELETE FROM product where productID=? ", String.valueOf(id));
         if(data > 0){
           Product oldData = getByID(id);
           productList.remove(oldData);
            
        }else{
            throw new Exception("Error Occure While Deleting Product Detail");
        }

    }

    public void set(int id, Product product) throws Exception {
        int data = doUpdate("UPDATE product SET name=?,description=?,quantity=? where productID=?", product.name, product.description, String.valueOf(product.quantity), String.valueOf(product.productID));

         if(data > 0){
           Product oldData = getByID(id);
           oldData.setName(product.name);
           oldData.setDescription(product.getDescription());
           oldData.setQuantity(product.getQuantity());
            
        }else{
            throw new Exception("Error Occure While Updating Product Detail");
        }
    }

    public JsonArray toJSON(){
        
        JsonArray ja = null;
        
        for(Product product : productList){
             JsonObject json = Json.createObjectBuilder()
                    .add("productId", product.productID)
                    .add("name", product.name)
                    .add("description",  product.description)
                    .add("quantity", product.quantity)
                    .build();
         ja.add(json);
        }
        
        return ja;
    }
    
    
    
    private int doUpdate(String query, String... parameter) {
        int change = 0;
        try (Connection conn = DBConnect.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            for (int i = 1; i <= parameter.length; i++) {
                pstmt.setString(i, parameter[i - 1]);
            }
            change = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return change;
    }
    
    
}
