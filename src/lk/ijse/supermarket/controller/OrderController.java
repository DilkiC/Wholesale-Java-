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
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.supermarket.db.DBConnection;
import lk.ijse.supermarket.model.Item;
import lk.ijse.supermarket.model.Orders;

/**
 *
 * @author DILLKI COORAY
 */
public class OrderController {
    public static boolean addOrder(Orders orders) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement("Insert into Orders values(?,?,?)");
            stm.setObject(1, orders.getOrderId());
            stm.setObject(2, orders.getOrderDate());
            stm.setObject(3, orders.getCustomerId());
            boolean isAddedOrder = stm.executeUpdate() > 0;
            if (isAddedOrder) {
                boolean isAddedOrderDetail = OrderDetailController.addOrderDetail(orders.getOrderDetailList());
                if (isAddedOrderDetail) {
                    boolean isUpdate = ItemController.updateStock(orders.getOrderDetailList());
                    if (isUpdate) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();  //if not(else)send all the data to database rollback data (delete)
            return false;
        } finally {                                                     //try-finally to run another code with - java code
               connection.setAutoCommit(true);
        }
    }
    
    /*public static ArrayList<Orders> getOrderDate() throws ClassNotFoundException, SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Orders");

        ArrayList<Orders> alItems = new ArrayList<>();

        while (rst.next()) {

            Orders orders = new Orders();
            orders.setOrderId(rst.getString(1));
            orders.setOrderDate(rst.getString(2));
            orders.setCustomerId(rst.getString(3));
            //orders.setOrderDetailList(rst.getOrderDetail(4));

            alItems.add(orders);
        }

        return alItems;

    }*/
    
    
}
