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
public class OrderDetailsDTO {
    private int order_detail_id;
    private int order_id;
    private int product_id;
    private int quantity;
    private int unit_price;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int order_detail_id, int order_id, int product_id, int quantity, int unit_price) {
        this.order_detail_id = order_detail_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" + "order_detail_id=" + order_detail_id + ", order_id=" + order_id + ", product_id=" + product_id + ", quantity=" + quantity + ", unit_price=" + unit_price + '}';
    }
    
    
}
