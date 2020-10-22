<%-- 
    Document   : search
    Created on : Oct 12, 2020, 2:53:03 PM
    Author     : ACER
--%>

<%@page import="javax.naming.directory.SearchResult"%>
<%@page import="java.util.List"%>
<%@page import="sonpc.registeration.RegisterationDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <font color="red">
        <h1>
            Welcome, <%= session.getAttribute("USERNAME")%>
        </h1>
        </font>

        <form action="ServiceServlet">
            Search Value: <input type="text" name="txtSearchValue" value="" /><br/>
            <input type="submit" value="Search" name="btnAction"/>
        </form>
        <br/>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null && !searchValue.isEmpty()) {
                List<RegisterationDTO> result = (List<RegisterationDTO>) request.getAttribute("SEARCHRESULT");
                int count = 0;
                if (result != null && !result.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Last Name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (RegisterationDTO dto : result) {
                        String urlRewritting = "ServiceServlet"
                                + "?btnAction=Delete"
                                + "&pk="
                                + dto.getUsername()
                                + "&lastSearchValue="
                                + searchValue;
                %>
            <form action="ServiceServlet">
                <tr>
                    <td><%= ++count%></td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>"/>
                    </td>
                    <td>
                        <input type="text" name="txtPasswordHidden" value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtLastnameHidden" value="<%= dto.getLastname()%>" />
                    </td>
                    <td>
                        <input type="checkbox" name="chkRole" value="ADMIN" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }
                               %>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewritting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btnAction" />
                        <input type="hidden" name="txtLastSearchValue" value="<%= request.getParameter("txtSearchValue")%>"/>
                    </td>
                </tr> 
            </form>  
            <%
                }//end for dto list
            %>
        </tbody>
    </table>
    <%
        return;
    }//end if result != null
    %>
    <h2>No record is matched!!!</h2>
    <%
        return;
        } //end if searchValue != null
    %>
    <h2>No record is matched!</h2>


</body>
</html>
