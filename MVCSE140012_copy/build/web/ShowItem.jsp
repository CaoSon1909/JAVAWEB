<%-- 
    Document   : ShowItem
    Created on : Oct 16, 2020, 9:45:15 AM
    Author     : ACER
--%>

<%@page import="java.util.Map"%>
<%@page import="sonpc.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Market</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <%
            //1: Cust goes to cart place
            if (session != null) {
                //2: Cust take cart
                CartObject cart = (CartObject) session.getAttribute("CUSTCART");
                if (cart != null) {
                    //3: check items are existed
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <form action="ServiceServlet">
                <tbody>
                    <%
                        int count = 0;
                        for (String itemKey : items.keySet()) {
                    %>
                    <tr>
                        <td>
                            <%= ++count%>
                        </td>
                        <td>
                            <%= itemKey%>
                        </td>
                        <td>
                            <%= items.get(itemKey)%>
                        </td>
                        <td>
                            <input type="checkbox" name="chkItem" 
                                   value="<%= itemKey%>" />
                        </td>
                    </tr>
                    <%
                        }//end for itemKey
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="shopping.html">Add More Books to Your Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Selected Items" name="btnAction" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Check Out" name="btnAction" />
                        </td>
                    </tr>
                </tbody>
            </form>
        </table>


        <%
                        return;
                    }
                }//end if cart is existed
            }//end if session is existed

        %>
        <h2>No cart is existed</h2>
    </body>
</html>
