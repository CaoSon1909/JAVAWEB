<%-- 
    Document   : register
    Created on : Oct 21, 2020, 2:55:33 PM
    Author     : ACER
--%>

<%@page import="sonpc.registeration.RegisterationInsertError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Account</title>
    </head>
    <body>
        <h1>Register Page</h1>
        <form action="ServiceServlet" method="POST">
            Username:* <input type="text" name="txtUsername" value="" />(6-20 characters)<br/>
            <font color="red">
            <%
                RegisterationInsertError error
                        = (RegisterationInsertError) request.getAttribute("INSERTERROR");
                if (error != null) {
                    if (error.getUsernameLengthErr() != null) {
            %>
            <%= error.getUsernameLengthErr()%>
            <%
                    }
                    {

                    }
                }
            %>
            </font> <br/>
            Password:* <input type="password" name="txtPassword" value="" />(6-20 characters)<br/>
            <font color="red">
            <%
                if (error != null) {
                    if (error.getPasswordLengthErr() != null) {
            %>
            <%= error.getPasswordLengthErr()%>
            <%
                    }
                }
            %>
            </font> <br/>
            Confirm:* <input type="password" name="txtConfirm" value="" /><br/>
            <font color="red">
            <%
                if (error != null) {
                    if (error.getConfirmNotMatch() != null) {
            %>
            <%= error.getConfirmNotMatch()%>
            <%
                    }
                }
            %>
            </font> <br/>
            Full name: <input type="text" name="txtFullName" value="" /> (2-50 characters)<br/>
            <font color="red">
            <%
                if (error != null) {
                    if (error.getFullNameLengthErr() != null) {
            %>
            <%= error.getFullNameLengthErr()%>
            <%
                    }
                }
            %>
            </font> <br/>
            <input type="submit" value="Register" name="btnAction" />
        </form> <br/> <br/>
        <%
            if (error != null) {
                if (error.getUsernameIsExisted() != null) {
        %>
        <%= error.getUsernameIsExisted()%>
        <%
                }
            }
        %>
    </body>
</html>
