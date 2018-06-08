package sintatico;

import lexico.Tipo;

/**
 * Estado.java - Classe que guarda os itens de cada estado do autômato.
 * @author Iago Arantes, Jean Silva
 */
public class Estado {
    /**
     * ID do estado.
     */
    public final int id;
    /**
     * Importante para estados de redução - Contém o não terminal que será empilhado no caso de um REDUCE.
     */
    public final Tipo naoTerminal;
    /**
     * Array do tipo Path, guarda os caminhos do estado que podem ser acessados com terminais, acessados com o SHIFT.
     */
    public final Path[] shifts; 
    /**,
     * Array do tipo Path, guarda os caminhos do estado que podems ser acessados com não terminais, acessado através do GOTO.
     */
    public final Path[] gotos;
    /**
     * Importante para estados de redução - Contém a quantidade de tokens da regra da gramática contida nesse estado (Estados de redução nesse caso contém apenas uma regra) afim de saber quantos elementos da pilha devemos desempilhar com o REDUCE.
     */
    public int qntTokens;
    
    public int localL;
    
    public int localA;
    
    /**
     * Construtor para estados de SHIFT - Aqui, a quantidade de tokens é iniciada como -1, uma forma de indicar que este estado é um estado de SHIFT.
     * @param naoTerminal Não é necessário neste caso, mas ainda sim é assimilado ao estado.
     * @param id ID do estado criado.
     * @param shifts Lista de caminhos de terminais (SHIFT).
     * @param gotos Lista de caminhos de não terminais (GOTO).
     */
    public Estado(Tipo naoTerminal, int id, Path[] shifts, Path[] gotos) {
        this.naoTerminal = naoTerminal;
        this.id = id;
        this.shifts = shifts;
        this.gotos = gotos;
        this.qntTokens = -1;
    }
    
    /**
     * Construtor para estados de REDUCE - Aqui, não temos o vetor de shifts nem de goTos, já que neste estado somente seram realizadas reduções.
     * @param naoTerminal Neste caso é vital, já que nos indica o não terminal a ser empilhado após a redução.
     * @param id ID do estado criado.
     * @param qntTokens Neste caso a quantidade de tokens é enviada como parâmetro, afim de mostrar quantos itens da pilha serão desempilhados com o REDUCE deste estado.
     */
    public Estado(Tipo naoTerminal, int id, int qntTokens) {
        this.naoTerminal = naoTerminal;
        this.id = id;
        this.qntTokens = qntTokens;
        this.shifts = new Path[]{};
        this.gotos = new Path[]{};
    }
    
    public Estado(int localL, int localA, Tipo naoTerminal, int id, Path[] shifts, Path[] gotos) {
        this.localL = localL;
        this.localA = localA;
        this.naoTerminal = naoTerminal;
        this.id = id;
        this.shifts = shifts;
        this.gotos = gotos;
        this.qntTokens = -1;
    }
    
    public Estado(int localL, int localA, Tipo naoTerminal, int id, int qntTokens) {
        this.localL = localL;
        this.localA = localA;
        this.naoTerminal = naoTerminal;
        this.id = id;
        this.qntTokens = qntTokens;
        this.shifts = new Path[]{};
        this.gotos = new Path[]{};
    }
}
