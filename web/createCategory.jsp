<%-- 
    Document   : createCategory
    Created on : Nov 10, 2022, 3:05:10 AM
    Author     : Duy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="mycss.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <form action="mainController" class="formregister">
            <header>
            <%@include file="header_loginedAdmin.jsp" %>
        </header>
                <h1>Add new category</h1>
                <table>

                   
                    <tr>
                        <td>Cate name</td>
                        <td><input type="text" name="txtcatename" required=""></td>
                    </tr>
                   
                    <tr>
                        <td colspan="2"><input type="submit" value="createCategory" name="action"></td>
                    </tr>
                </table>
            </form>
    </body>
</html>
