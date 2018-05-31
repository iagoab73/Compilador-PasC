package sintatico;

import lexico.Tipo;

/**
 *
 * @author Iago
 */
public class Estado {
    public final int id;
    public final Tipo naoTerminal;
    public final Path[] shifts; 
    public final Path[] gotos;
    public int qntTokens;
    

    public Estado(Tipo naoTerminal, int id, Path[] shifts, Path[] gotos) {
        this.naoTerminal = naoTerminal;
        this.id = id;
        this.shifts = shifts;
        this.gotos = gotos;
        this.qntTokens = -1;
    }
    
    public Estado(Tipo naoTerminal, int id, int qntTokens) {
        this.naoTerminal = naoTerminal;
        this.id = id;
        this.qntTokens = qntTokens;
        this.shifts = new Path[]{};
        this.gotos = new Path[]{};
    }
}
