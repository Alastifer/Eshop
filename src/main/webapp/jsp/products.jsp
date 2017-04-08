<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <h1>Products list</h1>
        <ul>
            <c:forEach var="product" items="${products}">
                <li><a href="product.do?id=${product.id}">${product.name}</a></li>
            </c:forEach>
        </ul>
    </body>
</html>