package com.example.demoapp1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class FacilityRequest {

    @Id
    @GeneratedValue
    private int facilityRequestId;

    private String loanType;

    private float loanAmount;

    private float collateralAmount;

    private String maturityCode;

    private int businessProcessId;

    private String appEventTypeCode;

    private Date transactTs;

    private String userId;

    public FacilityRequest() {
        super();
    }

    public FacilityRequest(String loanType, float loanAmount, float collateralAmount, String maturityCode, int businessProcessId, String appEventTypeCode, Date transactTs, String userId) {
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.collateralAmount = collateralAmount;
        this.maturityCode = maturityCode;
        this.businessProcessId = businessProcessId;
        this.appEventTypeCode = appEventTypeCode;
        this.transactTs = transactTs;
        this.userId = userId;
    }

    public int getFacilityRequestId() {
        return facilityRequestId;
    }

    public void setFacilityRequestId(int facilityRequestId) {
        this.facilityRequestId = facilityRequestId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public float getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(float loanAmount) {
        this.loanAmount = loanAmount;
    }

    public float getCollateralAmount() {
        return collateralAmount;
    }

    public void setCollateralAmount(float collateralAmount) {
        this.collateralAmount = collateralAmount;
    }

    public String getMaturityCode() {
        return maturityCode;
    }

    public void setMaturityCode(String maturityCode) {
        this.maturityCode = maturityCode;
    }

    public int getBusinessProcessId() {
        return businessProcessId;
    }

    public void setBusinessProcessId(int businessProcessId) {
        this.businessProcessId = businessProcessId;
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
        FacilityRequest that = (FacilityRequest) o;
        return facilityRequestId == that.facilityRequestId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(facilityRequestId);
    }

    @Override
    public String toString() {
        return "FacilityRequest{" +
                "facilityRequestId=" + facilityRequestId +
                ", loanType='" + loanType + '\'' +
                ", loanAmount=" + loanAmount +
                ", collateralAmount=" + collateralAmount +
                ", maturityCode='" + maturityCode + '\'' +
                ", businessProcessId='" + businessProcessId + '\'' +
                ", appEventTypeCode='" + appEventTypeCode + '\'' +
                ", transactTs=" + transactTs +
                ", userId='" + userId + '\'' +
                '}';
    }
}