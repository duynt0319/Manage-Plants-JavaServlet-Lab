<%-- 
    Document   : processingOrders
    Created on : Nov 8, 2022, 12:49:21 AM
    Author     : Duy
--%>

<%@page import="sample.dto.Account"%>
<%@page import="sample.dao.AccountDao"%>
<%@page import="sample.dto.Order"%>
<%@page import="sample.dao.OrderDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <p><font color="red">you must login to see complete list order</p>
        <p></p>
        <%} else {%>

        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name%> come back</h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
        </section>
        <section> <!--load all orders of the user at here-->
            <%
                ArrayList<Order> list = OrderDAO.getOrdersByStatus(email, 1);
                String[] status = {"", "processing", "completed", "canceled"};
                if (list != null && !list.isEmpty()) {
                    for (Order ord : list) {
            %>
            <table class="order">
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's Status</td><td>action</td></tr>
                <tr>
                    <td><%= ord.getOrderID()%></td>
                    <td><%= ord.getOrderDate()%></td>
                    <td><%= ord.getShipDate()%></td>
                    <td><%= status[ord.getStatus()]%>
                        <br/><% if (ord.getStatus() == 3) {%>
                        <a href="mainController?action=orderagainOrder&againID=<%= ord.getOrderID()%>">Order again</a>
                        <% } %>
                        <br/><% if (ord.getStatus() == 1) {%>
                        <a href="mainController?action=cancelOrder&cancelID=<%= ord.getOrderID()%>" >cancel order</a>
                        <% }%>
                    </td>
                    <td><a href="orderDetail.jsp?orderid= <%= ord.getOrderID()%>">detail</a></td>
                </tr>
            </table>
            <%}
            } else {
            %>
            <p>You don't have any order</p>
            <% }%>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <% }%>
    </body>
</html>
