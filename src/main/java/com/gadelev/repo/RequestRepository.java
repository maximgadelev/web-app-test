package com.gadelev.repo;

import com.gadelev.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    List<Request> findAllByUserId(Integer userId);

    List<Request> findAllByCity(String city);
}
