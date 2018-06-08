package sintatico;

import java.util.ArrayList;
import lexico.Tipo;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import lexico.Lexico;
import lexico.Token;

/**
 * Sintatico.java - Classe principal do Analisador Sintatico.
 * @author Iago Arantes, Jean Silva
 */
public class Sintatico {
    /**
     * Nome do arquivo a ser lido pelo programa.
     */
    public static String nomeArquivo = "teste.txt";
    /**
     * Instância do analisador léxico, que dará ao sintático os tokens a serem analisados.
     */
    static Lexico lexico;
    /**
     * Estrutura de dados que guarda os estados correspondentes aos estados do autômato.
     */
    static HashMap<Integer, Estado> estados;
    /**
     * Pilha de estados, que guarda o histórico da movimentação pelo autômato.
     */
    static Stack<Integer> pilha;
    /**
     * Lista de tipos de tokens esperados - Foi necessária a implementação dessa lista para guardar os tokens esperados no caso de erro.
     */
    static List<Tipo> esperados;
    /**
     * Estrutura que guarda os exemplos dos tokens esperados que serão mostrados em caso de erro.
     */
    static HashMap<Tipo, String> exemploEsperados;
    /**
     * Contador de erros sintáticos.
     */
    static int contErro = 0;
    /**
     * Indicador do fim do arquivo.
     */
    static boolean fim = false;
    /**
     * Variável que guarda o token atual.
     */
    static Token t;
       /**
     * Variável que guarda o estado atual do autômato.
     */
    static int estadoAtual;
    /**
     * Variável que guarda uma versão da pilha que pode ser recuperada em caso de erro.
     */
    static Stack<Integer> pilhaBackup;
    
    static List<ItemTela> listaItens;
    
    static ItemTela atual;
    
    static String log;

    /**
     * Método principal da classe Sintático, responsável por inicializar os itens acima e começar a análise.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        insereEstados(); // Cria a estrutura de estados.
        // Analisador léxico é inicializado, juntamente com a pilha e a lista de tokens esperados.
        lexico = new Lexico(nomeArquivo);
        pilha = new Stack<>();
        esperados = new ArrayList<>();
        insereExemplosEsperados(); // Cria a estrutura de exemplos dos tokens esperados.
        listaItens = new ArrayList<>();
        t = lexico.proximoToken();// O primeiro token é recebido, enviado após a análise do analisador léxico.
        ItemTela it = new ItemTela(t.toString());
        atual = it;
        listaItens.add(it);
        estadoAtual = 1; // Variável que guarda o estado atual do autômato, começa com o valor 1 (Estado Inicial).
        pilha.push(estadoAtual); // Insere o estado atual na pilha.
        log = "";
        iniciaEstado();// Inicia as verificações no estado inicial.
    }
    
    /**
     * Método responsável por inserir os estados do autômato na estrutura estados.
     */
    public static void insereEstados(){
        estados = new HashMap<>();// Estados é inicializado e os estados do autômato são inseridos. Na classe Estado e Path é possível ver o que cada um dos parâmetros significa.
        estados.put(1, new Estado(105,31,Tipo.P, 1, new Path[]{new Path(Tipo.KW_PROGRAM, 2)}, new Path[]{}));
        estados.put(2, new Estado(105,219,Tipo.P, 2, new Path[]{new Path(Tipo.ID, 3)}, new Path[]{}));
        estados.put(3, new Estado(406,218,Tipo.P, 3, new Path[]{new Path(Tipo.VAZIO, 7), new Path(Tipo.KW_NUM, 9), new Path(Tipo.KW_CHAR, 10)}, new Path[]{new Path(Tipo.B, 4), new Path(Tipo.DL, 5), new Path(Tipo.D, 6), new Path(Tipo.TY, 8)}));
        estados.put(4, new Estado(406,29,Tipo.P, 4, new Path[]{new Path(Tipo.EOF, 0)}, new Path[]{}));
        estados.put(5, new Estado(847,279,Tipo.B, 5, new Path[]{new Path(Tipo.SMB_OBC, 11)}, new Path[]{}));
        estados.put(6, new Estado(593,490,Tipo.DL, 6, new Path[]{new Path(Tipo.SMB_SEM, 12)}, new Path[]{}));
        estados.put(7, new Estado(721,490,Tipo.DL, 7, 1));
        estados.put(8, new Estado(165,490,Tipo.D, 8, new Path[]{new Path(Tipo.ID, 14)}, new Path[]{new Path(Tipo.IL, 13)}));
        estados.put(9, new Estado(442,490,Tipo.TY, 9, 1));
        estados.put(10, new Estado(300,490,Tipo.TY, 10, 1));
        estados.put(11, new Estado(1215,315,Tipo.B, 11, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 15), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
        estados.put(12, new Estado(445,793,Tipo.DL, 12, new Path[]{new Path(Tipo.VAZIO, 7), new Path(Tipo.KW_NUM, 9), new Path(Tipo.KW_CHAR, 10)}, new Path[]{new Path(Tipo.DL, 23), new Path(Tipo.D, 6), new Path(Tipo.TY, 8)}));
        estados.put(13, new Estado(165,753,Tipo.D, 13, 2));
        estados.put(14, new Estado(167,909,Tipo.IL, 14, new Path[]{new Path(Tipo.SMB_COM, 36), new Path(Tipo.VAZIO, 37)}, new Path[]{new Path(Tipo.IL_, 35)}));
        estados.put(15, new Estado(931,109,Tipo.B, 15, new Path[]{new Path(Tipo.SMB_CBC, 38)}, new Path[]{}));
        estados.put(16, new Estado(2183,1019,Tipo.SL, 16, new Path[]{new Path(Tipo.SMB_SEM, 39)}, new Path[]{}));
        estados.put(17, new Estado(2183,1145,Tipo.SL, 17, 1));
        estados.put(18, new Estado(2183,1783,Tipo.S, 18, 1));
        estados.put(19, new Estado(2183,1657,Tipo.S, 19, 1));
        estados.put(20, new Estado(2183,1533,Tipo.S, 20, 1));
        estados.put(21, new Estado(2180,1393,Tipo.S, 21, 1));
        estados.put(22, new Estado(2180,1267,Tipo.S, 22, 1));
        estados.put(23, new Estado(441,1017,Tipo.DL, 23, 3));
        estados.put(24, new Estado(2183,890,Tipo.AS, 24, new Path[]{new Path(Tipo.OP_ASS, 40)}, new Path[]{}));
        estados.put(25, new Estado(2181,767,Tipo.IS, 25, new Path[]{new Path(Tipo.SMB_OPA, 41)}, new Path[]{}));
        estados.put(26, new Estado(2182,638,Tipo.WHS, 26, new Path[]{new Path(Tipo.SMB_OBC, 42)}, new Path[]{}));
        estados.put(27, new Estado(2184,437,Tipo.RS, 27, new Path[]{new Path(Tipo.ID, 43)}, new Path[]{}));
        estados.put(28, new Estado(2179,233,Tipo.WRS, 28, new Path[]{new Path(Tipo.LIT, 51), new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.W, 44), new Path(Tipo.SE, 50), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(29, new Estado(2181,529,Tipo.SP, 29, new Path[]{new Path(Tipo.SMB_OPA, 45)}, new Path[]{}));
        estados.put(30, new Estado(931,1150,Tipo.WHS, 30, new Path[]{new Path(Tipo.SMB_CBC, 88)}, new Path[]{}));
        estados.put(35, new Estado(295,1115,Tipo.IL, 35, 2));
        estados.put(36, new Estado(165,1153,Tipo.IL_, 36, new Path[]{new Path(Tipo.ID, 14)}, new Path[]{new Path(Tipo.IL, 46)}));
        estados.put(37, new Estado(41,1113,Tipo.IL_, 37, 1));
        estados.put(38, new Estado(641,111,Tipo.B, 38, 4));
        estados.put(39, new Estado(1214,776,Tipo.SL, 39, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 47), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
        estados.put(40, new Estado(2598,888,Tipo.AS, 40, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.SE, 48), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(41, new Estado(2599,591,Tipo.IS, 41, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.CN, 56), new Path(Tipo.E, 57), new Path(Tipo.SE, 58), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(42, new Estado(1222,1275,Tipo.WHS, 42, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 30), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
        estados.put(43, new Estado(2600,63,Tipo.RS, 43, 2));
        estados.put(44, new Estado(1811,40,Tipo.WRS, 44, 2));
        estados.put(45, new Estado(2601,270,Tipo.SP, 45, new Path[]{new Path(Tipo.KW_NOT,54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.CN, 59), new Path(Tipo.E, 57),new Path(Tipo.SE, 58), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(46, new Estado(439,1229,Tipo.IL_, 46, 2));
        estados.put(47, new Estado(859,621,Tipo.SL, 47, 3));
        estados.put(48, new Estado(2544,1090,Tipo.AS, 48, 3));
        estados.put(49, new Estado(3172,1412,Tipo.SE, 49, new Path[]{new Path(Tipo.VAZIO, 62), new Path(Tipo.OP_AD, 63), new Path(Tipo.OP_MIN, 64), new Path(Tipo.KW_OR, 65)}, new Path[]{new Path(Tipo.SE_, 60), new Path(Tipo.A, 61)}));
        estados.put(50, new Estado(1622,42,Tipo.W, 50, 1));
        estados.put(51, new Estado(1427,41,Tipo.W, 51, 1));
        estados.put(52, new Estado(3172,571,Tipo.TE, 52, new Path[]{new Path(Tipo.VAZIO, 68), new Path(Tipo.OP_MUL, 69), new Path(Tipo.OP_DIV, 70), new Path(Tipo.KW_AND, 71)}, new Path[]{new Path(Tipo.TE_, 66), new Path(Tipo.M, 67)}));
        estados.put(53, new Estado(3171,831,Tipo.FA, 53, new Path[]{new Path(Tipo.ID, 73), new Path(Tipo.SMB_OPA, 75), new Path(Tipo.CON_NUM, 76), new Path(Tipo.CON_CHAR, 77)}, new Path[]{new Path(Tipo.F, 72), new Path(Tipo.CT, 74)}));
        estados.put(54, new Estado(3172,336,Tipo.FA_, 54, 1));
        estados.put(55, new Estado(3173,419,Tipo.FA_, 55, 1));
        estados.put(56, new Estado(2438,1226,Tipo.IS, 56, new Path[]{new Path(Tipo.SMB_CPA, 78)}, new Path[]{}));
        estados.put(57, new Estado(2825,441,Tipo.CN, 57, 1));
        estados.put(58, new Estado(3173,1113,Tipo.E, 58, new Path[]{new Path(Tipo.VAZIO, 81), new Path(Tipo.OP_EQ, 82), new Path(Tipo.OP_GT, 83), new Path(Tipo.OP_GE, 84), new Path(Tipo.OP_LT, 85), new Path(Tipo.OP_LE, 86), new Path(Tipo.OP_NE, 87)}, new Path[]{new Path(Tipo.E_, 79), new Path(Tipo.R, 80)}));
        estados.put(59, new Estado(2948,185,Tipo.SP, 59, new Path[]{new Path(Tipo.SMB_CPA, 89)}, new Path[]{}));
        estados.put(60, new Estado(3172,1978,Tipo.SE, 60, 2));
        estados.put(61, new Estado(2684,1278,Tipo.SE_, 61, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.TE, 90), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(62, new Estado(3173,1628,Tipo.SE_, 62, 1));
        estados.put(63, new Estado(3171,1725,Tipo.A, 63, 1));
        estados.put(64, new Estado(3173,1801,Tipo.A, 64, 1));
        estados.put(65, new Estado(3173,1879,Tipo.A, 65, 1));
        estados.put(66, new Estado(3689,332,Tipo.TE, 66, 2));
        estados.put(67, new Estado(3692,816,Tipo.TE_, 67, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.FA, 91), new Path(Tipo.FA_, 53)}));
        estados.put(68, new Estado(3682,422,Tipo.TE_, 68, 1));
        estados.put(69, new Estado(3678,517,Tipo.M, 69, 1));
        estados.put(70, new Estado(3677,603,Tipo.M, 70, 1));
        estados.put(71, new Estado(3679,683,Tipo.M, 71, 1));
        estados.put(72, new Estado(4295,1571,Tipo.FA, 72, 2));
        estados.put(73, new Estado(4296,1500,Tipo.F, 73, 1));
        estados.put(74, new Estado(4304,1419,Tipo.F, 74, 1));
        estados.put(75, new Estado(3697,1258,Tipo.F, 75, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.E, 92), new Path(Tipo.SE, 58), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(76, new Estado(4324,1725,Tipo.CT, 76, 1));
        estados.put(77, new Estado(4319,1652,Tipo.CT, 77, 1));
        estados.put(78, new Estado(2438,1381,Tipo.IS, 78, new Path[]{new Path(Tipo.SMB_OBC, 93)}, new Path[]{}));
        estados.put(79, new Estado(3473,1977,Tipo.E, 79, 2));
        estados.put(80, new Estado(3694,1019,Tipo.E_, 80, new Path[]{new Path(Tipo.KW_NOT, 54), new Path(Tipo.VAZIO, 55)}, new Path[]{new Path(Tipo.SE, 94), new Path(Tipo.TE, 49), new Path(Tipo.FA, 52), new Path(Tipo.FA_, 53)}));
        estados.put(81, new Estado(3323,1977,Tipo.E_, 81, 1));
        estados.put(82, new Estado(3622,1979,Tipo.R, 82, 1));
        estados.put(83, new Estado(3752,1982,Tipo.R, 83, 1));
        estados.put(84, new Estado(3907,1982,Tipo.R, 84, 1));
        estados.put(85, new Estado(4061,1984,Tipo.R, 85, 1));
        estados.put(86, new Estado(4205,1988,Tipo.R, 86, 1));
        estados.put(87, new Estado(4352,1987,Tipo.R, 87, 1));
        estados.put(88, new Estado(930,941,Tipo.WHS, 88, 4));
        estados.put(89, new Estado(3267,183,Tipo.SP, 89, 4));
        estados.put(90, new Estado(2684,1703,Tipo.SE_, 90, new Path[]{new Path(Tipo.VAZIO, 62), new Path(Tipo.OP_AD, 63), new Path(Tipo.OP_MIN, 64), new Path(Tipo.KW_OR, 65)}, new Path[]{new Path(Tipo.SE_, 95), new Path(Tipo.A, 61)}));
        estados.put(91, new Estado(3609,130,Tipo.TE_, 91, new Path[]{new Path(Tipo.VAZIO, 68), new Path(Tipo.OP_MUL, 69), new Path(Tipo.OP_DIV, 70), new Path(Tipo.KW_AND, 71)}, new Path[]{new Path(Tipo.TE_, 96), new Path(Tipo.M, 67)}));
        estados.put(92, new Estado(3982,1182,Tipo.F, 92, new Path[]{new Path(Tipo.SMB_CPA, 97)}, new Path[]{}));
        estados.put(93, new Estado(1223,1750,Tipo.IS, 93, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 98), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
        estados.put(94, new Estado(3982,1020,Tipo.E_, 94, 2));
        estados.put(95, new Estado(2404,1705,Tipo.SE_, 95, 3));
        estados.put(96, new Estado(3898,133,Tipo.TE_, 96, 3));
        estados.put(97, new Estado(4229,1178,Tipo.F, 97, 3));
        estados.put(98, new Estado(805,1751,Tipo.IS, 98, new Path[]{new Path(Tipo.SMB_CBC, 99)}, new Path[]{}));
        estados.put(99, new Estado(803,1949,Tipo.IS, 99, new Path[]{new Path(Tipo.KW_ELSE, 101), new Path(Tipo.VAZIO, 102)}, new Path[]{new Path(Tipo.IS_, 100)}));
        estados.put(100, new Estado(330,1815,Tipo.IS, 100, 8));
        estados.put(101, new Estado(804,2123,Tipo.IS_, 101, new Path[]{new Path(Tipo.SMB_OBC, 103)}, new Path[]{}));
        estados.put(102, new Estado(375,1946,Tipo.IS_, 102, 1));
        estados.put(103, new Estado(1225,2216,Tipo.IS_, 103, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 104), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
        estados.put(104, new Estado(798,2324,Tipo.IS_, 104, new Path[]{new Path(Tipo.SMB_CBC, 105)}, new Path[]{}));
        estados.put(105, new Estado(361,2325,Tipo.IS_, 105, 4));    
    }
    
    /**
     * Método responsável por inserir os exemplos de token esperados na estrutura exemploEsperados.
     */
    public static void insereExemplosEsperados(){
        exemploEsperados = new HashMap<>();
        exemploEsperados.put(Tipo.KW_PROGRAM, "'program'");
        exemploEsperados.put(Tipo.KW_IF, "'if'");
        exemploEsperados.put(Tipo.KW_ELSE, "'else'");
        exemploEsperados.put(Tipo.KW_WHILE, "'while'");
        exemploEsperados.put(Tipo.KW_WRITE, "'write'");
        exemploEsperados.put(Tipo.KW_READ, "'read'");
        exemploEsperados.put(Tipo.KW_NUM, "'num'");
        exemploEsperados.put(Tipo.KW_CHAR, "'char'");
        exemploEsperados.put(Tipo.KW_NOT, "'not'");
        exemploEsperados.put(Tipo.KW_OR, "'or'");
        exemploEsperados.put(Tipo.KW_AND, "'and");
        exemploEsperados.put(Tipo.SMB_SEM, "';'");
        exemploEsperados.put(Tipo.SMB_COM, "','");
        exemploEsperados.put(Tipo.SMB_CPA, "')'");
        exemploEsperados.put(Tipo.SMB_OPA, "'('");
        exemploEsperados.put(Tipo.SMB_CBC, "'}'");
        exemploEsperados.put(Tipo.SMB_OBC, "'{'");
        exemploEsperados.put(Tipo.OP_MUL, "'*'");
        exemploEsperados.put(Tipo.OP_DIV, "'/'");
        exemploEsperados.put(Tipo.OP_LT, "'<'");
        exemploEsperados.put(Tipo.OP_LE, "'<='");
        exemploEsperados.put(Tipo.OP_AD, "'+'");
        exemploEsperados.put(Tipo.OP_MIN, "'-'");
        exemploEsperados.put(Tipo.OP_GT, "'>'");
        exemploEsperados.put(Tipo.OP_GE, "'>='");
        exemploEsperados.put(Tipo.OP_NE, "'!='");
        exemploEsperados.put(Tipo.OP_ASS, "'='");
        exemploEsperados.put(Tipo.OP_EQ, "'=='");
        exemploEsperados.put(Tipo.ID, "Identificador");
        exemploEsperados.put(Tipo.LIT, "Literal");
        exemploEsperados.put(Tipo.CON_CHAR, "Constante de Caractér");
        exemploEsperados.put(Tipo.CON_NUM, "Constante Numérica");
        exemploEsperados.put(Tipo.EOF, "Fim de Arquivo");
        
    }
    
    /**
     * Método responsável pela verificação inicial do estado, identificando se o estado atual é um estado de shift ou reduce.
     */
    public static void iniciaEstado(){
        while(!fim){ // Enquanto não estamos no fim do arquivo.
            Estado e = estados.get(estadoAtual); // Pega o estado atual da lista de estados.
            if(e.qntTokens == -1){ // Uma quantidade de Tokens igual a -1 é como indicamos um estado de SHIFT.
                shift();
            }else{ // Caso o valor da quantidade de Tokens não seja -1, o estado é de REDUCE.
                reduce();
            }   
        }
        adicionaNoLog("Finalizado! " + lexico.getContErro() + " erro(s) léxico(s) e " + contErro + " erro(s) sintático(s) encontrado(s).");
        System.out.println("Finalizado! " + lexico.getContErro() + " erro(s) léxico(s) e " + contErro + " erro(s) sintático(s) encontrado(s)."); // Mostra a mensagem de fim e a quantidade de erros sintáticos encontrados.
        Tela tela = new Tela(listaItens, estados, log);
        tela.setVisible(true);
    }

    /**
     * Método responsável pela operação SHIFT - Compara o token atual com os tipos de token aceitos pelo estado atual, avançando caso sejam compatíveis -
     * Caso a cadeia vazia seja um dos tipos aceitos pelo estado e nenhum outro tipo do estado seja igual ao token atual, é feito um shift sem avançar o token atual -
     * Aqui é feita a implementação do modo pânico, onde identificamos o erro sintático, mostramos os tokens esperados e prosseguimos a análise enquanto a quantidade de erros é inferior a 5.
     */
    public static void shift() {
        Estado e = estados.get(estadoAtual); // Pega o estado atual da lista de estados.
        Path vazio = null; // Variável que guarda o caminho com cadeia vazia do estado caso ele tenha um.
        for(Path p : e.shifts){ // Passa por todos os caminhos do estado.
            if(p.entrada.equals(t.getTipo())){ // Verifica se a entrada de cada caminho é igual ao tipo do token.
                if(t.getTipo().equals(Tipo.EOF)){ // Verifica se é o fim do arquivo.
                    fim = true; // Dá sinal para o fim da análise.
                    return;
                }
                estadoAtual = p.saida; // Avança o estado atual para o destino do caminho.
                pilha.push(estadoAtual); // Insere o novo estado na pilha.
                atual.addOp("SHIFT", estadoAtual);
                System.out.println("\tSHIFT " + estadoAtual); // Mensagem de confirmação do SHIFT.
                esperados.clear(); // Limpa a lista de esperados, já que não ocorreu erro com o Token.
                t = lexico.proximoToken(); // Recebe o próximo token do Léxico.
                ItemTela it = new ItemTela(t.toString());
                atual = it;
                listaItens.add(it);
                return;
            }else if(p.entrada.equals(Tipo.VAZIO)){ // Caso haja um caminho em cadeia vazia, apenas o salva na variável vazio. 
                vazio = p; //Um shift com vazio só pode ser feito caso nenhum dos tipos verificados se encaixe com o tipo do token atual.
            }
        }
        if(esperados.isEmpty()){ // Nenhum tipo correspondeu ao tipo do token, portanto pode haver erro. Fazemos aqui um backup da pilha. 
            pilhaBackup = (Stack<Integer>) pilha.clone();
        }
        adicionaEsperados(e.shifts); // Começa a preencher a lista de esperados.
        if(vazio != null){ // Caso haja caminho com cadeia vazia.
            estadoAtual = vazio.saida; // Realiza os passos acima descritos, porém sem avançar o token.
            pilha.push(estadoAtual);
            atual.addOp("SHIFT", estadoAtual);
            System.out.println("\tSHIFT " + estadoAtual);
        }else{ // Se não for o caso, dá erro.
            pilha = (Stack<Integer>) pilhaBackup.clone(); // Ocorreu um erro e podem ter ocorrido movimentações entre os estados que prejudicariam o funcionamento seguinte do analisador. Por isso recuperamos o estado da pilha antes dessas movimentações.
            estadoAtual = pilha.peek(); // O estado atual é o topo da pilha.
            adicionaNoLog("\nErro Sintático na linha " + t.getLinha() + " e coluna " + t.getColuna() + ". '" + t.getLexema() + "' encontrado. Esperado(s): ");
            System.out.println("\nErro Sintático na linha " + t.getLinha() + " e coluna " + t.getColuna() + ". '" + t.getLexema() + "' encontrado. Esperado(s): ");
            for(Tipo tipo : esperados){ // Mostra os tipos esperados.
                adicionaNoLog("- " + exemploEsperados.get(tipo));
                System.out.println("- " + exemploEsperados.get(tipo));
            }
            contErro++; // Aumenta a contagem de erros.
            if(contErro > 4){ // Caso a contagem de erros sintáticos for superior à 4.
                adicionaNoLog("Compilação abortada. 5 erros sintáticos encontrados.");
                System.out.println("Compilação abortada. 5 erros sintáticos encontrados."); // Mostra a mensagem e termina compilação.
                fim = true; // Dá sinal para o fim da análise.
                return;
            }
            t = lexico.proximoToken(); // Para o modo pânico, mantemos o autômato no mesmo estado e avançamos o token de entrada.
            ItemTela it = new ItemTela(t.toString());
            atual = it;
            listaItens.add(it);
        }
    }
    
    /**
     * Método responsável pela operação REDUCE - Desempilha a quantidade necessária de itens e "empilha" o não terminal correspondente ao estado atual.
     */
    public static void reduce(){
        Estado e = estados.get(estadoAtual); // Pega o estado atual da lista de estados.
        for(int i = 0; i < e.qntTokens; i++){ // Desempilha os estados com base na quantidade de tokens do estado.
            pilha.pop();
        }
        atual.addOp("REDUCE", pilha.peek());
        System.out.println("\tREDUCE " + pilha.peek()); // Mensagem de confirmação do REDUCE.
        goTo(e.naoTerminal); // Chama o método do GOTO, mandando como parâmetro o não terminal deste estado.
    }
    
    /**
     * Método responsável pela operação GOTO - Verifica o não terminal enviado pelo REDUCE e compara com as entradas os caminhos do estado atual, afim de ir até o estado destino.
     * @param naoTerminal Não terminal que será usado para indicar o estado para o qual iremos com o GOTO.
     */
    public static void goTo(Tipo naoTerminal){
        Estado e = estados.get(pilha.peek()); // Pega o estado atual (topo da pilha) da lista de estados.
        for(Path p : e.gotos){ // Para cada caminho de goTos do estado atual
            if(p.entrada.equals(naoTerminal)){ // Verifica se a entrada equivale ao não terminal enviado.
                estadoAtual = p.saida; // Avança o estado com base no destino do caminho.
                pilha.push(estadoAtual); // Empilha o próximo estado.
                atual.addOp("GOTO", estadoAtual);
                System.out.println("\tGOTO " + estadoAtual); // Mensagem de confirmação do GOTO.
                return;
            }
        } 
    }
    
    /**
     * Método para inserção de caminhos à lista de tipos de tokens esperados.
     * @param esp Lista de caminhos a serem adicionados a lista de esperados 
     */
    public static void adicionaEsperados(Path[] esp){
        for(Path p : esp){ // Para cada caminho enviado
            if(!esperados.contains(p.entrada) && !p.entrada.equals(Tipo.VAZIO)){ // Caso não esteja na lista...
                esperados.add(p.entrada); // Faz a inserção.
            }
        }
    }
    
    public static void adicionaNoLog(String texto){
        log = log + (texto + "\n");
    }
}