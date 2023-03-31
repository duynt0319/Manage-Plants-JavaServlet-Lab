<%-- 
    Document   : header
    Created on : Oct 21, 2022, 3:25:41 PM
    Author     : Duy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Header</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, inittial-scale = 1.0">
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <header>
            <nav>
                <ul>
                  
                    
                    <li><a href=""><img src="images/logo.jpg" id="logo"></a></li>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="registration.jsp">Register</a></li>
                    <li><a href="login.jsp">Login</a></li>
                    <li><a href="mainController?action=viewcart">View cart</a></li>
                    
                    <li><form action="mainController" method="post" class="formsearch">
                            <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch")==null)?"":request.getParameter("txtsearch")%>">
                            <select name="searchby"><option value="byname">By Name</option>
                                                    <option value="bycate">By Category</option>
                            </select>
                            <input type="submit" value="search" name="action">
                        </form></li>
                </ul>
            </nav>
        </header>
    </body>
</html>
