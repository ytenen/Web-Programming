package utils;

import jakarta.servlet.http.HttpServletRequest;
import table.CoordinateDTO;
import table.TableData;

public class RequestChecker {
     public static boolean checkRequest(HttpServletRequest request) {
        try {
            String xValue = request.getParameter("x_value");
            String yValue = request.getParameter("y_value");
            String yValueInput = request.getParameter("y_value_input");
            String radius = request.getParameter("radius");

            if ((yValue == null || yValue.isEmpty()) && yValueInput != null && !yValueInput.isEmpty()) {
                yValue = yValueInput;
            }
            float x = Float.parseFloat(xValue);
            float y = Float.parseFloat(yValue);
            double r = Double.parseDouble(radius);

            return x >= -4 && x <= 4
                    && y >= -5 && y <= 3
                    && r >= 1 && r <= 5;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static TableData createTableData(CoordinateDTO data){
        boolean isInside = checkIfInside(data.getX(), data.getY(), data.getR());
        return new TableData(data.getX(), data.getY(), data.getR(), isInside);
    }
    private static boolean checkIfInside(float x, float y, double r) {
        if (x>=0 && y<=0 && y>=-(r/2) && x<=r){
            return true;
        }else if(x>=0 && y>=0 && (x+y)<=r){
            return true;
        }else if(x<=0 && y>=0 && (x*x+y*y)<=(r/2)*(r/2)){
            return true;
        }
        return false;
    }

}
