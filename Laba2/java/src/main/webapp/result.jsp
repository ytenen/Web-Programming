<%@ page import="table.TableData" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Результаты проверки точки</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        .container {
            background-color: white;
            margin: 50px auto;
            padding: 20px;
            border-radius: 10px;
            width: 50%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        table {
            margin: 0 auto;
            width: 50%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
            padding: 10px;
        }
        th {
            background-color: blueviolet;
            color: white;
            padding: 10px;
            text-align: center;
        }
        td {
            padding: 10px;
            text-align: center;
        }
        a {
            display: block;
            margin-top: 20px;
            text-decoration: none;
            color: #3498db;
            font-size: 20px;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Результаты</h2>

    <%
        TableData currentResult = (TableData) request.getAttribute("currentResult");
        if (currentResult != null) {
    %>
    <table>
        <tr>
            <th>Параметр</th>
            <th>Значение</th>
        </tr>
        <tr>
            <td>X</td>
            <td><%= currentResult.getX() %></td>
        </tr>
        <tr>
            <td>Y</td>
            <td><%= currentResult.getY() %></td>
        </tr>
        <tr>
            <td>Радиус</td>
            <td><%= currentResult.getRadius() %></td>
        </tr>
        <tr>
            <td>Результат</td>
            <td><%= currentResult.isInside() ? "Попадание" : "Мимо" %></td>
        </tr>
    </table>
    <%
    } else {
    %>
    <p>Нет результатов для отображения.</p>
    <%
        }
    %>

    <a href="index.jsp">Вернуться</a>
</div>

</body>
</html>