/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ACER
 */
public class CartDTO implements Serializable{
    private String custName;
    private Map<String, Integer> items;

    public CartDTO() {
    }

    public CartDTO(String custName, Map<String, Integer> items) {
        if (this.items == null){
            items = new HashMap<>();
        }
        this.custName = custName;
        this.items = items;
    }

    
    
    /**
     * @return the custName
     */
    public String getCustName() {
        return custName;
    }

    /**
     * @param custName the custName to set
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * @return the items
     */
    public Map<String, Integer> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }
    
//    public int getQuantityValue(){
//        int quantity = 1;
//        for (String title : items.keySet()){
//            if (items.containsKey(title)){
//                quantity = items.get(title) + 1;
//            }
//        }
//        return quantity;
//    }
    
    public int getQuantityValue(){
        int quantity = 1;
        for (Map.Entry entry : items.entrySet()){
            quantity = (Integer) entry.getValue() + 1;
        }
        return quantity;
    }
    
    public String getTitleKey(){
        String title = null;
        for (Map.Entry entry : items.entrySet()){
            title = (String) entry.getKey();
        }
        return title;
    }

   
    
    
    
    
}
