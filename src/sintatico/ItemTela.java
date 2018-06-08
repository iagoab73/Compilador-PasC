package sintatico;

import java.util.ArrayList;

/**
 *
 * @author Iago
 */
public class ItemTela {
    private String t;
    
    private ArrayList<Operacao> ops;

    public ItemTela(String t) {
        this.t = t;
        this.ops = new ArrayList<>();
    }
    
    public void addOp(String n, int e){
        this.ops.add(new Operacao(n, e));
    }

    public String getT() {
        return t;
    }

    public ArrayList<Operacao> getOps() {
        return ops;
    }
    
    
}
