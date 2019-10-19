<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 19.10.2019
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Площадка продажи машин</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
    </script>
</head>
<body>
<c:if test="${error !=''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<c:if test="${carUpdate !=''}">
    <div style="background-color: green">
        <c:out value="${carUpdate}"/>
    </div>
</c:if>
<div class="container-fluid">
</div>
<br>
<div class="container">
    <form class="form-inline" action="logout" method="post" id="logout">
        <button type="submit" class="btn btn-primary">Logout</button>
    </form>
    <h2> Список машин на продажу:</h2>
    <table class="table table-bordered" id='table'>
        <thead>
        <tr>
            <th>Фото</th>
            <th>Марка</th>
            <th>Модель</th>
            <th>Пробег</th>
            <th>Цена</th>
            <th>Год выпуска</th>
            <th>Характеристики</th>
            <th>Продается</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allCars}" var="car">
            <tr>
                <td>
                    <img src="${pageContext.request.contextPath}/photo?carId=<c:out value="${car.id}"/>"
                         alt="Фото" width="100%"></td>
                <td><c:out value="${car.manufacturer}"><</c:out></td>
                <td><c:out value="${car.model}"><</c:out></td>
                <td><c:out value="${car.mileage}"><</c:out></td>
                <td><c:out value="${car.price}"><</c:out></td>
                <td><c:out value="${car.productionYear}"><</c:out></td>
                <td><c:out value="${car.carBody}"><</c:out>, объем двигателя: <c:out value="${car.engine}"><</c:out> л,
                    трансмиссия:<c:out
                            value="${car.transmission}"><</c:out>, <c:out value="${car.description}"><</c:out></td>
                <td><c:choose>
                    <c:when test="${car.onSale==true}">
                        Да
                    </c:when>
                    <c:otherwise>
                        Нет
                    </c:otherwise>
                </c:choose>
                </td>
                <td>

                    <form action="update" method="post">
                        <input type="hidden" name="carId" value="${car.id}">
                        <button type="submit" class="btn btn-primary">Изменить статус объявления</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form class="form-inline" action="add" method="get" title="Добавить новое объявление"
          id="formAdd">
        <button type="submit" class="btn btn-primary">Добавить объявление</button>
    </form>
</div>
</body>
</html>
