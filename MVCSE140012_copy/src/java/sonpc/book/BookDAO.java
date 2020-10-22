/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.book;

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
public class BookDAO implements Serializable {

    public boolean updateBookSold(String bookTitle, int quantity) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update bookSold Set quantity = ? Where bookTitle = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, quantity);
                ps.setString(2, bookTitle);
                
                int row = ps.executeUpdate();
                
                if (row > 0){
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
