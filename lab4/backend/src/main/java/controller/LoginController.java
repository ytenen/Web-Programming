package controller;


import entity.UserEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.UserRequest;
import service.UserService;
import utils.Validator;


public class LoginController {
    @Inject
    private UserService userService;


    public Response login(UserRequest userRequest) {
        try {
            Validator.validateUserRequest(userRequest);
            UserEntity user = userService.authenticate(userRequest.getUsername(), userRequest.getPassword());
            if (user == null) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid username or password")
                        .build();
            }
            return Response.ok(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred")
                    .build();
        }
    }

}
