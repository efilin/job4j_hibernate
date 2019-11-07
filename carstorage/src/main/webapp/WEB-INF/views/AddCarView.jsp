<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 23.10.2019
  Time: 15:53
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
            if (!$('#manufacturer').val()) {
                alert($('#manufacturer').attr('title'));
            } else if (!$('#model').val()) {
                alert($('#model').attr('title'));
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
    <h2> Добавить новое объявление</h2>
    <form class="form form-horizontal" action="add" method="post"
          enctype="multipart/form-data" id="formAdd">
        <div class="form-group">
            <div class="col-md-3">
                <label for="manufacturer">Марка машины:</label>
                <input type="text" class="form-control" name="manufacturer" title="Введите марку машины"
                       id="manufacturer">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="model">Модель машины:</label>
                <input type="text" class="form-control" name="model" title="Введите модель машины" id="model">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="mileage">Пробег, км</label>
                <input type="text" class="form-control" name="mileage" title="Введите пробег машины" id="mileage">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="price">Цена машины:</label>
                <input type="text" class="form-control" name="price" title="Введите цену машины" id="price">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="productionYear">Год выпуска машины:</label>
                <input type="text" class="form-control" name="productionYear" title="Введите год выпуска машины"
                       id="productionYear">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="carBody">Выберите тип кузова:</label>
                <select class="form-control" name="carBody" id="carBody">
                    <option value="Sedan">Sedan</option>
                    <option value="Hatchback">Hatchback</option>
                    <option value="Universal">Universal</option>
                    <option value="Crossover">Crossover</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="engine">Выберите объём двигателя:</label>
                <select class="form-control" name="engine" id="engine">
                    <option value="1.6">1.6</option>
                    <option value="1.8">1.8</option>
                    <option value="2.0">2.0</option>
                    <option value="2.5">2.5</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="transmission">Выберите тип трансмиссии:</label>
                <select class="form-control" name="transmission" id="transmission">
                    <option value="Manual">Manual</option>
                    <option value="Automatic">Automatic</option>
                    <option value="DSG">DSG</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="photoFile">Фото автомобиля</label>
                <input type="file" class="form-control-file" name="photoFile" id="photoFile" >
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="description">Описание:</label>
                <textarea class="form-control" title="Описание" name="description" id="description"></textarea>
            </div>
        </div>
        <button type="button" class="btn btn-primary" onclick="checkInput()">Добавить объявление</button>
    </form>
</div>
</body>
</html>
