package data;

import service.AddResultRequest;


public class Validator {
    public static boolean unpackAndValidate(AddResultRequest request){
        return validate(request.getX(), request.getY(), request.getR());
    }
    public static boolean validate(String xValue, String yValue, String rValue){
        try{
            float x = Float.parseFloat(xValue);
            float y = Float.parseFloat(yValue);
            float r = Math.round(Float.parseFloat(rValue)*100.0f)/100.0f;
            if (x<-5.0 || x>3){
                throw new Exception();
            }
            if(y<-5.0 || y>5.0){
                throw new Exception();
            }
            if ((r < 2.0) || (r > 5.0) || (Math.abs(r * 4 - Math.round(r * 4)) > 0.0001)) {
                throw new Exception();
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }




}
