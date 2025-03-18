/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author trong
 */
public class OrderDTO {
    private int order_id;
    private int user_id;
    private double totalPrice;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(int order_id, int user_id, double totalPrice, String status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getTotal_price() {
        return totalPrice;
    }

    public void setTotal_price(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "order_id=" + order_id + ", user_id=" + user_id + ", totalPrice=" + totalPrice + ", status=" + status + '}';
    }
    
}
