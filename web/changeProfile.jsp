<%-- 
    Document   : changeProfile
    Created on : Nov 7, 2022, 10:21:53 PM
    Author     : Duy
--%>

<%@page import="sample.dao.AccountDao"%>
<%@page import="sample.dto.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>

        <%
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie cookie : c) {
                    if (cookie.getName().equals("selector")) {
                        token = cookie.getValue();
                        Account acc = AccountDao.getAccountToken(token);
                        if (acc != null) {
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;
                        }
                    }
                }
            } else {
                login = true;
            }
            if (!login) {
        %>
        <p><font color="red">you must login to change your profile</p>
        <p></p>
        <%} else {%>

        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>

        <p>Hello <%= email%></p>
        <p><a href="mainController?action=logout">Logout</a></p>
        <form action="mainController" method="post">
            <table>
                <tr><td>Enter new full name:</td><td><input type="text" name="newfullname" required=""></td></tr>
                <tr><td>Enter new phone</td><td><input type="text" name="newphone" required=""></td></tr>
                <tr><td colspan="2"><input type="submit" value="changeProfile" name="action"></td></tr>
            </table>
        </form>
        <% if (session.getAttribute("updateprofile") != null) {
        %>
        <p><%= session.getAttribute("updateprofile")%></p>
        <%
           }
           }%>

        <footer>
            <%@include file="footer.jsp" %>
        </footer>

    </body>
</html>
