package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import model.Result;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Named("resultBean")
@Path("/getResults")
@SessionScoped
@Produces(MediaType.APPLICATION_JSON)
public class ResultBean implements Serializable {

    private List<Result> results;

    public ResultBean(){
        results = new ArrayList<>();
    }

    public void addResult(Result result){
        results.add(result);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Result> getResults() {
        return results;
    }

}