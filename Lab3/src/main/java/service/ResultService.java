package service;

import data.AreaChecker;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Result;
import utils.Truncate;

import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class ResultService implements Serializable {

    private final ResultRepository resultRepository;

    @Inject
    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public boolean processResult(float x, float y, float r) {
        x = Truncate.truncate(x, 2);
        y = Truncate.truncate(y, 2);
        boolean isInside = AreaChecker.isInArea(x, y, r);

        Result entity = Result.builder()
                .x(x)
                .y(y)
                .r(r)
                .result(isInside)
                .build();

        resultRepository.save(entity);
        return isInside;
    }
}
