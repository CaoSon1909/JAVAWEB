/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.cart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import sonpc.utils.DBHelpers;

/**
 *
 * @author ACER
 */
public class CartDAO implements Serializable{
    
    public boolean addItemToCart(CartDTO dto) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DBHelpers.makeConnection();
            if (con != null){
                String sql = "Insert Into bookCart (custName, bookTitle, quantity) Values (?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getCustName());
                ps.setString(2, dto.getTitleKey());
                ps.setInt(3, dto.getQuantityValue());
                
                int row = ps.executeUpdate();
                
                if (row > 0){
                    return true;
                }
            }
        }
        finally{
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }
    
    
    public boolean removeItemFromCart(CartDTO dto) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DBHelpers.makeConnection();
            if (con != null){
                String sql = "Delete From bookCart Where bookTitle = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getTitleKey());
                
                int row = ps.executeUpdate();
                
                if (row > 0){
                    return true;
                }
            }
        }
        finally{
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }
    
}
