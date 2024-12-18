package data;

import service.AddResultRequest;

public class PointDTOParser {
    public static PointDTO parse(AddResultRequest request){
        return PointDTO.of(request.getX(), request.getY(), request.getR());
    }
}
