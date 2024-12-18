package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import table.TableData;
import utils.CoordinatesDTOParser;
import utils.MetricsHandler;
import utils.RequestChecker;
import utils.SessionUpdater;

import java.io.IOException;
@WebServlet
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