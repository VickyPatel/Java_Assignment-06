/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import Connection.DBConnect;
import Servlet.Services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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

    @EJB
    Services newService;

    public List<Product> productList;

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

    public void add(Product product) {

        newService.add(product.toJSON());
        productList.add(product);

    }

    public void remove(Product product) {

        int id = product.productID;
        newService.deleteById(String.valueOf(id));
        productList.remove(product);

    }

    public void remove(int id) {

        newService.deleteById(String.valueOf(id));

    }

    public void set(int id, Product product) {
        newService.updateData(String.valueOf(id), product.toJSON());

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
    
    
}
