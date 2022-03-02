package com.gadelev.service;

import com.gadelev.dto.RequestDto;
import com.gadelev.model.Request;

import java.util.List;

public interface RequestService {
    List<RequestDto> findAllByUserId(Integer userId);
    List<RequestDto> findAllCity(String city);
    Request save(Request request);
}
