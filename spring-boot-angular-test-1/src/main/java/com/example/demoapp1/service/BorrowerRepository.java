package com.example.demoapp1.service;

import com.example.demoapp1.model.Borrower;
import com.example.demoapp1.model.BusinessProcess;
import com.example.demoapp1.model.FacilityRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowerRepository extends JpaRepository<Borrower, Integer>{

    List<Borrower> findByBusinessProcessId(int businessProcessId);
}
