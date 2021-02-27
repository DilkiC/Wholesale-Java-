/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.supermarket.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.supermarket.db.DBConnection;
import lk.ijse.supermarket.model.OrderDetail;

/**
 *
 * @author DILLKI COORAY
 */
// if order have two or many items
public class OrderDetailController {
    public static boolean addOrderDetail(ArrayList<OrderDetail> orderDetailList) throws ClassNotFoundException, SQLException{
        for (OrderDetail orderDetail : orderDetailList) {
            boolean isAddOrderDetail = addOrderDetail(orderDetail);
            if(!isAddOrderDetail){return false;}
        }
        return true;
    }
    
    //if order has one item
    public static boolean addOrderDetail(OrderDetail orderDetail) throws ClassNotFoundException, SQLException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Insert into OrderDetail Values(?,?,?,?)");
        stm.setObject(1,orderDetail.getOrderId());
        stm.setObject(2,orderDetail.getItemCode());
        stm.setObject(3,orderDetail.getOrderQty());
        stm.setObject(4,orderDetail.getUnitPrice());
        return stm.executeUpdate()>0;
        
    }
    
}
