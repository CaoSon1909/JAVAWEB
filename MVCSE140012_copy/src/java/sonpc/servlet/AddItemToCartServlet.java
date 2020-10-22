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
import javax.servlet.http.HttpSession;
import sonpc.cart.CartObject;

/**
 *
 * @author ACER
 */
public class AddItemToCartServlet extends HttpServlet {
    private final String SHOPPING_PAGE = "shopping.html";
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
        String url = SHOPPING_PAGE;
        try{
            //1:Customer đi đến chỗ để giỏ (luôn luôn getsession = true: phải tìm thấy đc nơi để giỏ)
            HttpSession session = request.getSession();
            //2: Cust take cart
            CartObject cart = (CartObject) session.getAttribute("CUSTCART");
            //nếu như chưa có giỏ
            if (cart == null){
                cart = new CartObject();
            }
            //3:lấy đồ
            String item = request.getParameter("cboBook"); //copy select bên shopping.html
            //4: customer bỏ đồ vô giỏ
            cart.addItemToCart(item);
            //cập nhật lại attribute
            session.setAttribute("CUSTCART", cart); //copy bên trên
            //5:Shopping tiếp
        }
        finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            //requestdispatcher hay sendredirect đều đc. RD phục vụ cho việc search lại lần nữa
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
