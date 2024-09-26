public class HitChecker {
    public static String calculatePoint(double x, double y, double r) {
        if (x<0 && y>0){
            return "Нет";
        }
        if ((x>(-r/2)) && (x<=0) && (y>-r) && (y<=0)){
            return "Да";
        }
        if (((x*x + y*y)<=((r/2)*(r/2))) && x >= 0   &&   y <= 0){
            return "Да";
        }
        if (x >= 0 && y >= 0 && y <= r / 2 && x <= r && y <= (-0.5 * x + r / 2)){
            return "Да";
        }
        return "Нет";
    }
}