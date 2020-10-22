/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class CartObject implements Serializable{
    Map<String, Integer> items;

    public Map<String, Integer> getItems() { //getitem ctrl space
        return items;
    }
    
    public void addItemToCart(String title){
        if (this.items == null){
            items = new HashMap<>();
        }//end if
        int quantity = 1;
        if (this.items.containsKey(title)){ 
            quantity = this.items.get(title)+1;
        }
        this.items.put(title, quantity);
    }
    
    public void removeItemToCart(String title){
        if (this.items == null){ //nếu như ko có giỏ
            return;
        }
        if (this.items.containsKey(title)){ //kiem tra do co trong gio ko
            this.items.remove(title);
            //nếu như ko có giỏ thì chắc chắc các phần từ là null
            if (this.items.isEmpty()){
                this.items = null;
            }
        }
    }
    
    
}
