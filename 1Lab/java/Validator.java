import java.util.ArrayList;
import java.util.Arrays;

public class Validator {
    public static boolean validate(String x, String y, String r){
        try{
            double dx = Double.parseDouble(x.trim());
            if (dx<-5 || dx>3){
                throw new Exception();
            }
            double dy = Double.parseDouble(y.trim());
            if (dy<-3 || dy>5){
                throw new Exception();
            }
            double dr = Double.parseDouble(r.trim());
            Double[] rValues = {1.0,1.5,2.0,2.5,3.0};
            ArrayList<Double> rList = new ArrayList<>(Arrays.asList(rValues));
            if (!rList.contains(dr)){
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
