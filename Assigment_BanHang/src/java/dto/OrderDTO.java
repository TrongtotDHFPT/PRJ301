/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;

/**
 *
 * @author trong
 */
public class OrderDTO {

    private int order_id;
    private int user_id;
    private int total_amount;
    private String status;
    private Date created_at;

    public OrderDTO() {
    }

    public OrderDTO(int order_id, int user_id, int total_amount, String status, Date created_at) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.total_amount = total_amount;
        this.status = status;
        this.created_at = created_at;
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

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "order_id=" + order_id + ", user_id=" + user_id + ", total_amount=" + total_amount + ", status=" + status + ", created_at=" + created_at + '}';
    }

}
