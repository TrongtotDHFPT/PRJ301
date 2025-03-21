
import dao.CategoryDAO;
import dto.CategoryDTO;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trong
 */
public class TestMain {
    public static void main(String[] args) {
        CategoryDAO cdao = new CategoryDAO();
            List<CategoryDTO> cateList = cdao.readAll();
            for (CategoryDTO categoryDTO : cateList) {
                System.out.println(categoryDTO.getCategor_name());
            }
    }
}
