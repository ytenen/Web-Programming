package utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import table.TableData;

import java.util.ArrayList;
import java.util.List;

public class SessionUpdater {
    public static void updateSession(HttpServletRequest request, TableData result){
        // Получаем сессию и списки результатов
        HttpSession session = request.getSession();
        List<TableData> results = (List<TableData>) session.getAttribute("results");
        List<TableData> points = (List<TableData>) session.getAttribute("points");

        if (results == null) {
            results = new ArrayList<>();
        }

        if (points == null) {
            points = new ArrayList<>();
        }

        // Добавляем результат в общий список
        results.add(result);
        // Всегда добавляем точку, независимо от способа ввода
        points.add(result);

        // Сохраняем списки в сессии
        session.setAttribute("results", results);
        session.setAttribute("points", points);
    }
}
