<%-- 
    Document   : header_loginedAdmin
    Created on : Nov 6, 2022, 10:15:55 PM
    Author     : Duy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="mycss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <ul>
                <li><a href="mainController?action=manageAccounts">Mange Accounts</a></li>
                <li><a href="mainController?action=manageOrders">Mange Orders</a></li>
                <li><a href="mainController?action=managePlants">Mange Plants</a></li>
                <li><a href="mainController?action=managerCategories">Mange categories</a></li>
                <li> Welcome ${sessionScope.name} | <a href="mainController?action=logout">logout</a></li>
            </ul>
        </header>
    </body>
</html>
