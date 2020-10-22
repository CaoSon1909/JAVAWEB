/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
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
public class RemoveItemFromCartServlet extends HttpServlet {

    private final String SHOW_ITEM_PAGE = "ShowItem.jsp";

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
        String urlRewritting = SHOW_ITEM_PAGE;
        try {

            //1: Cust goes to his/her cart place
            HttpSession session = request.getSession(false); //phải là false để check xem item còn tồn tại ko, do có thể hết tgian timeout, object bị hủy bên server
            if (session != null) {
                //2: Cust takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                if (cart != null) {
                    //3: Cust get all items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4: Cust get all removed items
                        String[] removeItems = request.getParameterValues("chkItem"); //copy bên ShowItem.jsp
                        if (removeItems != null) {
                            for (String item : removeItems) {
                                cart.removeItemToCart(item);
                            }//end for item
                            //5: update cart
                            session.setAttribute("CUSTCART", cart);
                        }//end if remove items existed
                    }//end if items is existed
                }//end if cart is existed

            }//end if session is existed

        } finally {
            //call again view cart function
            urlRewritting = "ServiceServlet?btnAction=View Cart";
            response.sendRedirect(urlRewritting);
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
