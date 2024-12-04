package servlets;

import utils.MetricsHandler;
import utils.RequestChecker;

import javax.servlet.ServletException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {
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