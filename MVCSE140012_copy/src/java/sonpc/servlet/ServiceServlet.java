/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
public class ServiceServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String SEARCH_SERVLET = "SearchServlet";
    private final String DELETE_SERVLET = "DeleteServlet";
    private final String UPDATE_SERVLET = "UpdateServlet";
    private final String STARTUP_SERVLET = "StartupServlet";
    private final String ADD_ITEM_TO_CART = "AddItemToCartServlet";
    private final String SHOW_ITEM = "ShowItem.jsp";
    private final String REMOVE_ITEM_FROM_CART = "RemoveItemFromCartServlet";
    private final String REGISTER_SERVLET = "RegisterServlet";
    private final String CHECKOUT_SERVLET = "CheckOutCartServlet";
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
        String url = LOGIN_PAGE;
        
        try {
            String button = request.getParameter("btnAction");
            if (button == null) {
                url = STARTUP_SERVLET;
                
            } else if (button.equals("Login")) {
                url = LOGIN_SERVLET;
            } else if (button.equals("Search")) {
                url = SEARCH_SERVLET;
            } else if (button.equals("Delete")) {
                url = DELETE_SERVLET;
            } else if (button.equals("Update")) {
                url = UPDATE_SERVLET;
            }
            else if ("Add to Cart".equals(button)){
                url = ADD_ITEM_TO_CART;
            }
            else if ("View Cart".equals(button)){
                url = SHOW_ITEM;
            }
            else if ("Remove Selected Items".equals(button)){
                url = REMOVE_ITEM_FROM_CART;
            }
            else if (button.equals("Register")){
                url = REGISTER_SERVLET;
            }
            else if (button.equals("Check Out")){
                url = CHECKOUT_SERVLET;
            }
            
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
