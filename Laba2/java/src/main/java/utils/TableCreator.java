package utils;


import jakarta.servlet.jsp.JspWriter;
import table.TableData;

import java.io.IOException;
import java.util.ArrayList;

public class TableCreator {
    public void create(JspWriter out, ArrayList<TableData> results) throws IOException {

        // Начало таблицы
        out.println("<table>");
        out.println("<tr><th>X</th><th>Y</th><th>Радиус</th><th>Результат</th></tr>");

        // Заполнение таблицы данными
        for (TableData result : results) {
            out.println("<tr>");
            out.println("<td>" + result.getX() + "</td>");
            out.println("<td>" + result.getY() + "</td>");
            out.println("<td>" + result.getRadius() + "</td>");
            out.println("<td>" + (result.isInside() ? "Попадание" : "Мимо") + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
    }

}
