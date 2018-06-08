package sintatico;

/**
 *
 * @author Iago
 */
public class Operacao {
    
    private String nome;
    private int estado;

    public Operacao(String nome, int estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
}
