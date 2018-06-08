package lexico;

import java.util.ArrayList;

/**
 * Classe Token, representação dos simbolos, palavras chaves, identificadores, entre outros tipos de lexemas encontrados no programa.
 * @author Iago Arantes, Jean Silva
 */
public class Token {
    /**
     * String que guarda o lexema do token
     */
    private final String lexema;
    
    /**
     * Inteiro que guarda a linha onde esse token esta localizado no programa.
     * Tanto para a linha como para a coluna, foi usada a ultima posição onde o lexema se encontra no arquivo.
     */
    private int linha;
    
    /**
     * Inteiro que guarda a coluna onde esse token esta localizado no programa.
     */
    private int coluna;
    
    /**
     * Tipo do token, com base nos tipos da clase Tipo
     */
    private final Tipo tipo;
    
    /**
     * Método construtor da classe Token, recebendo todos os atributos necessários.
     * @param tipo Tipo do token.
     * @param lexema Lexema do token
     * @param linha Linha onde o token está
     * @param coluna Coluna onde o token está
     */
    public Token(Tipo tipo, String lexema, int linha, int coluna) {
        this.lexema = lexema;
        this.linha = linha;
        this.coluna = coluna;
        this.tipo = tipo;
    }

    /**
     * Retorna a linha onde o token está no programa
     * @return Linha
     */
    public int getLinha() {
        return linha;
    }

    /**
     * Retorna a coluna onde o token está no programa
     * @return Coluna
     */
    public int getColuna() {
        return coluna;
    }

    /**
     * Retorna o lexema do token.
     * @return Lexema
     */
    public String getLexema() {
        return lexema;
    }

    /**
     * Retorna o tipo do token.
     * @return Tipo
     */
    public Tipo getTipo() {
        return tipo;
    }
    
    /**
     * Altera a coluna do token.
     * Usada apenas quando uma palavra-chave ou um id anteriormente visto são encontrados no programa.
     * @param coluna Nova coluna do token
     */
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    
    /**
     * Altera a lina do token.
     * Usada apenas quando uma palavra-chave ou um id anteriormente visto são encontrados no programa.
     * @param linha Nova linha do token
     */
    public void setLinha(int linha) {
        this.linha = linha;
    }
    
    @Override
    public String toString(){
        return ("Novo Token: < " + this.tipo + " , '" + this.lexema + "' > Linha: " + this.linha + " , Coluna: " + this.coluna);
    }
}
