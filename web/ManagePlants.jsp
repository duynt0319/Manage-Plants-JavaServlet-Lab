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
        <header>
            <c:import url="header_loginedAdmin.jsp"></c:import>
        </header>
        
            <form action="mainController" method="post">
                <input type="text" name="txtsearchPlantsByName" placeholder="search by name of plant">
                <input type="submit" value="searchPlants" name="action">
            </form>
            <h1></h1>
            <table class="order">
                <tr>
                    <th> Plant ID</th>
                    <th> Plant name</th>
                    <th> Plant price</th>
                    <th> Plant Image</th>
                    <th> Plant description</th>
                    <th> Plant status</th>
                    <th> Category id</th>
                    <th> Category name</th>
                    <th> Update status</th>
                </tr>


            <c:forEach var="plant" items="${requestScope.plantList}">
                <tr>
                    <td><c:out value="${plant.getId()}"></c:out></td>
                    <td><c:out value="${plant.getName()}"></c:out></td>
                    <td><c:out value="${plant.getPrice()}"></c:out></td>
                    <td><img src="<c:out value="${plant.getImgpath()}"></c:out>" style="width: 100px; height: 100px" /></td>
                    <td><c:out value="${plant.getDescription()}"></c:out></td>
                    <td><c:choose>
                            <c:when test="${plant.getStatus() eq 1}">Available</c:when>
                            <c:otherwise>Not Available</c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:out value="${plant.getCateid()}"></c:out></td>
                    <td><c:out value="${plant.getCatename()}"></c:out></td>
                    <td><c:url var="mylink" value="mainController">
                            <c:param name="plantName" value="${plant.getName()}"></c:param>
                            <c:param name="status" value="${plant.getStatus()}"></c:param>
                            <c:param name="action" value="updateStatusAvailablePlant"></c:param>
                        </c:url>
                        <a href="${mylink}">Available</a>
                    
                        <c:url var="mylink" value="mainController">
                            <c:param name="plantName" value="${plant.getName()}"></c:param>
                            <c:param name="status" value="${plant.getStatus()}"></c:param>
                            <c:param name="action" value="updateStatusNotAvailablePlant"></c:param>
                        </c:url>
                        <a href="${mylink}">Not Available</a>
                    </td>
                </tr>
            </c:forEach>
          
        </table>
            <footer>
                <c:import url="footer_managePlant.jsp"></c:import>
            </footer>
             <% }%>
    </body>
</html>
