<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 23.10.2019
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Площадка продажи машин</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function checkInput() {
            if (validate()) {
                $('#formAdd').submit();
            }
        }

        function validate() {
            var result = false;
            if (!$('#name').val()) {
                alert($('#name').attr('title'));
            } else if (!$('#login').val()) {
                alert($('#login').attr('title'));
            } else if (!$('#password').val()) {
                alert($('#password').attr('title'));
            } else if (!$('#phone').val()) {
                alert($('#phone').attr('title'));
            } else {
                result = true;
            }
            return result;
        }
    </script>
</head>
<body>
<div class="container-fluid">
</div>
<br>
<div class="container">
    <h2> Добавить нового пользователя</h2>
    <form class="form form-horizontal" action="addseller" method="post"
          id="formAdd">
        <div class="form-group">
            <div class="col-md-3">
                <label for="name">Имя:</label>
                <input type="text" class="form-control" name="name" title="Введите имя подльзователя"
                       id="name">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="login">Login:</label>
                <input type="text" class="form-control" name="login" title="Введите login пользователя" id="login">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="password">Пароль:</label>
                <input type="password" class="form-control" name="password" title="Введите пароль пользователя"
                       id="password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="phone">Номер телефона пользователя:</label>
                <input type="text" class="form-control" name="phone" title="Введите номер телефона пользователя"
                       id="phone">
            </div>
        </div>
        <button type="button" class="btn btn-primary" onclick="checkInput()">Добавить пользователя</button>
    </form>
</div>
</body>
</html>
