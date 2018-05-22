package sintatico;

import AnalisadorLexico.Tipo;
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
        estados.put(1, new Estado(1,new Tipo[]{Tipo.KW_PROGRAM},new int[]{2},new Tipo[]{},new int[]{}));
        estados.put(2, new Estado(2,new Tipo[]{Tipo.ID},new int[]{3},new Tipo[]{},new int[]{}));
        estados.put(3, new Estado(3,new Tipo[]{Tipo.VAZIO,Tipo.KW_NUM,Tipo.KW_CHAR},new int[]{7,9,10},new Tipo[]{Tipo.B,Tipo.DL,Tipo.D,Tipo.TY,},new int[]{4,5,6,8}));
        estados.put(2, new Estado(2,new Tipo[]{Tipo.ID},new int[]{3},new Tipo[]{},new int[]{}));
        
    }
    
    public static void shift(){
        
    }
    
}
