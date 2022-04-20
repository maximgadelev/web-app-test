package com.gadelev.controller;

import com.gadelev.aspect.Loggable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.gadelev.dto.RequestDto;
import com.gadelev.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @Operation(summary = "Returns requests by city")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Requests was get by city",
            content = {@Content(mediaType = "application/json")})})
    @Loggable
    @GetMapping("/city/{city}")
    public Iterable<RequestDto> getAllByCity(@PathVariable String city) {
        return requestService.findAllCity(city);
    }

    @Operation(summary = "Returns requests by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Requests was get by id",
            content = {@Content(mediaType = "application/json")})})
    @Loggable
    @GetMapping("/request/{id}")
    public List<RequestDto> getAllById(@PathVariable Integer id) {
        return requestService.findAllByUserId(id);
    }

}
