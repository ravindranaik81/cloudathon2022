package com.example.demoapp1.service;

import com.example.demoapp1.model.BusinessProcessState;
import com.example.demoapp1.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessProcessStateRepository extends JpaRepository<BusinessProcessState, Integer>{

    List<BusinessProcessState> findByBusinessProcessStepCodeAndBusinessProcessStateCode(String step, String state);

	List<BusinessProcessState> findByBusinessProcessId(int businessProcessId);
}
