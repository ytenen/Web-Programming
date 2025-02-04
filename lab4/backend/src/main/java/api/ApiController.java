package api;

import controller.LoginController;
import controller.RegistrationController;
import controller.ResultController;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import request.PointRequest;
import request.UserRequest;



@Path("/controller")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApiController {

    @Inject
    private ResultController resultController;
    @Inject
    private LoginController loginController;
    @Inject
    private RegistrationController registration;


    @POST
    @Path("/register")
    public Response register(UserRequest userRequest) {
        return registration.register(userRequest);
    }

    @POST
    @Path("/login")
    public Response login(UserRequest userRequest) {
        return loginController.login(userRequest);
    }

    @POST
    @Path("/points")
    public Response points(PointRequest pointRequest) {
        return resultController.saveResult(pointRequest);
    }

    @GET
    @Path("/results/{id}")
    public Response getPoints(@PathParam("id") Long userId) {
        return resultController.getResultsByUserId(userId);
    }

    @DELETE
    @Path("/results/clear/{id}")
    public Response clear(@PathParam("id") Long userId) {
        return resultController.deleteResultsByUserId(userId);
    }
}