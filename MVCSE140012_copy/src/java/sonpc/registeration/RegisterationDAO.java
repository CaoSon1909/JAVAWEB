/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.registeration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import sonpc.utils.DBHelpers;

/**
 *
 * @author ACER
 */
public class RegisterationDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select username, password From registeration Where username= ? and password = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (rs.next()) {
                    return true;
                }//end if rs.next
            }//end if con existed

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

    public List<RegisterationDTO> accountList;

    public List<RegisterationDTO> search(String searchValue) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (accountList == null) {
                accountList = new Vector<>();
            }//end if accountList existed
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select username, password, lastname, isAdmin From registeration Where lastname like ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchValue + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegisterationDTO dto = new RegisterationDTO(username, password, lastname, role);

                    accountList.add(dto);

                }//end while rs.next

            }//end if con existed

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return accountList;
    }

    public boolean delete(String pk) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Delete From registeration Where username = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, pk);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            } //end if con existed
        }//end try
        finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }

    public boolean update(String username, String password, String lastname, boolean role) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update registeration Set password = ?, lastname = ?, isAdmin = ? Where username = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, password);
                ps.setString(2, lastname);
                ps.setBoolean(3, role);
                ps.setString(4, username);

                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }//end if row > 0 
            }//end if con existed
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

    public boolean register(String username, String password, String lastName, boolean role) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert Into Registeration (username, password, lastname, isAdmin) Values (?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, lastName);
                ps.setBoolean(4, role);

                int row = ps.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        try {
//            RegisterationDAO dao = new RegisterationDAO();
//            dao.register("khanhkute", "khanhkutequatroi", "khanhkutequatroi", "khanh kieu");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(RegisterationDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(RegisterationDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NamingException ex) {
//            Logger.getLogger(RegisterationDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
