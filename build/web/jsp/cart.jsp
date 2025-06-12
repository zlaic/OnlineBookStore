<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Bookshop</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>

    <body>
        <h1>The following items are in your shopping cart</h1>

        <form name="form1" method="post" action="./books">
            <input type="hidden" name="action" value="update_cart">

            <table>
                <thead>
                    <tr>
                        <th>ISBN</th>
                        <th>Title</th>
                        <th>Price/unit</th>
                        <th>Quantity</th>
                        <th>Subtotal</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty sessionScope.cart}">
                        <c:set var="totalCost" value="0.0" scope="page" />
                        <c:forEach var="entry" items="${sessionScope.cart}">
                            <c:set var="isbn" value="${entry.key}" />
                            <c:set var="item" value="${entry.value}" />
                            <c:set var="book" value="${item.book}" />
                            <c:set var="cost" value="${item.orderCost}" />
                            <tr>
                                <td>${isbn}</td>
                                <td>${book.title}</td>
                                <td>${book.price}</td>
                                <td>
                                    <input type="text" name="${isbn}" size="2" value="${item.quantity}" maxlength="4" />
                                </td>
                                <td><fmt:formatNumber value="${cost}" type="currency" currencySymbol=""/></td>
                                <td align="center">
                                    <input type="checkbox" name="remove" value="${isbn}" />
                                </td>
                            </tr>
                            <c:set var="totalCost" value="${totalCost + cost}" scope="page"/>
                        </c:forEach>
                        <tr>
                            <td colspan="4">
                                <input type="submit" name="Submit" value="Update Cart" />
                            </td>
                            <td colspan="2" align="right">
                                <b>ORDER TOTAL: $<fmt:formatNumber value="${totalCost}" type="number" minFractionDigits="2" /></b>
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </form>

        <div class="link-container">
            <p><a href="./books?action=continue">Continue Shopping</a></p>
            <p><a href="./books?action=checkout">Check Out</a></p>
        </div>
    </body>
</html>