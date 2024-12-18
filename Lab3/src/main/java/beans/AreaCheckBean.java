package beans;

import data.AreaChecker;
import data.PointDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.Result;
import service.ResultDAO;

import java.io.Serializable;

@ApplicationScoped
@Named("areaCheckBean")
public class AreaCheckBean implements Serializable {

    @Inject
    private ResultDAO resultDAO;
    public Result processResult(PointDTO pointDTO) {

        float x = pointDTO.getX();
        float y = pointDTO.getY();
        float r = pointDTO.getR();

        boolean isInside = AreaChecker.isInArea(x, y, r);

        Result entity = Result.builder()
                .x(x)
                .y(y)
                .r(r)
                .result(isInside)
                .build();

        resultDAO.save(entity);
        return entity;
    }


}
