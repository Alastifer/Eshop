<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <h1>Product</h1>
        Name = ${product.name}
        <br/><br/> ID = ${product.id}
        <br/><a href="productAdd.do?id=${product.id}">add</a>
        <br/><br/><a href = "/eShop">Main page</a>

        <hr/>
        <h2>Product basket<h2/>
        <ul>
            <c:forEach var="productInBasket" items="${productsBasket}">
                <li>
                    <a href="product.do?id=${productInBasket.key.id}">${productInBasket.key.name}</a>: =
                        ${productInBasket.value} <a href="productDelete.do?id=${productInBasket.key.id}&currentProductID=${product.id}">
                        delete</a>
                </li>
            </c:forEach>
        </ul>

    </body>
</html>