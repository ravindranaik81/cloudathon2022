package com.example.demoapp1.controller;

import com.example.demoapp1.model.*;
import com.example.demoapp1.service.BorrowerRepository;
import com.example.demoapp1.service.BusinessProcessRepository;
import com.example.demoapp1.service.BusinessProcessStateRepository;
import com.example.demoapp1.service.FacilityRequestRepository;
import com.example.demoapp1.view.CreateNewWorkflowModel;
import com.example.demoapp1.view.LoanWorkflowModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class WorkflowController {
    @Autowired
    BusinessProcessRepository businessProcessRepository;

    @Autowired
    BusinessProcessStateRepository businessProcessStateRepository;

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    FacilityRequestRepository facilityRequestRepository;

    @CrossOrigin
    @PostMapping(path = "/create-loan-workflow",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String createLoanWorkflow(@RequestBody CreateNewWorkflowModel viewModel) {
        String appEventTypeCode = "CreateWorkflow";
        String userId = "test-user";
        Date currentTime= new Date();

        BusinessProcess bp = new BusinessProcess();
        bp.setBusinessProcessTypeCode("Origination");
        bp.setBusinessProcessStatus("Pending");
        bp.setAppEventTypeCode(appEventTypeCode);
        bp.setTransactTs(currentTime);
        bp.setUserId(userId);

        businessProcessRepository.save(bp);

        BusinessProcessState bps = new BusinessProcessState();
        bps.setAppEventTypeCode(appEventTypeCode);
        bps.setBusinessProcessId(bp.getBusinessProcessId());
        bps.setBusinessProcessStepCode("PrimaryOpsReview");
        bps.setBusinessProcessStateCode("Pending");
        bps.setTransactTs(currentTime);
        bps.setUserId(userId);

        businessProcessStateRepository.save(bps);

        Borrower borrower = new Borrower();
        borrower.setFirstName(viewModel.getFirstName());
        borrower.setLastName(viewModel.getLastName());
        borrower.setMobileNumber(viewModel.getMobileNumber());
        borrower.setBusinessProcessId(bp.getBusinessProcessId());
        borrower.setAppEventTypeCode(appEventTypeCode);
        borrower.setUserId(userId);
        borrower.setTransactTs(currentTime);
        borrower.setPrimary(true);
        borrowerRepository.save(borrower);

        FacilityRequest request = new FacilityRequest();
        request.setLoanAmount(0.0f);
        request.setCollateralAmount(0.0f);
        request.setBusinessProcessId(bp.getBusinessProcessId());
        request.setAppEventTypeCode(appEventTypeCode);
        request.setUserId(userId);
        request.setTransactTs(currentTime);
        facilityRequestRepository.save(request);

        return Integer.toString(bp.getBusinessProcessId());
    }

    @CrossOrigin
    @GetMapping("/get-loan-workflow")
    public LoanWorkflowModel getLoanWorkflow(@RequestParam int businessProcessId) {
        LoanWorkflowModel model = new LoanWorkflowModel();

        Optional<BusinessProcess> optBusinessProcess = businessProcessRepository.findById(businessProcessId);

        if (!optBusinessProcess.isPresent()) {
            return model;
        }

        List<BusinessProcessState> businessProcessStates = businessProcessStateRepository.findByBusinessProcessId(optBusinessProcess.get().getBusinessProcessId());

        List<FacilityRequest> requests = facilityRequestRepository.findByBusinessProcessId(optBusinessProcess.get().getBusinessProcessId());

        List<Borrower> borrowers = borrowerRepository.findByBusinessProcessId(optBusinessProcess.get().getBusinessProcessId());

        model.setBp(optBusinessProcess.get());
        model.setBpsList(businessProcessStates);
        model.setBorrowerList(borrowers);
        model.setRequest(requests.get(0));

        return model;
    }
}
