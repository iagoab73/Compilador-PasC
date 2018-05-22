package sintatico;

import AnalisadorLexico.Tipo;

/**
 *
 * @author Iago
 */
public class Estado {
    private final int id;
    private final Tipo[] entradas;
    private final int[] saidas;
    private final Tipo[] entradasGoTo;
    private final int[] saidasGoTo;

    public Estado(int id, Tipo[] entradas, int[] saidas, Tipo[] entradasGoTo ,int[] saidasGoTo) {
        this.id = id;
        System.out.println("\n" + this.id + ":");
        this.entradas = entradas;
        this.saidas = saidas;
        this.entradasGoTo = entradasGoTo;
        this.saidasGoTo = saidasGoTo;
    }
}
