package beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.Result;
import service.AddResultRequest;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import service.ResultService;


import java.io.Serializable;
import java.util.List;

@Data
@Slf4j
@Named("resultBean")
@ApplicationScoped
@Path("/addResult")
@Produces(MediaType.APPLICATION_JSON)
public class ResultBean implements Serializable {


    private ResultService resultService;

    private List<Result> results;

    @PostConstruct
    @Inject
    public void init() {
        results = resultService.getAllResults();
        log.info("Amount of results: {}", results.size());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addResult(AddResultRequest request) {
        boolean isInside = resultService.processResult(request.getX(), request.getY(), request.getR());
        results = resultService.getAllResults();
        return Response.ok("{ \"success\": true, \"isInside\": " + isInside + " }").build();
    }

    public void addResultFromForm(float x, float y, float r) {
        boolean isInside = resultService.processResult(x, y, r);
        results = resultService.getAllResults();
        log.info("Result added from form: X={}, Y={}, R={}, Result={}", x, y, r, isInside);
    }
}