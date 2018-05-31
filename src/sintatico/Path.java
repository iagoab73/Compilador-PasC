package sintatico;

import lexico.Tipo;

/**
 * Path - Classe que guarda os dados de um caminho entre dois estados de um autômato.
 * @author Iago Arantes, Jean Silva.
 */
public class Path {
    /**
     * Item que guarda o tipo de token que ativa este caminho.
     */
    public final Tipo entrada;
    /**
     * Item que contém o estado destino após identificar um token do tipo da entrada. 
     */
    public final int saida;  
  
    /**
     * Método construtor da classe Path.
     * @param entrada Entrada de ativação do caminho.
     * @param saida Estado destino do caminho.
     */
    public Path(Tipo entrada, int saida) {
        this.entrada = entrada;
        this.saida = saida;
    }
}
