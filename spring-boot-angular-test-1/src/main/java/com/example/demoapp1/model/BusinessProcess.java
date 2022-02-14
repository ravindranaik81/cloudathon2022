package com.example.demoapp1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
public class BusinessProcess {

    @Id
    @GeneratedValue
    private int businessProcessId;

    private String businessProcessTypeCode;

    private String businessProcessStatus;

    private String appEventTypeCode;

    private Date transactTs;

    private String userId;

    public BusinessProcess() {
        super();
    }

    public BusinessProcess(String businessProcessTypeCode, String businessProcessStatus, String appEventTypeCode, Date transactTs, String userId) {
        this.businessProcessTypeCode = businessProcessTypeCode;
        this.businessProcessStatus = businessProcessStatus;
        this.appEventTypeCode = appEventTypeCode;
        this.transactTs = transactTs;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBusinessProcessId() {
        return businessProcessId;
    }

    public void setBusinessProcessId(int businessProcessId) {
        this.businessProcessId = businessProcessId;
    }

    public String getBusinessProcessTypeCode() {
        return businessProcessTypeCode;
    }

    public void setBusinessProcessTypeCode(String businessProcessTypeCode) {
        this.businessProcessTypeCode = businessProcessTypeCode;
    }

    public String getBusinessProcessStatus() {
        return businessProcessStatus;
    }

    public void setBusinessProcessStatus(String businessProcessStatus) {
        this.businessProcessStatus = businessProcessStatus;
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

    @Override
    public int hashCode() {
        return Objects.hash(businessProcessId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessProcess that = (BusinessProcess) o;
        return businessProcessId == that.businessProcessId;
    }

    @Override
    public String toString() {
        return "BusinessProcess{" +
                "businessProcessId=" + businessProcessId +
                ", businessProcessTypeCode='" + businessProcessTypeCode + '\'' +
                ", businessProcessStatus='" + businessProcessStatus + '\'' +
                ", appEventTypeCode='" + appEventTypeCode + '\'' +
                ", transactTs=" + transactTs +
                ", userId='" + userId + '\'' +
                '}';
    }
}