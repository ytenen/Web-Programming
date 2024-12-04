package utils;

import table.CoordinateDTO;

import javax.servlet.http.HttpServletRequest;

public class CoordinatesDTOParser {
    public static CoordinateDTO parse(HttpServletRequest request){
        String xValue = request.getParameter("x_value");
        String yValue = request.getParameter("y_value");
        String radius = request.getParameter("radius");
        return CoordinateDTO.of(xValue,yValue,radius);
    }
}
