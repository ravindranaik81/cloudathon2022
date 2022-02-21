package com.example.demoapp1.service;

import com.example.demoapp1.model.BusinessProcessState;
import com.example.demoapp1.model.FacilityRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRequestRepository extends JpaRepository<FacilityRequest, Integer>{

    List<FacilityRequest> findByBusinessProcessId(int businessProcessId);
}
