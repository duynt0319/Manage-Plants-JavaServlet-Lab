<%-- 
    Document   : ManageAccounts
    Created on : Nov 6, 2022, 10:43:06 PM
    Author     : Duy
--%>

<%@page import="sample.dto.Account"%>
<%@page import="sample.dao.AccountDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="mycss.css" rel="stylesheet" type="text/css"/>
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
            <input type="text" name="txtsearchByName" placeholder="search by name of user">
            <input type="submit" value="searchAccount" name="action" >
        </form>
        <h1></h1>
        <table class="order">
            <tr>
                <th> ID User</th>
                <th> Email</th>
                <th> Pass Word</th>
                <th> Full Name</th>
                <th> Status</th>
                <th> Phone</th>
                <th> Role</th>
                <th> Action</th>
            </tr>
        <c:forEach var="acc" items="${requestScope.accountList}">
            <tr>
                <td><c:out value="${acc.getAccID()}"> </c:out></td>
                <td><c:out value="${acc.getEmail()}"> </c:out></td>
                <td><c:out value="${acc.getPassword()}"> </c:out></td>
                <td><c:out value="${acc.getFullname()}"> </c:out></td>
                <td><c:choose>
                        <c:when test="${acc.getStatus() eq 1}"> Active</c:when>
                        <c:otherwise>InActive</c:otherwise>
                </c:choose>                  
                </td>
                <td><c:out value="${acc.getPhone()}"> </c:out></td>
                <td><c:choose>
                        <c:when test="${acc.getRole() eq 1}"> Admin </c:when>
                        <c:otherwise> User </c:otherwise>
                </c:choose>                  
                </td>
                <td><c:if test="${acc.getRole() eq 0}">
                        <c:url var="mylink" value="mainController">
                            <c:param name="email" value="${acc.getEmail()}"></c:param>
                            <c:param name="status" value="${acc.getStatus()}"></c:param>
                            <c:param name="action" value="updateStatusAccountBlock"></c:param>
                        </c:url>
                        <a href="${mylink}">Block</a>
                        
                         <c:url var="mylink" value="mainController">
                            <c:param name="email" value="${acc.getEmail()}"></c:param>
                            <c:param name="status" value="${acc.getStatus()}"></c:param>
                            <c:param name="action" value="updateStatusAccountUnBlock"></c:param>
                        </c:url>
                        <a href="${mylink}">UnBlock</a>
                </c:if>
                    
                </td>
            </tr>
            
        </c:forEach>
            
        </table>
        <% }%>
    </body>
</html>
