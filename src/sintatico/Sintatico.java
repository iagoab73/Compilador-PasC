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
        t = lexico.proximoToken();// O primeiro token é recebido, enviado após a análise do analisador léxico.
        estadoAtual = 1; // Variável que guarda o estado atual do autômato, começa com o valor 1 (Estado Inicial).
        pilha.push(estadoAtual); // Insere o estado atual na pilha.
        iniciaEstado();// Inicia as verificações no estado inicial.
    }
    
    /**
     * Método responsável por inserir os estados do autômato na estrutura estados.
     */
    public static void insereEstados(){
        estados = new HashMap<>();// Estados é inicializado e os estados do autômato são inseridos. Na classe Estado e Path é possível ver o que cada um dos parâmetros significa.
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
        estados.put(39, new Estado(Tipo.SL, 39, new Path[]{new Path(Tipo.VAZIO, 17), new Path(Tipo.ID, 24), new Path(Tipo.KW_IF, 25), new Path(Tipo.KW_READ, 27), new Path(Tipo.KW_WRITE, 28), new Path(Tipo.KW_WHILE, 29)}, new Path[]{new Path(Tipo.SL, 47), new Path(Tipo.S, 16), new Path(Tipo.AS, 18), new Path(Tipo.IS, 19), new Path(Tipo.WHS, 20), new Path(Tipo.RS, 21), new Path(Tipo.WRS, 22), new Path(Tipo.SP, 26)}));
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
        System.out.println("Finalizado! " + lexico.getContErro() + " erro(s) léxico(s) e " + contErro + " erro(s) sintático(s) encontrado(s)."); // Mostra a mensagem de fim e a quantidade de erros sintáticos encontrados.
    }

    /**
     * Método responsável pela operação SHIFT - Compara o token atual com os tipos de token aceitos pelo estado atual, avançando caso sejam compatíveis -
     * Caso a cadeia vazia seja um dos tipos aceitos pelo estado e nenhum outro tipo do estado seja igual ao token atual, é feito um shift sem avançar o token atual.
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
                System.out.println("\tSHIFT " + estadoAtual); // Mensagem de confirmação do SHIFT.
                esperados.clear(); // Limpa a lista de esperados, já que não ocorreu erro com o Token.
                t = lexico.proximoToken(); // Recebe o próximo token do Léxico.
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
            System.out.println("\tSHIFT " + estadoAtual);
        }else{ // Se não for o caso, dá erro.
            pilha = (Stack<Integer>) pilhaBackup.clone(); // Ocorreu um erro e podem ter ocorrido movimentações entre os estados que prejudicariam o funcionamento seguinte do analisador. Por isso recuperamos o estado da pilha antes dessas movimentações.
            estadoAtual = pilha.peek(); // O estado atual é o topo da pilha.
            System.out.println("\nErro Sintático na linha " + t.getLinha() + " e coluna " + t.getColuna() + ". '" + t.getLexema() + "' encontrado. Esperado(s): ");
            for(Tipo tipo : esperados){ // Mostra os tipos esperados.
                System.out.println("- " + exemploEsperados.get(tipo));
            }
            contErro++; // Aumenta a contagem de erros.
            if(contErro > 4){ // Caso a contagem de erros sintáticos for superior à 4.
                System.out.println("Compilação abortada. 5 erros sintáticos encontrados."); // Mostra a mensagem e termina compilação.
                fim = true; // Dá sinal para o fim da análise.
                return;
            }
            t = lexico.proximoToken(); // Para o modo pânico, mantemos o autômato no mesmo estado e avançamos o token de entrada.
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
}