package com.lvbank.actions.profile.entry;

import com.lvbank.model.navigate.Address;
import com.lvbank.model.profile.Admin;
import com.lvbank.model.profile.Customer;
import com.lvbank.model.profile.Employee;
import com.lvbank.model.profile.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class SignUpAction extends ActionSupport {
    private String firstName, middleName, lastName, emailId = null, gender, password = null, mobileNumber = null;
    private int age;
    private String addressLineOne, addressLineTwo, addressLineThree, landMark, city, state, country, pinCode;
    private int branchId = 0;
    private String panNumber, CKYCVerificationDocument, CKYCVerificationId,employeeDesignation;
    private String activeString;
    private int yearOfExperience;
    private double employeeCTC;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getCKYCVerificationDocument() {
        return CKYCVerificationDocument;
    }

    public void setCKYCVerificationDocument(String CKYCVerificationDocument) {
        this.CKYCVerificationDocument = CKYCVerificationDocument;
    }

    public String getCKYCVerificationId() {
        return CKYCVerificationId;
    }

    public void setCKYCVerificationId(String CKYCVerificationId) {
        this.CKYCVerificationId = CKYCVerificationId;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public String getActiveString() {
        return activeString;
    }

    public void setActiveString(String activeString) {
        this.activeString = activeString;
    }

    public int getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(int yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }

    public double getEmployeeCTC() {
        return employeeCTC;
    }

    public void setEmployeeCTC(double employeeCTC) {
        this.employeeCTC = employeeCTC;
    }

    public String execute() {
        String retVal;
        User user = null;
        Address address;
        boolean isUserSignedUp = false;

        HttpSession session = ServletActionContext.getRequest().getSession(false);
        EntryService service = new EntryService();

//        firstName = request.getParameter("firstName");
//        middleName = request.getParameter("middleName");
//        lastName = request.getParameter("lastName");
//        emailId = request.getParameter("emailId");
//        gender = request.getParameter("gender");
//        password = request.getParameter("password");
//        mobileNumber = request.getParameter("mobileNumber");
//        age = Integer.parseInt(request.getParameter("age"));

//        addressLineOne = request.getParameter("addressLineOne");
//        addressLineTwo = request.getParameter("addressLineTwo");
//        addressLineThree = request.getParameter("addressLineThree");
//        landMark = "Landmark";
//        city = request.getParameter("city");
//        state = request.getParameter("state");
//        country = "India";
//        pinCode = request.getParameter("pinCode");
        String role = (String) session.getAttribute("role");

        if (role == null) {
            user = new Customer(firstName, middleName, lastName, emailId, gender, age, mobileNumber, password, panNumber, CKYCVerificationDocument, CKYCVerificationId);
        } else {
            if (session.getAttribute("branchIds") != null) {
                boolean isActive = true;

                branchId = Integer.parseInt(String.valueOf(branchId));

                user = new Employee(firstName, middleName, lastName, emailId, gender, age, mobileNumber, password, employeeDesignation, employeeCTC, yearOfExperience, isActive);

            } else if (role.compareTo("Admin") == 0) {
                boolean isActive = false;

                isActive = activeString.compareToIgnoreCase("Checked") == 0;

                user = new Admin(firstName, middleName, lastName, emailId, gender, age, mobileNumber, password, isActive);
            }
        }

        if (user != null) {
            address = new Address(addressLineOne, addressLineTwo, addressLineThree, "landMark", city, state, "India", pinCode);
            user.setAddress(address);
            if (user instanceof Customer) {
                isUserSignedUp = service.registerCustomer((Customer) user);
            } else if (user instanceof Employee) {
                isUserSignedUp = service.registerEmployee((Employee) user, branchId);
            } else {
                isUserSignedUp = service.registerAdmin((Admin) user);
            }
        }

        if (!isUserSignedUp) {
            if (user instanceof Customer) {
                retVal= "errorCustomerSignUp";
            } else {
                retVal= "errorNonCustomerSignUp";
            }
        } else {
            if (user instanceof Customer) {
                retVal= "successCustomerSignUp";
            } else {
                retVal= "successNonCustomerSignUp";
            }
        }
        return retVal;
    }
}
