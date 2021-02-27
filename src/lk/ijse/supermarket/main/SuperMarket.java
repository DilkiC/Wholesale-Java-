/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.supermarket.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DILLKI COORAY
 */
public class SuperMarket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String SQL = "Select * From Customer";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Wholesale", "root", "1234");
            Statement stm = conn.createStatement();
            ResultSet rst = stm.executeQuery(SQL);
            while (rst.next()) { //move first row
                String id = rst.getString("CustId");
                String title = rst.getString("CustTitle");
                String name = rst.getString("CustName");
                String address = rst.getString("CustAddress");
                String city = rst.getString("City");
                String province = rst.getString("Province");
                String postalCode = rst.getString("PostalCode");
                System.out.println(id + "\t" + title + "\t" + name + "\t" + address + "\t" + city + "\t" + province + "\t" + postalCode);
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found...");
            System.out.println(ex.getMessage());
            return;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
