package controller;

import dto.PointDTOConverter;
import entity.PointEntity;
import entity.UserEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import request.PointRequest;
import response.PointResponse;
import service.PointService;
import service.UserService;
import utils.AreaChecker;
import utils.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class ResultController {

    @Inject
    private UserService userService;
    @Inject
    private PointService pointService;

    public Response saveResult(PointRequest pointRequest) {
        try {
            Validator.validatePointRequest(pointRequest);
            UserEntity user = userService.findUserById(pointRequest.getUserId());
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("hjkkhkjh")
                        .build();
            }

            boolean hit = AreaChecker.checkHit(pointRequest);
            PointEntity pointEntity = PointDTOConverter.toEntity(pointRequest);
            pointEntity.setUser(user);
            pointEntity.setHit(hit);
            pointService.saveResult(user, pointRequest, hit);

            PointResponse response = PointDTOConverter.toResponse(pointEntity);
            return Response.ok(response).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus())
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred")
                    .build();
        }
    }

    public Response getResultsByUserId(Long userId) {
        try {
            List<PointEntity> results = pointService.getResultsByUserId(userId);
            if (results.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No results found for this user")
                        .build();
            }

            List<PointResponse> responseList = results.stream()
                    .map(PointDTOConverter::toResponse)
                    .collect(Collectors.toList());

            return Response.ok(responseList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred while retrieving results")
                    .build();
        }
    }

    public Response deleteResultsByUserId(Long userId) {
        try {
            pointService.clearResultsByUserId(userId);
            return Response.ok("Results cleared successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred while clearing results")
                    .build();
        }
    }
}
