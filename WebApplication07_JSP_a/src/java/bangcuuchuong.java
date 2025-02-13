/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trong
 */
public class bangcuuchuong {
    public static void main(String[] args) {
        for(int i = 2; i <= 9; i++){
            System.out.println("Bảng cửu chương "+i);
            for(int j = 1; j <= 10; j++){
                System.out.println(i+"*"+j+"= "+i*j);
            }
        }
    }
}
