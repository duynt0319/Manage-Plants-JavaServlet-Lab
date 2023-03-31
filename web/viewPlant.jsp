<%-- 
    Document   : viewPlant
    Created on : Oct 19, 2022, 5:46:50 PM
    Author     : nguye
--%>

<%@page import="com.sun.swing.internal.plaf.basic.resources.basic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <jsp:useBean id="plantObj" class="sample.dto.Plant" scope="request" />
        <table>
            <h3><font style="color: #006666">View Plant</h3>
            <tr>
                <td rowspan="8"><img src="<jsp:getProperty name="plantObj" property="imgpath"></jsp:getProperty>" class="planting"></td>
                </tr>
                <tr>
                    <td>id:<jsp:getProperty name="plantObj" property="id"></jsp:getProperty></td>

                <tr>
                    <td>product name:<jsp:getProperty name="plantObj" property="name"></jsp:getProperty></td>
                </tr>
                <tr>
                    <td>price:<jsp:getProperty name="plantObj" property="price"></jsp:getProperty></td>
                </tr>
                <tr>
                    <td>description:<jsp:getProperty name="plantObj" property="description"></jsp:getProperty></td>
                </tr>
                <tr>
                    <td>status:<jsp:getProperty name="plantObj" property="status"></jsp:getProperty></td>
                </tr>
                <tr>
                    <td>cate id:<jsp:getProperty name="plantObj" property="cateid"></jsp:getProperty></td>
                </tr>
                <tr>
                    <td>category:<jsp:getProperty name="plantObj" property="catename"></jsp:getProperty></td>
                </tr>
            </table>

            <!--su dung EL-->
            <table>
                <tr>
                    <td rowspan="8"><img src="${plantObj.imgpath}" class="planting"></td>
            </tr>
            <tr>
                <td>id:${plantObj.id}</td>

            <tr>
                <td>product name:${plantObj.name}</td>
            </tr>
            <tr>
                <td>price:${plantObj.price}</td>
            </tr>
            <tr>
                <td>description:${plantObj.description}</td>
            </tr>
            <tr>
                <td>status:${plantObj.status}</td>
            </tr>
            <tr>
                <td>cate id:${plantObj.cateid}</td>
            </tr>
            <tr>
                <td>category:${plantObj.catename}</td>
            </tr>
        </table>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
