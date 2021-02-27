/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.supermarket.model;

import java.util.ArrayList;

/**
 *
 * @author DILLKI COORAY
 */
public class Orders {
    private String orderId;
    private String orderDate;
     private String customerId;
     private ArrayList< OrderDetail>orderDetailList;

    public Orders(String orderId, String orderDate, String customerId, ArrayList<OrderDetail> orderDetailList) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.orderDetailList = orderDetailList;
    }

    public Orders() {
    }

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the orderDetailList
     */
    public ArrayList< OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    /**
     * @param orderDetailList the orderDetailList to set
     */
    public void setOrderDetailList(ArrayList< OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
    
}
