package sintatico;

import lexico.Tipo;
import java.util.HashMap;
import java.util.Stack;
import lexico.Lexico;
import lexico.Token;

/**
 *
 * @author Iago Arantes, Jean Silva
 */
public class Sintatico {

    static Lexico lexico;
    static HashMap<Integer, Estado> estados;
    static Stack<Integer> pilha;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        estados = new HashMap<>();
        estados.put(1, new Estado(Tipo.P, 1, new Path[]{new Path(Tipo.KW_PROGRAM, 2)}, new Path[]{}));
        estados.put(2, new Estado(Tipo.P, 2, new Path[]{new Path(Tipo.ID, 3)}, new Path[]{}));
        estados.put(3, new Estado(Tipo.P, 3, new Path[]{new Path(Tipo.VAZIO, 7), new Path(Tipo.KW_NUM, 9), new Path(Tipo.KW_CHAR, 10)}, new Path[]{new Path(Tipo.B, 4), new Path(Tipo.DL, 5), new Path(Tipo.D, 6), new Path(Tipo.TY, 8)}));
        estados.put(4, new Estado(Tipo.P, 4, new Path[]{new Path(Tipo.EOF, 0)}, new Path[]{}));
        estados.put(5, new Estado(Tipo.B, 5, new Path[]{new Path(Tipo.SMB_OBC, 11)}, new Path[]{}));
        estados.put(6, new Estado(Tipo.DL, 6, new Path[]{new Path(Tipo.SMB_SEM, 12)}, new Path[]{}));
        estados.put(7, new Estado(Tipo.DL, 7, 1));
        estados.put(8, new Estado(Tipo.D, 8, new Path[]{new Path(Tipo.ID, 14)}, new Path[]{new Path(Tipo.IL, 13)}));
        estados.put(9, new Estado(Tipo.TY, 9, 1));
        estados.put(10, new Estado(Tipo.TY, 10, 1));
        estados.put(11, new Estado(Tipo.B, 11, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 15), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
        estados.put(12, new Estado(Tipo.DL, 12, new Path[]{new Path(Tipo.VAZIO, 7), new Path(Tipo.KW_NUM, 9), new Path(Tipo.KW_CHAR, 10)}, new Path[]{new Path(Tipo.DL, 23), new Path(Tipo.D, 6), new Path(Tipo.TY, 8)}));
        estados.put(13, new Estado(Tipo.D, 13, 2));
        estados.put(14, new Estado(Tipo.IL, 14, new Path[]{new Path(Tipo.SMB_COM, 36), new Path(Tipo.VAZIO, 37)}, new Path[]{new Path(Tipo.IL_, 35)}));
        estados.put(15, new Estado(Tipo.B, 15, new Path[]{new Path(Tipo.SMB_CBC, 38)}, new Path[]{}));
        estados.put(16, new Estado(Tipo.SL, 16, new Path[]{new Path(Tipo.SMB_SEM, 39)}, new Path[]{}));
        estados.put(17, new Estado(Tipo.SL, 17, 1));
        estados.put(18, new Estado(Tipo.S, 18, 1));
        estados.put(19, new Estado(Tipo.S, 19, 1));
        estados.put(20, new Estado(Tipo.S, 20, 1));
        estados.put(21, new Estado(Tipo.S, 21, 1));
        estados.put(22, new Estado(Tipo.S, 22, 1));
        estados.put(23, new Estado(Tipo.DL, 23, 3));
        estados.put(24, new Estado(Tipo.AS, 24, new Path[]{new Path(Tipo.OP_ASS, 40)}, new Path[]{}));
        estados.put(25, new Estado(Tipo.IS, 25, new Path[]{new Path(Tipo.SMB_OPA, 41)}, new Path[]{}));
        estados.put(26, new Estado(Tipo.WHS, 26, new Path[]{new Path(Tipo.SMB_OBC, 42)}, new Path[]{}));
        estados.put(27, new Estado(Tipo.RS, 27, new Path[]{new Path(Tipo.ID, 43)}, new Path[]{}));
        estados.put(28, new Estado(Tipo.WRS, 28, new Path[]{new Path(Tipo.LIT, 51), new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.W, 44), new Path(Tipo.SE, 50), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(29, new Estado(Tipo.SP, 29, new Path[]{new Path(Tipo.SMB_OPA, 45)}, new Path[]{}));
        estados.put(30, new Estado(Tipo.WHS, 30, new Path[]{new Path(Tipo.SMB_CBC, 88)}, new Path[]{}));
        estados.put(35, new Estado(Tipo.IL, 35, 2));
        estados.put(36, new Estado(Tipo.IL_, 36, new Path[]{new Path(Tipo.ID, 14)}, new Path[]{new Path(Tipo.IL, 46)}));
        estados.put(37, new Estado(Tipo.IL_, 37, 1));
        estados.put(38, new Estado(Tipo.B, 38, 4));
        estados.put(39, new Estado(Tipo.SL, 39, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 49), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
        estados.put(40, new Estado(Tipo.AS, 40, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.SE, 48), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(41, new Estado(Tipo.IS, 41, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.CN, 56), new Path(Tipo.E, 57), new Path(Tipo.SE, 58), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(42, new Estado(Tipo.WHS, 42, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 30), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
        estados.put(43, new Estado(Tipo.RS, 43, 2));
        estados.put(44, new Estado(Tipo.WRS, 44, 2));
        estados.put(45, new Estado(Tipo.SP, 45, new Path[]{new Path(Tipo.KW_NOT,54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.CN, 59), new Path(Tipo.E, 57),new Path(Tipo.SE, 58), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(46, new Estado(Tipo.IL_, 46, 2));
        estados.put(47, new Estado(Tipo.SL, 47, 3));
        estados.put(48, new Estado(Tipo.AS, 48, 3));
        estados.put(49, new Estado(Tipo.SE, 49, new Path[]{new Path(Tipo.VAZIO, 62), new Path(Tipo.OP_AD, 63), new Path(Tipo.OP_MIN, 64), new Path(Tipo.KW_OR, 65)}, new Path[]{new Path(Tipo.SE_, 60), new Path(Tipo.A, 61)}));
        estados.put(50, new Estado(Tipo.W, 50, 1));
        estados.put(51, new Estado(Tipo.W, 51, 1));
        estados.put(52, new Estado(Tipo.TE, 52, new Path[]{new Path(Tipo.VAZIO, 68), new Path(Tipo.OP_MUL, 69), new Path(Tipo.OP_DIV, 70), new Path(Tipo.KW_AND, 71)}, new Path[]{new Path(Tipo.TE_, 66), new Path(Tipo.M, 67)}));
        estados.put(53, new Estado(Tipo.FA, 53, new Path[]{new Path(Tipo.ID, 73), new Path(Tipo.SMB_OPA, 75), new Path(Tipo.CON_NUM, 76), new Path(Tipo.CON_CHAR, 77)}, new Path[]{new Path(Tipo.F, 72), new Path(Tipo.CT, 74)}));
        estados.put(54, new Estado(Tipo.FA_, 54, 1));
        estados.put(55, new Estado(Tipo.FA_, 55, 1));
        estados.put(56, new Estado(Tipo.IS, 56, new Path[]{new Path(Tipo.SMB_CPA, 78)}, new Path[]{}));
        estados.put(57, new Estado(Tipo.CN, 57, 1));
        estados.put(58, new Estado(Tipo.E, 58, new Path[]{new Path(Tipo.VAZIO, 81), new Path(Tipo.OP_EQ, 82), new Path(Tipo.OP_GT, 83), new Path(Tipo.OP_GE, 84), new Path(Tipo.OP_LT, 85), new Path(Tipo.OP_LE, 86), new Path(Tipo.OP_NE, 87)}, new Path[]{new Path(Tipo.E_, 79), new Path(Tipo.R, 80)}));
        estados.put(59, new Estado(Tipo.SP, 59, new Path[]{new Path(Tipo.SMB_CPA, 89)}, new Path[]{}));
        estados.put(60, new Estado(Tipo.SE, 60, 2));
        estados.put(61, new Estado(Tipo.SE_, 61, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.TE, 90), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(62, new Estado(Tipo.SE_, 62, 1));
        estados.put(63, new Estado(Tipo.A, 63, 1));
        estados.put(64, new Estado(Tipo.A, 64, 1));
        estados.put(65, new Estado(Tipo.A, 65, 1));
        estados.put(66, new Estado(Tipo.TE, 66, 2));
        estados.put(67, new Estado(Tipo.TE_, 67, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.FA, 91), new Path(Tipo.FA_, 53)}));
        estados.put(68, new Estado(Tipo.TE_, 68, 1));
        estados.put(69, new Estado(Tipo.M, 69, 1));
        estados.put(70, new Estado(Tipo.M, 70, 1));
        estados.put(71, new Estado(Tipo.M, 71, 1));
        estados.put(72, new Estado(Tipo.FA, 72, 2));
        estados.put(73, new Estado(Tipo.F, 73, 1));
        estados.put(74, new Estado(Tipo.F, 74, 1));
        estados.put(75, new Estado(Tipo.F, 75, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.E, 92), new Path(Tipo.SE, 58), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(76, new Estado(Tipo.CT, 76, 1));
        estados.put(77, new Estado(Tipo.CT, 77, 1));
        estados.put(78, new Estado(Tipo.IS, 78, new Path[]{new Path(Tipo.SMB_OBC, 93)}, new Path[]{}));
        estados.put(79, new Estado(Tipo.E, 79, 2));
        estados.put(80, new Estado(Tipo.E_, 80, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.SE, 94), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(81, new Estado(Tipo.E_, 81, 1));
        estados.put(82, new Estado(Tipo.R, 82, 1));
        estados.put(83, new Estado(Tipo.R, 83, 1));
        estados.put(84, new Estado(Tipo.R, 84, 1));
        estados.put(85, new Estado(Tipo.R, 85, 1));
        estados.put(86, new Estado(Tipo.R, 86, 1));
        estados.put(87, new Estado(Tipo.R, 87, 1));
        estados.put(88, new Estado(Tipo.WHS, 88, 4));
        estados.put(89, new Estado(Tipo.SP, 89, 4));
        estados.put(90, new Estado(Tipo.SE_, 90, new Path[]{new Path(Tipo.VAZIO, 62), new Path(Tipo.OP_AD, 63), new Path(Tipo.OP_MIN, 64), new Path(Tipo.KW_OR, 65)}, new Path[]{new Path(Tipo.SE_, 95), new Path(Tipo.A, 61)}));
        estados.put(91, new Estado(Tipo.TE_, 91, new Path[]{new Path(Tipo.VAZIO, 68), new Path(Tipo.OP_MUL, 69), new Path(Tipo.OP_DIV, 70), new Path(Tipo.KW_AND, 71)}, new Path[]{new Path(Tipo.TE_, 96), new Path(Tipo.M, 67)}));
        estados.put(92, new Estado(Tipo.F, 92, new Path[]{new Path(Tipo.SMB_CPA, 97)}, new Path[]{}));
        estados.put(93, new Estado(Tipo.IS, 93, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 98), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
        estados.put(94, new Estado(Tipo.E_, 94, 2));
        estados.put(95, new Estado(Tipo.SE_, 95, 3));
        estados.put(96, new Estado(Tipo.TE_, 96, 3));
        estados.put(97, new Estado(Tipo.F, 97, 3));
        estados.put(98, new Estado(Tipo.IS, 98, new Path[]{new Path(Tipo.SMB_CBC, 99)}, new Path[]{}));
        estados.put(99, new Estado(Tipo.IS, 99, new Path[]{new Path(Tipo.KW_ELSE, 101), new Path(Tipo.VAZIO, 102)}, new Path[]{new Path(Tipo.IS_, 100)}));
        estados.put(100, new Estado(Tipo.IS, 100, 8));
        estados.put(101, new Estado(Tipo.IS_, 101, new Path[]{new Path(Tipo.SMB_OBC, 103)}, new Path[]{}));
        estados.put(102, new Estado(Tipo.IS_, 102, 1));
        estados.put(103, new Estado(Tipo.IS_, 103, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 104), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
        estados.put(104, new Estado(Tipo.IS_, 104, new Path[]{new Path(Tipo.SMB_CBC, 105)}, new Path[]{}));
        estados.put(105, new Estado(Tipo.IS_, 105, 4));
        
        
        
        lexico = new Lexico();
        pilha = new Stack<>();
        
        
        Token t = lexico.proximoToken();
        int estadoAtual = 1;
        pilha.push(estadoAtual);
        iniciaEstado(t, estadoAtual);
    }
    
    public static void iniciaEstado(Token t, int estadoAtual){
        Estado e = estados.get(estadoAtual);
        if(e.qntTokens == -1){
            shift(t, estadoAtual);
        }else{
            reduce(t, estadoAtual);
        }
    }

    public static void shift(Token t, int estadoAtual) {
        Estado e = estados.get(estadoAtual);
        Path vazio = null;
        for(Path p : e.shifts){
            if(p.entrada.equals(t.getTipo())){
                if(t.getTipo().equals(Tipo.EOF)){
                    System.out.println("FIM");
                    return;
                }
                estadoAtual = p.saida;
                pilha.push(estadoAtual);
                System.out.println("SHIFT " + estadoAtual);
                t = lexico.proximoToken();
                iniciaEstado(t, estadoAtual);
                return;
            }else if(p.entrada.equals(Tipo.VAZIO)){
                vazio = p;
            }
        }
        if(vazio != null){
            estadoAtual = vazio.saida;
            pilha.push(estadoAtual);
            System.out.println("SHIFT " + estadoAtual);
            iniciaEstado(t, estadoAtual);
        }  
    }
    
    public static void reduce(Token t, int estadoAtual){
        Estado e = estados.get(estadoAtual);
        for(int i = 0; i < e.qntTokens; i++){
            pilha.pop();
        }
        System.out.println("REDUCE " + pilha.peek());
        goTo(e.naoTerminal, pilha.peek(), t);
    }
    
    public static void goTo(Tipo naoTerminal, int estadoAtual, Token t){
        Estado e = estados.get(estadoAtual);
        for(Path p : e.gotos){
            if(p.entrada.equals(naoTerminal)){
                estadoAtual = p.saida;
                pilha.push(estadoAtual);
                System.out.println("GOTO " + estadoAtual);
                iniciaEstado(t, estadoAtual);
                return;
            }
        }
        
    }

}
