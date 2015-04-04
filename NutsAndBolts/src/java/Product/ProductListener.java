/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Product;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author c0633648
 */
@MessageDriven(mappedName="jms/Queue")
public class ProductListener {
    
    @EJB
    ProductList productlist;
    
  
    public void onMessage(Message message) {
        
    
        try {
            System.out.println("Hello");
            if(message instanceof TextMessage){
                String str = ((TextMessage) message).getText();
                JsonObject json = Json.createReader(new StringReader(str)).readObject();
                productlist.add(new Product(json));
                
            
            }
             
 
          
        } catch (JMSException ex) {
            Logger.getLogger(ProductListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
