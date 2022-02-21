package com.example.demoapp1.view;

import com.example.demoapp1.model.Borrower;
import com.example.demoapp1.model.BusinessProcess;
import com.example.demoapp1.model.BusinessProcessState;
import com.example.demoapp1.model.FacilityRequest;

import java.util.List;

public class LoanWorkflowModel {
    BusinessProcess bp;
    List<BusinessProcessState> bpsList;
    FacilityRequest request;
    List<Borrower> borrowerList;

    public BusinessProcess getBp() {
        return bp;
    }

    public void setBp(BusinessProcess bp) {
        this.bp = bp;
    }

    public List<BusinessProcessState> getBpsList() {
        return bpsList;
    }

    public void setBpsList(List<BusinessProcessState> bpsList) {
        this.bpsList = bpsList;
    }

    public FacilityRequest getRequest() {
        return request;
    }

    public void setRequest(FacilityRequest request) {
        this.request = request;
    }

    public List<Borrower> getBorrowerList() {
        return borrowerList;
    }

    public void setBorrowerList(List<Borrower> borrowerList) {
        this.borrowerList = borrowerList;
    }
}
