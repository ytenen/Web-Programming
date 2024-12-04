package utils;

public class Truncate {

    public static float truncate(float value, int decimalPlaces) {
        double factor = Math.pow(10, decimalPlaces);
        return (float) (Math.floor(value * factor) / factor);
    }
}