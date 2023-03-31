<%-- 
    Document   : header_loginedUser
    Created on : Oct 22, 2022, 2:56:05 PM
    Author     : Duy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="changeProfile.jsp">Change Profile</a></li>
                <li><a href="completeOrders.jsp">Completed Orders</a></li>
                <li><a href="canceledOrders.jsp">Canceled Orders</a></li>
                <li><a href="processingOrders.jsp">Processing orders</a></li>
                <form action="mainController" method="post">
                    <li>from<input type="date" name="from"> to <input type="date" name="to">
                        <input type="submit" name="action" value="filterOrders">
                    </li>
                </form>

            </ul>
        </nav>
    </body>
</html>
