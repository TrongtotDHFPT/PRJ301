/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.AccountDAO;
import dto.AccountDTO;
import java.util.List;

/**
 *
 * @author trong
 */
public class AuthUtils {
    public static final String ADMIN_ROLE = "ADMIN";
    
    public static boolean isNotUsernameAvailable(String strUsername){
        
        AccountDAO acDAO = new AccountDAO();
        AccountDTO account = acDAO.readByUsername(strUsername);
        
        return account == null;
    }
    
    public static boolean isValidLogin(String strUsername , String strPassword){
        AccountDTO account = getAccount(strUsername);
        return account!= null &&(account.getPassWord().equals(strPassword));
    }
    
    public  static AccountDTO getAccount(String strUsername){
        AccountDAO acDAO =  new AccountDAO();
        AccountDTO account = acDAO.readByUsername(strUsername);
        return account ;
    }
    
    
    
}
