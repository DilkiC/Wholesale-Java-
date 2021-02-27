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
import lk.ijse.supermarket.model.Customer;
import lk.ijse.supermarket.model.Item;
import lk.ijse.supermarket.model.OrderDetail;

/**
 *
 * @author DILLKI COORAY
 */
public class ItemController {
    
    public static boolean addItem(Item item) throws ClassNotFoundException, SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?)");

        pstm.setObject(1, item.getCode());
        pstm.setObject(2, item.getDescription());
        pstm.setObject(3, item.getUnitPrice());
        pstm.setObject(4, item.getQtyOnHand());

        int affectedRows = pstm.executeUpdate();

        return affectedRows > 0;

    }
    
    public static ArrayList<Item> loadAllItems() throws ClassNotFoundException, SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");

        ArrayList<Item> alItems = new ArrayList<>();

        while (rst.next()) {

            Item item = new Item();
            item.setCode(rst.getString(1));
            item.setDescription(rst.getString(2));
            item.setUnitPrice(rst.getDouble(3));
            item.setQtyOnHand(rst.getInt(4));

            alItems.add(item);
        }

        return alItems;

    }
    
    public static Item searchItem(String itemCode) throws ClassNotFoundException, SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item WHERE ItemCode='" + itemCode + "'");

        if (rst.next()) {
            Item item = new Item();
            item.setCode(rst.getString(1));
            item.setDescription(rst.getString(2));
            item.setUnitPrice(rst.getDouble(3));
            item.setQtyOnHand(rst.getInt(4));

            return item;
        } else {
            return null;
        }

    }
    
    //if order have many orders
    public static boolean updateStock(ArrayList<OrderDetail> orderDetailList) throws ClassNotFoundException, SQLException {
        for (OrderDetail orderDetail : orderDetailList) {
            boolean isUpdateStock = updateStock(orderDetail);//method overload
            if(!isUpdateStock){ //if not update at least one 
                return false;
            }
        }
        return true; //if update all
    }
    
    //if order has one item
    public static boolean updateStock(OrderDetail orderDetail) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Update Item set qtyOnHand=qtyOnHand-? where ItemCode=?");
        stm.setObject(1, orderDetail.getOrderQty());
        stm.setObject(2, orderDetail.getItemCode());
        return stm.executeUpdate()>0;
    }
    
     /*public static boolean updateItem(Item customer) throws ClassNotFoundException, SQLException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Update item set  Description=?, UnitPrice=?,QtyOnHand=?");
        //stm.setObject(1, customer.getCode());
        stm.setObject(1, customer.getDescription());
        stm.setObject(2, customer.getUnitPrice());
        stm.setObject(3, customer.getQtyOnHand());
        
        return stm.executeUpdate()>0;
    }*/
     
      public static boolean deleteItem(String id) throws ClassNotFoundException, SQLException{
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Item where ItemCode='"+id+"'")>0;
    }
      
      public static ArrayList<Item>getAllItem() throws ClassNotFoundException, SQLException{
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * From Item");
        ArrayList <Item>customerList=new ArrayList<>();
        while(rst.next()){
            Item customer=new Item(rst.getString("ItemCode"), rst.getString("Description"), rst.getDouble("UnitPrice"), rst.getInt("QtyOnHand"));
            customerList.add(customer);
        }
        return customerList;
    }
    
}
