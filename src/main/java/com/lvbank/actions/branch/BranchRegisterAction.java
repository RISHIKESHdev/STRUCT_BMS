package com.lvbank.actions.branch;

import com.lvbank.CommonConstant;
import com.lvbank.model.bank.Branch;
import com.lvbank.model.navigate.Address;
import com.lvbank.model.navigate.GeoLocation;
import com.opensymphony.xwork2.ActionSupport;

public class BranchRegisterAction extends ActionSupport {
    private String addressLineOne,addressLineTwo,addressLineThree,landMark,city,state,country,pinCode;
    private String branchName,bankCode;
    private double latitude,longitude;
    private long mobileNumber;
    private boolean isBranchRegistered=false;

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getAddressLineThree() {
        return addressLineThree;
    }

    public void setAddressLineThree(String addressLineThree) {
        this.addressLineThree = addressLineThree;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isBranchRegistered() {
        return isBranchRegistered;
    }

    public void setBranchRegistered(boolean branchRegistered) {
        isBranchRegistered = branchRegistered;
    }

    public String execute(){
        String retVal;

        bankCode = CommonConstant.BANK_CODE;
        landMark="Landmark";
        country="India";

        BranchService service = new BranchService();

        Branch branch = new Branch(branchName,bankCode,mobileNumber);
        Address address = new Address(addressLineOne,addressLineTwo,addressLineThree,landMark,city,state,country,pinCode);
        GeoLocation geoLocation = new GeoLocation(latitude,longitude);
        isBranchRegistered = service.registerBranch(branch,address,geoLocation);

        if(isBranchRegistered){
            retVal="success";
        }else{
            retVal="error";
        }

        return retVal;
    }
}
