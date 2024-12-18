package beans;

import data.PointDTOParser;
import data.Validator;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import model.Result;
import service.AddResultRequest;
import service.ResultDAO;

import java.io.Serializable;

@Data
@Slf4j
@Named("controllerBean")
@ApplicationScoped
@Path("/addResult")
@Produces(MediaType.APPLICATION_JSON)
public class ControllerBean implements Serializable {

    @Inject
    private ResultDAO resultDAO;

    @Inject
    private AreaCheckBean areaCheckBean;

    @Inject
    private ResultBean resultBean;

    @Inject
    private TableDataBean tableDataBean;


    @PostConstruct
    public void init() {
        tableDataBean.setResults(resultDAO.findAll());
        log.info("Amount of results: {}", resultDAO.findAll());
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addResult(AddResultRequest request) {
        if (Validator.unpackAndValidate(request)){
            Result resultEntity = areaCheckBean.processResult(PointDTOParser.parse(request));

            resultBean.addResult(resultEntity);

            tableDataBean.setResults(resultDAO.findAll());

            return Response.ok("{ \"success\": true, \"isInside\": " + resultEntity.isResult() + " }").build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{ \"success\": false, \"message\": \"Invalid input\" }")
                .type(MediaType.APPLICATION_JSON)
                .build();

    }


}
