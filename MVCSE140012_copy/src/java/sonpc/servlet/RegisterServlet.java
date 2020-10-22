/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sonpc.registeration.RegisterationDAO;
import sonpc.registeration.RegisterationInsertError;

/**
 *
 * @author ACER
 */
public class RegisterServlet extends HttpServlet {

    private final String INSERT_ERROR_PAGE = "register.jsp";
    private final String LOGIN_PAGE = "login.html";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String passwordConfirm = request.getParameter("txtConfirm");
        String lastName = request.getParameter("txtFullName");
        String url = INSERT_ERROR_PAGE;
        RegisterationInsertError error = new RegisterationInsertError();
        try {
            //RegisterationInsertError
            boolean catchErr = false; //mặc định là chưa có lỗi

            //cath error
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                catchErr = true;
                error.setUsernameLengthErr("Username Length Requires 6-20 characters");
            }

            if (password.trim().length() < 6 || password.trim().length() > 20) {
                catchErr = true;
                error.setPasswordLengthErr("Password Length Requires 6-20 characters");
            } else if (!passwordConfirm.trim().equals(password.trim())) {
                catchErr = true;
                error.setConfirmNotMatch("Password is not matched");
            }
            if (lastName.trim().length() < 2 || lastName.trim().length() > 50) {
                catchErr = true;
                error.setFullNameLengthErr("Full name requires 2-50 characters");
            }

            if (catchErr) { //nếu như bắt đc lỗi => dùng scope để đưa ra lỗi
                request.setAttribute("INSERTERROR", error);

            }//end if catchErr existed
            else {
                //call dao
                RegisterationDAO dao = new RegisterationDAO();
                //xu ly dao
                boolean result = dao.register(username, password, lastName, false);

                if (result) {
                    url = LOGIN_PAGE;
                }//end if result is true
            }

        } catch (ClassNotFoundException ex) {
            log("RegisterServlet - ClassNotFound" + ex.getMessage());
        } catch (SQLException ex) {
            log("RegisterServlet - SQLException" + ex.getMessage());
            //thông báo lỗi cho người dùng
            error.setUsernameIsExisted(username+ " Is Existed In System");
            //cập nhật lại scope
            request.setAttribute("INSERTERROR", error);
        } catch (NamingException ex) {
            log("RegisterServlet - NamingException" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
