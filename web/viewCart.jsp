<%-- 
    Document   : viewCart
    Created on : Oct 26, 2022, 4:21:24 PM
    Author     : Duy
--%>

<%@page import="sample.dao.PlantDAO"%>
<%@page import="sample.dto.Plant"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="mycss.css" type="text/css" />
</head>
<header>
    <%@include file="header.jsp" %>
</header>
<body>
    <section>
        <c:if test="${not empty sessionScope.name}">
            <h3>Welcome ${sessionScope.name} come back</h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
            <h3><a href="personalPage.jsp">Personal page</a></h3>
        </c:if>
        <font style="color: red;">
            <c:out value="${not empty requestScope.WARNING ? requestScope.WARNING : ''}"/>
        </font>

        <table width="100%" class="shopping">
            <tr>
                <td>Product id</td>
                <td>Quantity</td>
                <td>Image</td>
                <td>Price</td>
                <td>Action</td>
            </tr>
            <c:set var="cart" value="${sessionScope.cart}"/>
            <c:set var="money" value="0"/>
            <c:if test="${not empty cart}">
                <c:forEach var="pid" items="${cart.keySet()}">
                    <c:set var="quantity" value="${cart[pid]}"/>
                    <c:set var="plant" value="${PlantDAO.getPlant(pid)}"/>
                    <form action="mainController" method="post">
                        <tr>
                            <td><input type="hidden" value="${pid}" name="pid"><a href="getPlantServlet?pid=${pid}">${pid}</a></td>
                            <td><input type="number" value="${quantity}" name="quantity"></td>
                            <td><img src="${plant.imgpath}" style="width:50px; height: 50px"></td>
                            <td><c:out value="${plant.price}"/></td>
                            <c:set var="money" value="${money + quantity * plant.price}"/>
                            <td>
                                <input type="submit" value="update" name="action">
                                <input type="submit" value="delete" name="action">
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </c:if>
            <c:if test="${empty cart}">
                <tr><td>Your cart is empty</td></tr>
            </c:if>
            <tr><td>Total money:<c:out value="${money}"/></td></tr>
            <tr><td>Order date: <fmt:formatDate value="${now}" pattern="EEE MMM dd HH:mm:ss zzz yyyy"/></td></tr>
            <tr><td>Ship date: N/A </td></tr>
        </table>
    </section>
    <section>
        <form action="mainController" method="post">
            <input type="submit" value="saveOrder" name="action" class="submitorder">
        </form>
    </section>
    <footer>
        <%@include file="footer.jsp" %>
    </footer>
</body>
</html>
