package data;

public class AreaChecker {
    public static boolean isInArea(double x, double y, double r) {
        // Проверка четверти круга (вторая четверть: x ≥ 0 и y ≥ 0)
        if (x >= 0 && y <= 0 && (x * x + y * y) <= (r * r/4)) {
            return true;
        }
        // Проверка прямоугольника (третья четверть: x ≤ 0 и y ≥ 0)
        if (x <= 0 && y >= 0 && x >= -r/2 && y <= r) {
            return true;
        }
        // Проверка треугольника (четвертая четверть: x ≤ 0 и y ≤ 0)
        if (x <= 0 && y <= 0 && y >= -x - r) {
            return true;
        }
        return false;
    }

}