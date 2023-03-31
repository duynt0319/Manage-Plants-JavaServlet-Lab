<%-- 
    Document   : ManageAccounts
    Created on : Nov 5, 2022, 2:35:25 AM
    Author     : tuank
--%>

<%@page import="sample.dto.Account"%>
<%@page import="sample.dao.AccountDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="mycss.css" rel="stylesheet" type="text/css">
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
        <c:import url="header_loginedAdmin.jsp"></c:import>
            <form action="mainController" method="post">
                <input type="text" name="orderUserName" placeholder="search by name of Customer">
                <input type="submit" value="searchOrder" name="action">
            </form>
            <h1></h1>
            <table class="order">
                <tr>
                    <th>Order ID</th>
                    <th>Order date</th>
                    <th>Ship date</th>
                    <th>status</th>
                    <th>Customer's ID</th>
                    <th>Customer's Name</th>
                    <th>Action</th>
                </tr>

            <c:forEach var="order" items="${requestScope.orderList}">
                <tr>
                    <td><c:out value="${order.getOrderID()}"></c:out></td>
                    <td><c:out value="${order.getOrderDate()}"></c:out></td>
                    <td><c:out value="${order.getShipDate()}"></c:out></td>
                    <td><c:choose>
                            <c:when test="${order.getStatus() eq 1}">Processing</c:when>
                            <c:when test="${order.getStatus() eq 2}">Completed</c:when>
                            <c:otherwise>Canceled</c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:out value="${order.getAccID()}"></c:out></td> 
                    <td><c:out value="${order.getFullname()}"></c:out></td> 

                        <td>
                            <c:url var="mylink" value="mainController">
                                <c:param name="orderID" value="${order.getOrderID()}"></c:param>
                                <c:param name="status" value="${order.getStatus()}"></c:param>
                                <c:param name="action" value="confirmOrder"></c:param>
                            </c:url>
                            <a href="${mylink}"> Confirm Order</a>
                        </td>   
                        <td>
                            <c:url var="mylink" value="mainController">
                                <c:param name="orderID" value="${order.getOrderID()}"></c:param>
                                <c:param name="status" value="${order.getStatus()}"></c:param>
                                <c:param name="action" value="cancelOrderManage"></c:param>
                            </c:url>
                            <a href="${mylink}"> Cancel Order</a>
                        </td> 
                    </tr>
            </c:forEach>
        </table>
            <% }%>
    </body>
</html>
