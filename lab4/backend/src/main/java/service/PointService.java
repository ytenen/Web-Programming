package service;

import dao.PointDAO;
import entity.PointEntity;
import entity.UserEntity;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import request.PointRequest;

import java.util.List;

@Stateless
public class PointService {

    @Inject
    private PointDAO pointDAO;

    public void saveResult(UserEntity user, PointRequest request, boolean hit) {
        PointEntity result = new PointEntity();
        result.setUser(user);
        result.setX(request.getX());
        result.setY(request.getY());
        result.setR(request.getR());
        result.setHit(hit);
        pointDAO.save(result);
    }
    public List<PointEntity> getResultsByUserId(Long userId) {
        return pointDAO.findByUserId(userId);
    }

    public void clearResultsByUserId(Long userId) {
        pointDAO.deleteByUserId(userId);
    }


}
