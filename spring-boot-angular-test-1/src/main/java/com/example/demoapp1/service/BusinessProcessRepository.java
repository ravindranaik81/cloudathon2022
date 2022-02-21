package com.example.demoapp1.service;

import com.example.demoapp1.model.BusinessProcess;
import com.example.demoapp1.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessProcessRepository extends JpaRepository<BusinessProcess, Integer>{


}
