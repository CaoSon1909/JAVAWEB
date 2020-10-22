/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonpc.cart.CartObject;
import sonpc.book.BookDAO;

/**
 *
 * @author ACER
 */
public class CheckOutCartServlet extends HttpServlet {

    private final String SHOW_ITEM_PAGE = "ShowItem.jsp";
    private final String SHOPPING_PAGE = "shopping.html";

    /**
     * ;
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
        String url = SHOW_ITEM_PAGE;
        try {
            //1: Cust goes to his/her cart place
            //phải kiểm tra xem chỗ để giỏ còn mở cửa hay ko, lỡ hết thời gian time-out nó đóng cửa thì làm sao lấy đc giỏ?
            HttpSession session = request.getSession(false);
            //kiểm tra xem chỗ để giỏ còn mở cửa ko?
            if (session != null) { //nếu như còn mở cửa
                //2: Cust lấy giỏ của mình
                CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                //nếu như giỏ còn tồn tại (vì nếu chỗ để giỏ đóng cửa thì giỏ cũng sẽ bị hủy theo)
                if (cart != null) {
                    //3: Cust lấy đồ của mình trong giỏ
                    Map<String, Integer> items = cart.getItems();
                    //kiểm tra xem có đồ còn hay mất?
                    if (items != null) {
                        //4: Insert toàn bộ items xuống DB => call dao
                        int quantity = 0;
                        String bookTitle = "";
                        for (String itemKey : items.keySet()) {
                            bookTitle = itemKey;
                            quantity = items.get(itemKey);

                            BookDAO dao = new BookDAO();

                            boolean result = dao.updateBookSold(bookTitle, quantity);

                            if (result) {
                                //5: xóa giỏ hàng => invalidate session
                                session.invalidate();
                                //6: tiếp tục shopping
                                url = SHOPPING_PAGE;
                            }
                        }

                    }//end if items existed
                }//end if cart existed
            } //end if session existed

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckOutCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CheckOutCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
