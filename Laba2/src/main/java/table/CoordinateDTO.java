package table;

public class CoordinateDTO {
    private float x;
    private float y;
    private double r;

    public CoordinateDTO(float x, float y,double r){
        this.x = x;
        this.y =y;
        this.r = r;
    }

    public static CoordinateDTO of(String xValueStr, String yValueStr, String radiusStr){
        // Преобразуем строки в числа
        CoordinateDTO cordDTO = new CoordinateDTO(Float.parseFloat(xValueStr),Float.parseFloat(yValueStr),Double.parseDouble(radiusStr));
        return cordDTO;
    }

    public float getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public float getX() {
        return x;
    }

}
