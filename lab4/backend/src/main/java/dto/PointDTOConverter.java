package dto;

import entity.PointEntity;
import request.PointRequest;
import response.PointResponse;

public class PointDTOConverter {

    public static PointEntity toEntity(PointRequest request) {
        PointEntity pointEntity = new PointEntity();
        pointEntity.setX(request.getX());
        pointEntity.setY(request.getY());
        pointEntity.setR(request.getR());
        return pointEntity;
    }

    public static PointResponse toResponse(PointEntity entity) {
        return new PointResponse(
                entity.getX(),
                entity.getY(),
                entity.getR(),
                entity.getHit() != null && entity.getHit().equals("yes")
        );
    }
}
