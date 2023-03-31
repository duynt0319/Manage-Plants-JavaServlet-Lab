/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

/**
 *
 * @author Duy
 */
public class Order  extends Account{
    private int OrderID;
    private String orderDate;
    private String shipDate;
    private int status;   

    public Order() {
    }

    public Order(int OrderID, String orderDate, String shipDate, int status, int accID) {
        super(accID);
        this.OrderID = OrderID;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
    }

    public Order(int OrderID, String orderDate, String shipDate, int status, int accID, String fullname) {
        super(accID, fullname);
        this.OrderID = OrderID;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
    }

    @Override
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

   
        
    @Override
    public String toString() {
        return "Order{" + "OrderID=" + OrderID + ", orderDate=" + orderDate + ", shipDate=" + shipDate + ", status=" + status + ", accID=" + accID + '}';
    }
    
    
}
