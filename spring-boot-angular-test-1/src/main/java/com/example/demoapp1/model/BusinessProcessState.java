package com.example.demoapp1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class BusinessProcessState {

    @Id
    @GeneratedValue
    private int businessProcessStateId;

    private String businessProcessId;

    private String businessProcessStepCode;

    private String businessProcessStateCode;

    private String appEventTypeCode;

    private Date transactTs;

    private String userId;

    public BusinessProcessState() {
        super();
    }

    public BusinessProcessState(int businessProcessStateId, String businessProcessId, String businessProcessStepCode, String businessProcessStateCode, String appEventTypeCode, Date transactTs, String userId) {
        this.businessProcessStateId = businessProcessStateId;
        this.businessProcessId = businessProcessId;
        this.businessProcessStepCode = businessProcessStepCode;
        this.businessProcessStateCode = businessProcessStateCode;
        this.appEventTypeCode = appEventTypeCode;
        this.transactTs = transactTs;
        this.userId = userId;
    }

    public int getBusinessProcessStateId() {
        return businessProcessStateId;
    }

    public void setBusinessProcessStateId(int businessProcessStateId) {
        this.businessProcessStateId = businessProcessStateId;
    }

    public String getBusinessProcessId() {
        return businessProcessId;
    }

    public void setBusinessProcessId(String businessProcessId) {
        this.businessProcessId = businessProcessId;
    }

    public String getBusinessProcessStepCode() {
        return businessProcessStepCode;
    }

    public void setBusinessProcessStepCode(String businessProcessStepCode) {
        this.businessProcessStepCode = businessProcessStepCode;
    }

    public String getBusinessProcessStateCode() {
        return businessProcessStateCode;
    }

    public void setBusinessProcessStateCode(String businessProcessStateCode) {
        this.businessProcessStateCode = businessProcessStateCode;
    }

    public String getAppEventTypeCode() {
        return appEventTypeCode;
    }

    public void setAppEventTypeCode(String appEventTypeCode) {
        this.appEventTypeCode = appEventTypeCode;
    }

    public Date getTransactTs() {
        return transactTs;
    }

    public void setTransactTs(Date transactTs) {
        this.transactTs = transactTs;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessProcessState that = (BusinessProcessState) o;
        return businessProcessStateId == that.businessProcessStateId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessProcessStateId);
    }
}