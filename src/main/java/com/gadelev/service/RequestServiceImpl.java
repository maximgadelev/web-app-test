package com.gadelev.service;

import com.gadelev.dto.RequestDto;
import com.gadelev.model.Request;
import com.gadelev.repo.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }




    @Override
    public List<RequestDto> findAllByUserId(Integer id) {
        return  requestRepository.findAllByUserId(id).stream().map(RequestDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public List<RequestDto> findAllCity(String city) {
        return requestRepository.findAllByCity(city).stream().map(RequestDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public RequestDto save(Request request) {
       return RequestDto.fromModel(requestRepository.save(request));
    }

}
