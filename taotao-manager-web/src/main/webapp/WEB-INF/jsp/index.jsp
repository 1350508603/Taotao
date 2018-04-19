<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 2018-04-18
  Time: 7:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Mcdapao</title>
</head>
<body>
<h1>老子只想搞钱</h1>
        <c:forEach var="los"  items="${item}">
                ${los.price}
        </c:forEach>
</body>
</html>
