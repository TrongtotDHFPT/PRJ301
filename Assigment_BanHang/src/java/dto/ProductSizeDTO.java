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
public class ProductSizeDTO {
    private int id;
    private int product_id;
    private String size;
    private int stock_quantity;

    public ProductSizeDTO() {
    }

    public ProductSizeDTO(int id, int product_id, String size, int stock_quantity) {
        this.id = id;
        this.product_id = product_id;
        this.size = size;
        this.stock_quantity = stock_quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    @Override
    public String toString() {
        return "ProductSizeDTO{" + "id=" + id + ", product_id=" + product_id + ", size=" + size + ", stock_quantity=" + stock_quantity + '}';
    }
    
    
}
