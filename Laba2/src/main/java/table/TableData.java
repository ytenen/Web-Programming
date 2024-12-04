package table;

public class TableData {
    private double x;
    private double y;
    private double radius;
    private boolean isInside;


    public TableData(double x, double y, double radius, boolean isInside) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isInside = isInside;
    }

    public double getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public boolean isInside() {
        return isInside;
    }

    public void setInside(boolean inside) {
        isInside = inside;
    }
}
