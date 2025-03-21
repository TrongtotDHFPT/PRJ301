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
public class CategoryDTO {

    private int categor_id;
    private String categor_name;

    public CategoryDTO(int categor_id, String categor_name) {
        this.categor_id = categor_id;
        this.categor_name = categor_name;
    }

    public int getCategor_id() {
        return categor_id;
    }

    public void setCategor_id(int categor_id) {
        this.categor_id = categor_id;
    }

    public String getCategor_name() {
        return categor_name;
    }

    public void setCategor_name(String categor_name) {
        this.categor_name = categor_name;
    }

    
}
