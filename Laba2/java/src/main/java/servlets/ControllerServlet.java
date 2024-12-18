package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.MetricsHandler;
import utils.RequestChecker;

import java.io.IOException;
@WebServlet
public class ControllerServlet extends HttpServlet {
    @Override
    public void init() {
        try {
            super.init();
            MetricsHandler.initialize();
            System.out.println("Prometheus metrics server initialized on port 9091");
        } catch (Exception e) {
            System.out.println("Failed to initialize Prometheus metrics server "+ e.getMessage());
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (RequestChecker.checkRequest(request)) {
            request.getRequestDispatcher("/AreaCheckServlet").forward(request, response);
        } else {
            response.getWriter().println("Bad Request 400");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

}