package utils;

import request.PointRequest;

public class AreaChecker {

    public static boolean checkHit(PointRequest request) {
        double x = request.getX();
        double y = request.getY();
        double r = request.getR();
        boolean b = x * x + y * y <= r * r;
        if (r>=0){
            return ((x>=0 && y>=0 && y<=r && x <=r) || (x<=0 && y<=0 && b) || (x>=0 && y<=0 && y>=2*x-r));
        }else{
            return ((x<=0 && y<=0 && y>=r && x >=r) || (x>=0 && y>=0 && b) || (x<=0 && y>=0 && y<=2*x-r));
        }
    }
}
