<%-- 
    Document   : ManageAccounts
    Created on : Nov 5, 2022, 2:35:25 AM
    Author     : tuank
--%>

<%@page import="sample.dto.Account"%>
<%@page import="sample.dao.AccountDao"%>
<%@page import="sample.dao.AccountDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
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
        
            <section>
                <form action="mainController" method="post">
                    <input type="text" name="txtsearchCategoryByName" placeholder="search by name of category">
                    <input type="submit" value="searchCategories" name="action">
                </form>
                <h1></h1>
                <table class="order">
                    <tr><th> Category ID</th>
                        <th> Category Name</th>
                    </tr>
                <c:forEach var="cate" items="${requestScope.cateList}" >
                    <tr><td><c:out value="${cate.getCateID()}"></c:out></td>
                        <td><c:out value="${cate.getCateName()}"></c:out></td>
                        </tr>
                </c:forEach>
                </table>
            </section>
        
        <footer>
            <c:import url="footer_manageCategort.jsp"></c:import>
        </footer>
<% }%>
    </body>
</html>
