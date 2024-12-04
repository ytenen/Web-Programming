package servlets;

import table.TableData;
import utils.CoordinatesDTOParser;
import utils.MetricsHandler;
import utils.RequestChecker;
import utils.SessionUpdater;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AreaCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TableData result = RequestChecker.createTableData(CoordinatesDTOParser.parse(request));
            //Обновляем данные в сессии
            SessionUpdater.updateSession(request,result);
            MetricsHandler.checkHitResult(result);
            // Передаем текущий результат на страницу результата
            request.setAttribute("currentResult", result);
            request.getRequestDispatcher("/result.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Обрабатываем ошибку преобразования
            response.getWriter().println("Ошибка: " + e.getMessage());
        }
    }

}