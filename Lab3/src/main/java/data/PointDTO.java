package data;

import lombok.Getter;



@Getter
public class PointDTO {
    private float x;
    private float y;
    private float r;

    public PointDTO(float x, float y,float r){
        this.x = x;
        this.y =y;
        this.r = r;
    }

    public static PointDTO of(String xValueStr, String yValueStr, String radiusStr){

        return new PointDTO(Float.parseFloat(xValueStr),
                Float.parseFloat(yValueStr),
                Float.parseFloat(radiusStr));
    }


}
