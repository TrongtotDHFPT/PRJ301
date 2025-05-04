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
    private String title;
    private String author;
    private double price;
    private int stock;
    private String image;
    private int category_id;
    private String description;

    public ProductDTO() {
    }

    public ProductDTO(int product_id, String title, String author, double price, int stock, String image, int category_id, String description) {
        this.product_id = product_id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.category_id = category_id;
        this.description = description;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "product_id=" + product_id + ", title=" + title + ", author=" + author + ", price=" + price + ", stock=" + stock + ", image=" + image + ", category_id=" + category_id + ", description=" + description + '}';
    }
    
    
}
