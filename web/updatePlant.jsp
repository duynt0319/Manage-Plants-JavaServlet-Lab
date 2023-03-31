<%-- 
    Document   : updatePlant
    Created on : Nov 10, 2022, 2:35:52 AM
    Author     : Duy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <%@include file="header_loginedAdmin.jsp" %>
        </header>
        <section>
            <form action="mainController" class="formregister">
                <h1>Update plant by Plant ID</h1>
                <table>
                    <tr>
                        <td>input Plant ID here</td>
                        <td><input type="text" name="txtpid" required=""></td>
                    </tr>

                    <tr>
                        <td>Name of plant</td>
                        <td><input type="text" name="txtname" required=""></td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><input type="text" name="txtprice" required=""></td>
                    </tr>
                    <tr>
                        <td>Image</td>
                        <td><input type="text" name="txtimg"></td>
                    </tr>

                    <tr>
                        <td>Description</td>
                        <td><input type="text" name="txtdescription" required=""></td>
                    </tr>
                    <tr><td>Status</td>
                        <td>
                            <select name="txtsatus">
                                <option value="1">Stock</option>
                                <option value="2">Out of Stock</option>
                        </td>
                    </tr> 
                    <tr><td>Categories ID</td>
                        <td><input type="text" name="txtcateID" placeholder="ID from 1 to 3"></td>
                    </tr>

                    <tr>
                        <td colspan="2"><input type="submit" value="updatePlant" name="action"></td>
                    </tr>
                </table>
            </form>
            
        </section>

    </body>
</html>
