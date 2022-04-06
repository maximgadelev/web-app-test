package com.gadelev.controller;

import com.gadelev.aspect.Loggable;
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


    @Loggable
    @GetMapping("/city/{city}")
    public Iterable<RequestDto>  getAllByCity(@PathVariable String city){
        return requestService.findAllCity(city);
    }
    @Loggable
    @GetMapping("/request/{id}")
    public List<RequestDto> getAllById(@PathVariable Integer id){
       return requestService.findAllByUserId(id);
    }

}
