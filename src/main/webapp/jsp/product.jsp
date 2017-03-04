<html>
    <body>
        <h1>Product</h1>
        Name = ${product.name}
        <br/><br/> ID = ${product.id}
        <br/><a href=<%="productAdd.do?id=" + request.getParameter("id")%>>add</a>
        <br/><br/><a href = "/eShop">Main page</a>
    </body>
</html>