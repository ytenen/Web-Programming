package utils;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import request.PointRequest;
import request.UserRequest;

public class Validator {
    private static final String USERNAME_REQUIRED = "Username is required";
    private static final String PASSWORD_REQUIRED = "Password is required";
    private static final String USER_ID_REQUIRED = "User ID is required";
    private static final String Y_INVALID = "Y must be (-5;3)";
    private static final String X_INVALID = "X must be (-4;4)";
    private static final String R_INVALID = "R must be (-4;4)";

    public static void validateUserRequest(UserRequest userRequest) {
        if (userRequest.getUsername() == null || userRequest.getUsername().isBlank()) {
            throw new WebApplicationException(USERNAME_REQUIRED, Response.Status.BAD_REQUEST);
        }
        if (userRequest.getPassword() == null || userRequest.getPassword().isBlank()) {
            throw new WebApplicationException(PASSWORD_REQUIRED, Response.Status.BAD_REQUEST);
        }
    }

    public static void validatePointRequest(PointRequest pointRequest) {
        if (pointRequest.getUserId() == null) {
            throw new WebApplicationException(USER_ID_REQUIRED, Response.Status.BAD_REQUEST);
        }
        if (pointRequest.getY() < -5 || pointRequest.getY() > 3) {
            throw new WebApplicationException(Y_INVALID, Response.Status.BAD_REQUEST);
        }
        if (pointRequest.getX() < -4 || pointRequest.getX() > 4) {
            throw new WebApplicationException(X_INVALID, Response.Status.BAD_REQUEST);
        }
        if (pointRequest.getR()<-4 || pointRequest.getR()>4){
            throw new WebApplicationException(R_INVALID, Response.Status.BAD_REQUEST);
        }
    }
}