/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Product;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

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
            String text = ((TextMessage)message).getText();
            
            
            
          
        } catch (JMSException ex) {
            Logger.getLogger(ProductListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
