<%@ page import="table.TableData" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Лабораторная 2</title>
    <style>
        body, html {
            height: 100%;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body, html {
            height: 100%;
            font-family: Arial, sans-serif;
        }

        header {
            background-color: blueviolet;
            color: white;
            padding: 20px;
            text-align: center;
            font-size: 24px;
            top: 0;
            left: 0;
            width: 100%;
            z-index: 100;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .header p {
            margin: 5px 0;
            font-size: 18px;
        }

        .main-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
            margin-top: 20px;
        }
        .graph-container {
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
            width: 500px;
            height: 500px;
            margin: 0 auto;

        }

        .content {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 800px;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        form {
            margin: 20px 0;
        }

        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }

        input[type="text"] {
            padding: 5px;
            width: 50px;
            margin-top: 5px;
        }

        .x-button, .radius-button {
            padding: 10px 20px;
            margin: 5px;
            border: none;
            background-color: #3498db;
            color: white;
            cursor: pointer;
            border-radius: 5px;
        }

        .x-button.selected, .radius-button.selected {
            background-color: #2ecc71;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: blueviolet;
            color: white;
            border: none;
            cursor: pointer;
            margin-top: 20px;
            border-radius: 5px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
            padding: 10px;
        }

        th {
            background-color: blueviolet;
            color: white;
        }

        td {
            text-align: center;
        }

        img {
            display: block;
            width: 100%;
            height: auto;
            padding: 0;
            margin: 0;
            border: none;
        }


        .dot {
            position: absolute;
            width: 10px;
            height: 10px;
            border-radius: 50%;
        }
    </style>

    <script type="text/javascript">
        function handleClick(event) {
            var radius = parseFloat(document.getElementById("radius_hidden").value);
            if (isNaN(radius) || radius <= 0) {
                alert("Пожалуйста, выберите радиус перед кликом по плоскости.");
                return;
            }

            var img = event.target; // Получаем элемент изображения
            var rect = img.getBoundingClientRect(); // Получаем размеры и положение изображения на странице

            // Получаем точные координаты клика относительно изображения
            var clickX = event.clientX - rect.left;
            var clickY = event.clientY - rect.top;

            // Преобразуем координаты клика в координаты графика
            var x = ((clickX / rect.width) * 2 - 1) * radius;
            var y = ((1 - (clickY / rect.height) * 2)) * radius;

            console.log("Click at X: " + clickX + ", Y: " + clickY);
            console.log("Transformed X: " + x.toFixed(2) + ", Transformed Y: " + y.toFixed(2));

            // Устанавливаем вычисленные значения в форму
            document.getElementById('x_value').value = x.toFixed(2);
            document.getElementById('y_value').value = y.toFixed(2);

            // Устанавливаем флаг, что был клик по изображению
            document.getElementById('image_click').value = "true";

            // Отправляем форму для обработки на сервере
            document.getElementById('coords_form').submit();
        }


        function validateForm() {
            var x = document.getElementById("x_value").value;
            var y = document.getElementById("y_value_input").value || document.getElementById("y_value").value;
            var radius = document.getElementById("radius_hidden").value;

            if (isNaN(y) || y < -5 || y > 3) {
                alert("Y должно быть числом от -5 до 3.");
                return false;
            }
            if (!y) {
                alert("Пожалуйста, выберите Y.");
                return false;
            }

            if (!x) {
                alert("Пожалуйста, выберите X.");
                return false;
            }

            if (!radius) {
                alert("Пожалуйста, выберите радиус.");
                return false;
            }

            document.getElementById('y_value').value = y;

            return true;
        }
        function setR() {
            let selectedValue = document.getElementById("r").value;
            document.getElementById("radius_hidden").value = selectedValue;
            document.querySelectorAll('.radius-button').forEach(btn => btn.classList.remove('selected'));
            document.getElementById("r" + selectedValue).classList.add('selected');
        }

        function setX(value) {
            document.getElementById("x_value").value = value;
            document.querySelectorAll('.x-button').forEach(btn => btn.classList.remove('selected'));
            document.getElementById("x" + value).classList.add('selected');
        }
    </script>
</head>
<body>

<header>
    <div class="header">
        <p>Смородин Владислав Вадимович</p>
        <p>Группа: Р3222</p>
        <p>Номер варианта: 12202</p>
        <div id="date-time">
            <span id="date"></span>, <span id="time"></span>
        </div>
    </div>
</header>

<div class="main-container">
    <div class="content">
        <h2>Проверка попадания точки в область</h2>

        <form id="coords_form" name="coords_form" action="ControllerServlet" method="POST" accept-charset="UTF-8" onsubmit="return validateForm();">
            <input type="hidden" id="image_click" name="image_click" value="false">
            <label>Изменение X:</label>
            <div>
                <input type="checkbox" name="x[]" class="x-button" id="x-4" onclick="setX(-4)">-4</input>
                <input type="checkbox" name="x[]" class="x-button" id="x-3" onclick="setX(-3)">-3</input>
                <input type="checkbox" name="x[]" class="x-button" id="x-2" onclick="setX(-2)">-2</input>
                <input type="checkbox" name="x[]" class="x-button" id="x-1" onclick="setX(-1)">-1</input>
                <input type="checkbox" name="x[]" class="x-button" id="x0" onclick="setX(0)">0</input>
                <input type="checkbox" name="x[]" class="x-button" id="x1" onclick="setX(1)">1</input>
                <input type="checkbox" name="x[]" class="x-button" id="x2" onclick="setX(2)">2</input>
                <input type="checkbox" name="x[]" class="x-button" id="x3" onclick="setX(3)">3</input>
                <input type="checkbox" name="x[]" class="x-button" id="x4" onclick="setX(4)">4</input>
            </div>
            <input type="hidden" id="x_value" name="x_value">
            <input type="hidden" id="y_value" name="y_value">

            <br>

            <label for="y_value_input">Изменение Y (от -5 до 3):</label>
            <input type="text" id="y_value_input" name="y_value_input" >
            <br>

            <label>Изменение R:</label>
            <div>
                <select id="r" name="r" onchange="setR()">
                    <option class="radius-button" id="r1" value="1">1</option>
                    <option class="radius-button" id="r2" value="2">2</option>
                    <option class="radius-button" id="r3" value="3">3</option>
                    <option class="radius-button" id="r4" value="4">4</option>
                    <option class="radius-button" id="r5" value="5">5</option>
                </select>
            </div>
            <input type="hidden" id="radius_hidden" name="radius">
            <br>

            <input type="submit" value="Проверить точку">
        </form>

        <h3>График области</h3>
        <p>Нажмите на изображение, чтобы выбрать координаты X и Y:</p>
        <div class="graph-container">
            <canvas id="graphCanvas" width="500" height="500" onclick="handleClick(event);"></canvas>
<%--            <img src="image.png" alt="Координатная плоскость" style="width:500px;height:500px;" onclick="handleClick(event);">--%>

            <%
                // Отображение всех точек
                List<TableData> points = (List<TableData>) session.getAttribute("points");
                if (points != null) {
                    double graphWidth = 500;  // Фактическая ширина графика
                    double graphHeight = 500; // Фактическая высота графика
                    double offsetX = graphWidth / 2;  // Центр по X
                    double offsetY = graphHeight / 2; // Центр по Y
                    double rad = points.get(points.size()-1).getRadius();

                    for (TableData point : points) {
                        // Преобразуем координаты точки для изображения (500px ширина и высота картинки)
                        double px = (point.getX() / rad) * (graphWidth / 2) + offsetX;
                        double py = offsetY - (point.getY() / rad) * (graphHeight / 2);

                        // Проверка на границы: чтобы точки не выходили за пределы изображения
                        if (px < 0) px = 0;
                        if (px > graphWidth) px = graphWidth;
                        if (py < 0) py = 0;
                        if (py > graphHeight) py = graphHeight;

                        String color = point.isInside() ? "green" : "red";
            %>
            <div class="dot" style="left:<%= px %>px; top:<%= py %>px; background-color:<%= color %>;"></div>
            <%
                    }
                }
            %>
        </div>



        <h3>Результаты</h3>
        <%@ page import="java.util.ArrayList" %>
        <%@ page import="table.TableData" %>
        <%@ page import="utils.TableCreator" %>
        <%
            // Получите данные из сессии или запроса
            ArrayList<TableData> results = (ArrayList<TableData>) session.getAttribute("results");

            // Создайте экземпляр TableCreator
            TableCreator tableCreator = new TableCreator();

            // Проверьте, есть ли данные, и создайте таблицу
            if (results != null && !results.isEmpty()) {
                tableCreator.create(out, results);
            } else {
                out.println("<p>Нет данных для отображения.</p>");
            }
        %>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", () => {
        function updateDateTime() {
            const now = new Date();
            document.getElementById("date").innerText = now.toDateString();
            document.getElementById("time").innerText = now.toTimeString().substring(0, 8);
        }

        updateDateTime();
        setInterval(updateDateTime, 1000);
    });

    window.onload = function () {
        var canvas = document.getElementById("graphCanvas");
        var ctx = canvas.getContext("2d");
        var R = 200;  // Примерное значение радиуса

// Сместим начало координат в центр холста
        ctx.translate(250, 250);

// Отрисуем координатные оси
        ctx.beginPath();
        ctx.moveTo(-250, 0);
        ctx.lineTo(250, 0);
        ctx.moveTo(0, -250);
        ctx.lineTo(0, 250);
        ctx.strokeStyle = "#00FF00";
        ctx.stroke();

// Отрисуем четверть окружности во второй координатной четверти
        ctx.beginPath();
        ctx.arc(0, 0, R/2, Math.PI, 1.5 * Math.PI);
        ctx.strokeStyle = "#00FF00";
        ctx.stroke();

// Отрисуем треугольник в первой координатной четверти
        ctx.beginPath();
        ctx.moveTo(0, 0);
        ctx.lineTo(R, 0);
        ctx.lineTo(0, -R);
        ctx.closePath();
        ctx.strokeStyle = "#0000FF";
        ctx.stroke();

// Отрисуем прямоугольник в четвертой координатной четверти
        ctx.beginPath();
        ctx.rect(0, 0, R, R/2);
        ctx.strokeStyle = "#FFFF00";
        ctx.stroke();

// Добавим текстовые обозначения для всех сторон
        ctx.font = "15px Arial";
        ctx.fillStyle = "#000000";

// Обозначения осей
        ctx.fillText("R", -10, -R + 15);  // Верхняя ось
        ctx.fillText("R", R - 15, 15);     // Правая ось
        ctx.fillText("-R", -R - 25, 15);   // Левая ось
        ctx.fillText("-R", -10, R - 5);    // Нижняя ось

// Обозначения для половин радиуса
        ctx.fillText("R/2", R/2 - 10, 15);          // По правой оси
        ctx.fillText("-R/2", -R/2 - 30, 15);        // По левой оси
        ctx.fillText("R/2", 5, R/2 + 15);           // В нижней части
        ctx.fillText("-R/2", 5, -R/2 + 15);

    }





    const checkboxInputs = document.getElementsByName("x[]");

    function checkOnlyOne(checkbox) {
        checkboxInputs.forEach((item) => {
            if (item !== checkbox) item.checked = false;///МБ   ТУТ ПИЗДА
        });
    }

    checkboxInputs.forEach(function (checkbox) {
        checkbox.addEventListener("click", function () {
            checkOnlyOne(checkbox);
        });
    });
</script>
</body>
</html>