package com.example.demoapp1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Borrower {

    @Id
    @GeneratedValue
    private int borrowerId;

    private String firstName;

    private String lastName;

    private String mobileNumber;

    private boolean isPrimary;

    private Date birthDate;

    private String identityType;

    private String identityNumber;

    private String address;

    private int businessProcessId;

    private String appEventTypeCode;

    private Date transactTs;

    private String userId;

    public Borrower(String firstName, String lastName, String mobileNumber, boolean isPrimary, Date birthDate, String identityType, String identityNumber, String address, int businessProcessId, String appEventTypeCode, Date transactTs, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.isPrimary = isPrimary;
        this.birthDate = birthDate;
        this.identityType = identityType;
        this.identityNumber = identityNumber;
        this.address = address;
        this.businessProcessId = businessProcessId;
        this.appEventTypeCode = appEventTypeCode;
        this.transactTs = transactTs;
        this.userId = userId;
    }

    public Borrower() {
        super();
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        Borrower borrower = (Borrower) o;
        return borrowerId == borrower.borrowerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowerId);
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "borrowerId=" + borrowerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", isPrimary=" + isPrimary +
                ", birthDate=" + birthDate +
                ", identityType='" + identityType + '\'' +
                ", identityNumber='" + identityNumber + '\'' +
                ", address='" + address + '\'' +
                ", businessProcessId='" + businessProcessId + '\'' +
                ", appEventTypeCode='" + appEventTypeCode + '\'' +
                ", transactTs=" + transactTs +
                ", userId='" + userId + '\'' +
                '}';
    }
}