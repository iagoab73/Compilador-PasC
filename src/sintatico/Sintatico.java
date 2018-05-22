package sintatico;

import lexico.Tipo;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author yago
 */
public class Sintatico {

    static HashMap<Integer, Estado> estados;
    static Stack<Integer> pilha;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        estados = new HashMap<>();
        estados.put(1, new Estado(Tipo.P, 1, new Path[]{new Path(Tipo.KW_PROGRAM, 2)}, new Path[]{}));
        estados.put(2, new Estado(Tipo.P, 2, new Path[]{new Path(Tipo.ID, 3)}, new Path[]{}));
        estados.put(3, new Estado(Tipo.P, 3, new Path[]{new Path(Tipo.VAZIO, 7), new Path(Tipo.KW_NUM, 9), new Path(Tipo.KW_CHAR, 10)}, new Path[]{new Path(Tipo.E, 4), new Path(Tipo.DL, 5), new Path(Tipo.D, 6), new Path(Tipo.TY, 8)}));
        estados.put(4, new Estado(Tipo.P, 4, new Path[]{new Path(Tipo.EOF, 0)}, new Path[]{}));
        estados.put(5, new Estado(Tipo.B, 5, new Path[]{new Path(Tipo.SMB_OBC, 11)}, new Path[]{}));
        estados.put(6, new Estado(Tipo.DL, 6, new Path[]{new Path(Tipo.SMB_SEM, 12)}, new Path[]{}));
        estados.put(7, new Estado(Tipo.DL, 7, new Path[]{}, new Path[]{}));
        estados.put(8, new Estado(Tipo.D, 8, new Path[]{new Path(Tipo.ID, 14)}, new Path[]{new Path(Tipo.IL, 13)}));
        estados.put(9, new Estado(Tipo.TY, 9, new Path[]{}, new Path[]{}));
        estados.put(10, new Estado(Tipo.TY, 10, new Path[]{}, new Path[]{}));
        estados.put(11, new Estado(Tipo.B, 11, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 15), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
        estados.put(12, new Estado(Tipo.DL, 12, new Path[]{new Path(Tipo.VAZIO, 7), new Path(Tipo.KW_NUM, 9), new Path(Tipo.KW_CHAR, 10)}, new Path[]{new Path(Tipo.DL, 23), new Path(Tipo.D, 6), new Path(Tipo.TY, 8)}));
        estados.put(13, new Estado(Tipo.D, 13, new Path[]{}, new Path[]{}));
        estados.put(14, new Estado(Tipo.IL, 14, new Path[]{new Path(Tipo.SMB_COM, 36), new Path(Tipo.VAZIO, 37)}, new Path[]{new Path(Tipo.IL_, 35)}));
        estados.put(15, new Estado(Tipo.B, 15, new Path[]{new Path(Tipo.SMB_CBC, 38)}, new Path[]{}));
        estados.put(16, new Estado(Tipo.SL, 16, new Path[]{new Path(Tipo.SMB_SEM, 39)}, new Path[]{}));
        estados.put(17, new Estado(Tipo.SL, 17, new Path[]{}, new Path[]{}));
        estados.put(18, new Estado(Tipo.S, 18, new Path[]{}, new Path[]{}));
        estados.put(19, new Estado(Tipo.S, 19, new Path[]{}, new Path[]{}));
        estados.put(20, new Estado(Tipo.S, 20, new Path[]{}, new Path[]{}));
        estados.put(21, new Estado(Tipo.S, 21, new Path[]{}, new Path[]{}));
        estados.put(22, new Estado(Tipo.S, 22, new Path[]{}, new Path[]{}));
        estados.put(23, new Estado(Tipo.DL, 23, new Path[]{}, new Path[]{}));
        estados.put(24, new Estado(Tipo.AS, 24, new Path[]{new Path(Tipo.OP_ASS, 40)}, new Path[]{}));
        estados.put(25, new Estado(Tipo.IS, 25, new Path[]{new Path(Tipo.SMB_OPA, 41)}, new Path[]{}));
        estados.put(26, new Estado(Tipo.WHS, 26, new Path[]{new Path(Tipo.SMB_OBC, 42)}, new Path[]{}));
        estados.put(27, new Estado(Tipo.RS, 27, new Path[]{new Path(Tipo.ID, 43)}, new Path[]{}));
        estados.put(28, new Estado(Tipo.WRS, 28, new Path[]{new Path(Tipo.LIT, 51), new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.W, 44), new Path(Tipo.SE, 50), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(29, new Estado(Tipo.SP, 29, new Path[]{new Path(Tipo.SMB_OPA, 45)}, new Path[]{}));
        estados.put(35, new Estado(Tipo.IL, 35, new Path[]{}, new Path[]{}));
        estados.put(36, new Estado(Tipo.IL_, 36, new Path[]{new Path(Tipo.ID, 14)}, new Path[]{new Path(Tipo.IL, 46)}));
        estados.put(37, new Estado(Tipo.IL_, 37, new Path[]{}, new Path[]{}));
        estados.put(38, new Estado(Tipo.B, 38, new Path[]{}, new Path[]{}));
        estados.put(39, new Estado(Tipo.SL, 39, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 49), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
        estados.put(40, new Estado(Tipo.AS, 40, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.SE, 48), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(41, new Estado(Tipo.IS, 41, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.CN, 56), new Path(Tipo.E, 57), new Path(Tipo.SE, 58), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(42, new Estado(Tipo.WHS, 42, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 30), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
        estados.put(43, new Estado(Tipo.RS, 43, new Path[]{}, new Path[]{}));
        estados.put(44, new Estado(Tipo.WRS, 44, new Path[]{}, new Path[]{}));
    }

    public static void shift() {

    }

}
