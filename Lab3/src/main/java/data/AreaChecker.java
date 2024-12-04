package data;

public class AreaChecker {
    public static boolean isInArea(double x, double y, double r) {
        if (x <= r && x >= 0 && y >= -r/2 && y <= 0) {
            return true;
        }
        if (x >= -r / 2 && x <= 0 && y >= 0 && y <= r / 2) {
            if ((x * x + y * y) <= (r / 2) * (r / 2)) {
                return true;
            }
        }
        return x <= 0 && y <= 0 && y >=(-x-r);
    }

}