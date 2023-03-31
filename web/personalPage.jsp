<%-- 
    Document   : personalPage
    Created on : Oct 22, 2022, 2:57:06 PM
    Author     : Duy
--%>

<%@page import="sample.dao.AccountDao"%>
<%@page import="sample.dto.Account"%>
<%@page import="sample.dao.OrderDAO"%>
<%@page import="sample.dto.Order"%>
<%@page import="java.util.ArrayList"%>
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
                for (Cookie aCookie : c) {
                    if (aCookie.getName().equals("selector")) {
                        token = aCookie.getValue();
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
            if (login) {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h3> Welcome <%= name%> come back</h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
        </section>
        <section>
            <!--load all order of the user at here -->
            <%
                ArrayList<Order> list = OrderDAO.getOrders(email);
                String[] status = {"", "processing", "completed", "canceled"};

                if (request.getAttribute("orderListFilterByDate") != null) {
                    list = (ArrayList<Order>) request.getAttribute("orderListFilterByDate");
                }
                String from = request.getParameter("from");
                String to = request.getParameter("to");
                if (list.isEmpty() || list == null) {
                    if (from != null && to != null) {
            %>
            <p>We don't have any order from <%=from%> to <%=to%>   !</p>
            <% }
                }
                if (list != null && !list.isEmpty()) {
                        for (Order ord : list) {
            %>
            <table class="order">
                <tr>
                    <td>Order ID</td>
                    <td>Order Date</td>
                    <td>Ship Date</td>
                    <td>Order's status</td>
                    <td>Action</td>
                </tr>
                <tr>
                    <td><%= ord.getOrderID()%></td>
                    <td><%= ord.getOrderDate()%></td>
                    <td><%= ord.getShipDate()%></td>
                    <td><%= status[ord.getStatus()]%>
                        <br/><% if (ord.getStatus() == 3) {%>
                            <a href="mainController?action=orderagainOrder&againID=<%= ord.getOrderID()%>">Order again</a>
                            <% } %>
                            <br/><% if (ord.getStatus() == 1) {%>
                            <a href="mainController?action=cancelOrder&cancelID=<%= ord.getOrderID()%>">cancel order</a>
                            <% }%>
                    </td>
                    <td><a href="orderDetail.jsp?orderid=<%= ord.getOrderID() %>">detail</a> </td>
                </tr>
            </table>
            <%}
            } else {
            %>
            <p>You don't have any order</p>
            <%}%>


        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%} else {%>
        <p><font color="red">you must login to before go to this page</p>
            <%}%>
    </body>
</html>
