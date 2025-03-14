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
public class ProductDTO {
    private int product_id;
    private String product_name;
    private String description;
    private int price;
    private int stoc_quantity;
    private String image;
    private int category_id;

    public ProductDTO() {
    }

    public ProductDTO(int product_id, String product_name, String description, int price, int stoc_quantity, String image, int category_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.stoc_quantity = stoc_quantity;
        this.image = image;
        this.category_id = category_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStoc_quantity() {
        return stoc_quantity;
    }

    public void setStoc_quantity(int stoc_quantity) {
        this.stoc_quantity = stoc_quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    
}
