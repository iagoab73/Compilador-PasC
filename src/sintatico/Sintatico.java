/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintatico;

import java.util.HashMap;

/**
 *
 * @author yago
 */
public class Sintatico {

    static HashMap<Integer, Estado> estados;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        estados = new HashMap<>();
        estados.put(1, new Estado(1,new String[]{"asd"},new int[]{2},new int[][]{}));
    }
    
}
