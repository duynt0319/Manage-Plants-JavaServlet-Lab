<%-- 
    Document   : OrderDetail
    Created on : Oct 25, 2022, 11:25:41 PM
    Author     : Duy
--%>

<%@page import="sample.dto.OrderDetail"%>
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
        <body>
          <%
            String name = (String) session.getAttribute("name");
            if (name == null) {
        %>
        <p><font color='red'>you must login to view personal page</font></p>
        <p></p>
        <%} else {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name%> come back</h3>
            <h3> <a href="mainController?action=logout">Logout</a></h3>
            <h3> <a href="personalPage.jsp">view all orders</a></h3>
        </section>
        <section><!--load all order of the user at here-->
            <%
                String orderid = request.getParameter("orderid");
                if (orderid != null) {
                    int orderID = Integer.parseInt(orderid.trim());
                    ArrayList<OrderDetail> list = OrderDAO.getOrdersDetail(orderID);
                    
                    if (list != null && !list.isEmpty()) {
                        int money = 0;
                        for (OrderDetail detail : list) {%>
            <table class='order'>
                <tr>
                    <td>Order ID</td>
                    <td>Plant Id</td>
                    <td>Plant Name</td>
                    <td>Image</td>
                    <td>Price</td>
                    <td>quantity</td>
                </tr>
                <tr>
                    <td><%= detail.getOrderDetailID()%></td>
                    <td><%= detail.getPlantID()%></td>
                    <td><%= detail.getPlantName()%></td>
                    <td><img src='<%= detail.getImgPath()%>' class = "plantimg" /></td>
                    <td><%= detail.getPriece()%></td>
                    <td><%= detail.getQuantity()%></td>
                    <% money = money + detail.getPriece() * detail.getQuantity(); %>
                </tr>
            </table>
            <%        }%>
            <h3>Total money: <%= money%></h3>
            <% } else {
            %>
            <p>You don't have any order</p>
            <%}
                }%>



        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%}
        %>
    </body>
</html>
