/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.supermarket.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.supermarket.db.DBConnection;
import lk.ijse.supermarket.model.Customer;

/**
 *
 * @author DILLKI COORAY
 */
public class CustomerController {
    public static boolean addCustomer(Customer c1) throws ClassNotFoundException, SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Insert into Customer Values(?,?,?,?,?,?,?)");
        stm.setObject(1, c1.getId());
        stm.setObject(2, c1.getTitle());
        stm.setObject(3, c1.getName());
        stm.setObject(4, c1.getAddress());
        stm.setObject(5, c1.getCity());
        stm.setObject(6, c1.getProvince());
        stm.setObject(7, c1.getPostalCode());
        
        int res = stm.executeUpdate();
        return res>0;
    }
    
    public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Update Customer set CustTitle=?, CustName=?, CustAddress=?,City=?,Province=?,PostalCode=? where CustId=?");
        stm.setObject(1, customer.getTitle());
        stm.setObject(2, customer.getName());
        stm.setObject(3, customer.getAddress());
        stm.setObject(4, customer.getCity());
        stm.setObject(5, customer.getProvince());
        stm.setObject(6, customer.getPostalCode());
        stm.setObject(7, customer.getId());
        return stm.executeUpdate()>0;
    }
    
    public static Customer searchCustomer(String id) throws ClassNotFoundException, SQLException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Select * From Customer where CustId=?");
        stm.setObject(1, id);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
            Customer customer=new Customer(rst.getString("CustId"), rst.getString("CustTitle"), rst.getString("CustName"), rst.getString("CustAddress"),rst.getString("City"),rst.getString("Province"),rst.getString("PostalCode"));
            return customer;
        }
        return null;
    }
    
     public static boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException{
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Customer where CustId='"+id+"'")>0;
    }
     
     public static ArrayList<Customer>getAllCustomers() throws ClassNotFoundException, SQLException{
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * From Customer");
        ArrayList <Customer>customerList=new ArrayList<>();
        while(rst.next()){
            Customer customer=new Customer(rst.getString("CustId"), rst.getString("CustTitle"), rst.getString("CustName"), rst.getString("CustAddress"),rst.getString("City"),rst.getString("Province"),rst.getString("PostalCode"));
            customerList.add(customer);
        }
        return customerList;
    }
     
     
}
