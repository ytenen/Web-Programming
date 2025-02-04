package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import request.UserRequest;
import service.UserService;
import entity.UserEntity;
import utils.Validator;
import java.util.Optional;

public class RegistrationController {
    @Inject
    private UserService userService;


    public Response register(UserRequest userRequest) {
        try {
            Validator.validateUserRequest(userRequest);
            Optional<UserEntity> newUserOptional = Optional.ofNullable(
                    userService.register(userRequest.getUsername(), userRequest.getPassword())
            );
            return  newUserOptional.map(this::buildSuccessfulResponse)
                    .orElseGet(this::buildUserAlreadyExistsResponse);
        } catch (WebApplicationException e) {
            return buildErrorResponse(e);
        } catch (Exception e) {
            return buildInternalServerErrorResponse();
        }
    }

    private Response buildSuccessfulResponse(UserEntity userEntity) {
        return Response.ok(userEntity).build();
    }
    private Response buildUserAlreadyExistsResponse() {
        return Response.status(Response.Status.BAD_REQUEST).entity("User already exists").build();
    }
    private Response buildErrorResponse(WebApplicationException e) {
        return Response.status(e.getResponse().getStatus())
                .entity(e.getMessage())
                .build();
    }
    private Response buildInternalServerErrorResponse(){
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("An unexpected error occurred")
                .build();
    }
}