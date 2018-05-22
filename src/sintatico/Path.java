package sintatico;

import lexico.Tipo;

/**
 *
 * @author yago
 */
public class Path {
    public final Tipo entrada;
    public final int saida;  

    public Path(Tipo entrada, int saida) {
        this.entrada = entrada;
        this.saida = saida;
    }
}
