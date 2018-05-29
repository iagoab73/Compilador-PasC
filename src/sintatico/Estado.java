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
        System.out.println("\n" + this.id + ": " + this.naoTerminal);
        
        for(int i=0;i<shifts.length;i++){
            System.out.println(shifts[i].entrada + " " + shifts[i].saida);
        }
        
        for(int i=0;i<gotos.length;i++){
            System.out.println(gotos[i].entrada + " " + gotos[i].saida);
        }
    }
    
    public Estado(Tipo naoTerminal, int id, int qntTokens) {
        this.naoTerminal = naoTerminal;
        this.id = id;
        this.qntTokens = qntTokens;
        this.shifts = new Path[]{};
        this.gotos = new Path[]{};
        
        System.out.println("\n" + this.id + ": " + this.naoTerminal + " - " + this.qntTokens);
    }
    
    public void setQntTokens(int qntTokens){
        this.qntTokens = qntTokens;
    }
}
