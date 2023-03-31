<%-- 
    Document   : registration
    Created on : Oct 21, 2022, 4:16:31 PM
    Author     : Duy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />

    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <form action="mainController" method="post" class="formregister">
                <h1>REGISTER</h1>
                <table>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="txtemail" required="" pattern="(\\w)+@(a-zA-Z]+([.](\\w)+){1,2}"></td>
                    </tr>
                    <tr>
                        <td>Full Name</td>
                        <td><input type="text" name="txtfullname" required=""></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="txtpassword" required=""></td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                         <td>
                            <input type="text" name="txtphone" required="" value="<%= (request.getAttribute("txtphone") == null) ? "" : request.getAttribute("txtphone") %>">
                            <%= (request.getAttribute("ERROR") == null) ? "" : request.getAttribute("ERROR") %>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="register" name="action"></td>
                    </tr>
                </table>
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
