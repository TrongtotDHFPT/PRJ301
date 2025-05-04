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
public class CartDTO {

    private int cart_id;
    private int user_id;
    private int product_id;
    private int quantity;
    private Date addedAt;
    private ProductDTO product;

    public CartDTO() {
    }

    public CartDTO(int cart_id, int user_id, int product_id, int quantity, Date addedAt, ProductDTO product) {
        this.cart_id = cart_id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.addedAt = addedAt;
        this.product = product;
    }

    
    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CartDTO{" + "cart_id=" + cart_id + ", user_id=" + user_id + ", product_id=" + product_id + ", quantity=" + quantity + ", addedAt=" + addedAt + ", product=" + product + '}';
    }
    
    
}
