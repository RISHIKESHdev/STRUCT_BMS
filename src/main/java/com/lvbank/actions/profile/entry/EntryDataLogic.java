package com.lvbank.actions.profile.entry;

import com.lvbank.actions.navigate.NavigateService;
import com.lvbank.MySQLConnection;
import com.lvbank.model.navigate.Address;
import com.lvbank.model.profile.*;

import java.sql.*;
import java.util.ArrayList;

public class EntryDataLogic {
    Customer checkCredentialAndGetCustomer(String userName,String password){
        Customer customer=null;
        Address address;

        try(Connection connection = MySQLConnection.connect()){
            if(connection!=null){
                try(PreparedStatement ps = connection.prepareStatement(EntrySQLQuery.SELECT_CUSTOMER_ON_LOGIN)){
                    ps.setString(1, userName);
                    ps.setString(2,password);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        address=new Address(rs.getString("address_Line_One"),rs.getString("address_Line_Two"),rs.getString("address_Line_Three"),rs.getString("landmark"),rs.getString("city"),rs.getString("state"),rs.getString("country"),rs.getString("pinCode"));
                        customer=new Customer(rs.getString("first_Name"),rs.getString("middle_Name"),rs.getString("last_Name"),rs.getString("email_Id"),rs.getString("gender"),rs.getInt("age"),rs.getString("mobile_Number"),rs.getString("password"),rs.getString("PAN_Number"),rs.getString("CKYC_Verification_Document"),rs.getString("CKYC_Verification_Id"));
                        customer.setAddress(address);
                        customer.setCIFNumber(rs.getDouble("CIF_Number"));
                    }
                }
            }
        } catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return customer;
    }
    Employee checkCredentialAndGetEmployee(String userName,String password){
        Employee employee=null;
        Address address;

        try(Connection connection = MySQLConnection.connect()){
            if(connection!=null){
                try(PreparedStatement ps = connection.prepareStatement(EntrySQLQuery.SELECT_EMPLOYEE_ON_LOGIN)){
                    ps.setString(1,userName);
                    ps.setString(2,password);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        address=new Address(rs.getString("address_Line_One"),rs.getString("address_Line_Two"),rs.getString("address_Line_Three"),rs.getString("landmark"),rs.getString("city"),rs.getString("state"),rs.getString("country"),rs.getString("pinCode"));
                        employee=new Employee(rs.getString("first_Name"),rs.getString("middle_Name"),rs.getString("last_Name"),rs.getString("email_Id"),rs.getString("gender"),rs.getInt("age"),rs.getString("mobile_Number"),rs.getString("password"),rs.getString("employee_Designation"),rs.getDouble("employee_CTC"), rs.getInt("year_Of_Experience"),rs.getBoolean("is_Active"));
                        employee.setAddress(address);
                        employee.setEmployeeId(rs.getDouble("Employee_Id"));
                    }
                }
            }
        } catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return employee;
    }
    Admin checkCredentialAndGetAdmin(String userName, String password){
        Admin admin=null;
        Address address;

        try(Connection connection = MySQLConnection.connect()){
            if(connection!=null){
                try(PreparedStatement ps = connection.prepareStatement(EntrySQLQuery.SELECT_ADMIN_ON_LOGIN)){
                    ps.setString(1,userName);
                    ps.setString(2,password);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        address=new Address(rs.getString("address_Line_One"),rs.getString("address_Line_Two"),rs.getString("address_Line_Three"),rs.getString("landmark"),rs.getString("city"),rs.getString("state"),rs.getString("country"),rs.getString("pinCode"));
                        admin=new Admin(rs.getString("first_Name"),rs.getString("middle_Name"),rs.getString("last_Name"),rs.getString("email_Id"),rs.getString("gender"),rs.getInt("age"),rs.getString("mobile_Number"),rs.getString("password"),rs.getBoolean("is_Active"));
                        admin.setAddress(address);
                    }
                }
            }
        } catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return admin;
    }
    private boolean insertUserRecord(Connection connection, User user){
        int addressId=0;
        boolean isUserRecordInserted=false;

        NavigateService navigate = new NavigateService();

        addressId=navigate.getIdOnInsertAddressRecord(connection,user.getAddress());
        if(addressId!=0){
            try(PreparedStatement ps = connection.prepareStatement(EntrySQLQuery.INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getMiddleName());
                ps.setString(3, user.getLastName());
                ps.setInt(4, addressId);
                ps.setString(5, user.getEmailId());
                ps.setString(6, user.getGender());
                ps.setString(7, user.getPassword());
                ps.setInt(8, user.getAge());
                ps.setString(9, user.getMobileNumber());
                int rs = ps.executeUpdate();
                if(rs>0){
                    ResultSet rSet=ps.getGeneratedKeys();
                    rSet.next();
                    user.setUserId(rSet.getInt(1));
                    isUserRecordInserted=true;
                }
            }catch(NullPointerException | SQLException e){
                System.out.println(e.getMessage());
            }
        }

        return isUserRecordInserted;
    }
    boolean insertCustomer(Customer customer){
        int userId;
        double CIFNumber;
        boolean isCustomerInserted=false;

        try(Connection connection = MySQLConnection.connect()) {
            if(connection!=null){
                connection.setAutoCommit(false);
                if(insertUserRecord(connection,customer)){
                    userId=customer.getUserId();
                    try(PreparedStatement ps = connection.prepareStatement(EntrySQLQuery.INSERT_CUSTOMER_QUERY, Statement.RETURN_GENERATED_KEYS)){
                        ps.setInt(1,userId);
                        ps.setString(2,customer.getCKYCVerificationDocument());
                        ps.setString(3,customer.getCKYCVerificationId());
                        ps.setString(4, customer.getPANNumber());
                        int rs = ps.executeUpdate();
                        if(rs>0){
                            ResultSet rSet=ps.getGeneratedKeys();
                            rSet.next();
                            CIFNumber = rSet.getDouble(1);
                            customer.setCIFNumber(CIFNumber);
                            isCustomerInserted=true;
                        }
                    }
                }
                if(!isCustomerInserted) connection.rollback();
                else connection.commit();
            }
        }catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return isCustomerInserted;
    }
    boolean insertEmployee(Employee employee,int branchId){
        int userId,rs1=0;
        double employeeId = 0;
        boolean isEmployeeInserted=false;

        try(Connection connection = MySQLConnection.connect()) {
            if(connection != null){
                connection.setAutoCommit(false);
                if(insertUserRecord(connection,employee)){
                    userId=employee.getUserId();
                    if(branchId!=0){
                        try(PreparedStatement ps = connection.prepareStatement(EntrySQLQuery.INSERT_EMPLOYEE_QUERY, Statement.RETURN_GENERATED_KEYS)){
                            ps.setInt(1,userId);
                            ps.setString(2,employee.getEmployeeDesignation());
                            ps.setDouble(3,employee.getEmployeeCTC());
                            ps.setInt(4, employee.getYearOfExperience());
                            ps.setBoolean(5, employee.isActive());
                            int rs = ps.executeUpdate();
                            if(rs>0){
                                ResultSet rSet = ps.getGeneratedKeys();
                                rSet.next();
                                employeeId = rSet.getDouble(1);
                                employee.setEmployeeId(employeeId);
                            }
                        }
                        try(PreparedStatement ps = connection.prepareStatement(EntrySQLQuery.INSERT_EMPLOYEE_BRANCH_MAP)){
                            ps.setDouble(1,employeeId);
                            ps.setInt(2,branchId);
                            rs1=ps.executeUpdate();
                        }
                    }
                }
                if(rs1>0 && employeeId >= 10000000000D){
                    connection.commit();
                    isEmployeeInserted=true;
                }else{
                    connection.rollback();
                }
            }
        } catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return isEmployeeInserted;
    }
    boolean insertAdmin(Admin admin){
        int userId,rs = 0;
        boolean isAdminInserted=false;

        try(Connection connection = MySQLConnection.connect()) {
            if(connection != null){
                connection.setAutoCommit(false);
                if(insertUserRecord(connection,admin)){
                    userId=admin.getUserId();
                    try(PreparedStatement ps = connection.prepareStatement(EntrySQLQuery.INSERT_ADMIN_QUERY)){
                        ps.setInt(1,userId);
                        ps.setBoolean(2,admin.isActive());
                        rs = ps.executeUpdate();
                    }
                }
                if(rs > 0){
                    connection.commit();
                    isAdminInserted=true;
                }else{
                    connection.rollback();
                }
            }
        } catch(NullPointerException | SQLException e){
            System.out.println(e.getMessage());
        }

        return isAdminInserted;
    }








    ArrayList<Double> customerAccountNumber(double CIFNumber){
        ArrayList<Double> customerAccountNumbers=new ArrayList<>();

        try(Connection connection = MySQLConnection.connect()){
            if(connection!=null){
                try(PreparedStatement ps = connection.prepareStatement(EntrySQLQuery.SELECT_CUSTOMER_ACCOUNTS_QUERY)){
                    ps.setDouble(1,CIFNumber);
                    ResultSet rs=ps.executeQuery();
                    while(rs.next()){
                        customerAccountNumbers.add(rs.getDouble("account_Number"));
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return customerAccountNumbers;
    }
}
