package AnalisadorLexico;

import java.util.HashMap;

/**
 * TabelaSimbolos.java - Classe da tabela de simbolos que auxilia o analisador lexico, gravando as palavras-chave e identificadores.
 * @author Iago Arantes, Jean Silva
 */
public class TabelaSimbolos {
    /**
     * Estrutura de dados que guarda os tokens de Palavra-Chave existentes na linguagem e IDs encontradas no programa. Como orientado, foi usado o Hashmap, por permitir busca de custo O(1).
     */
    private HashMap<String, Token> tabela_simbolos;

    /**
     * Método construtor da Tabela de Simbolos, responsável pela iniciação da estrutura de dados e inclusão das plavaras chaves da linguagem na estrutura
     */
    public TabelaSimbolos() {
        this.tabela_simbolos = new HashMap<>();
        
        Token tok = new Token(Tipo.KW_PROGRAM, "program", 0, 0);
        this.tabela_simbolos.put(tok.getLexema(), tok);
        
        tok = new Token(Tipo.KW_IF, "if", 0, 0);
        this.tabela_simbolos.put(tok.getLexema(), tok);
        
        tok = new Token(Tipo.KW_ELSE, "else", 0, 0);
        this.tabela_simbolos.put(tok.getLexema(), tok);
        
        tok = new Token(Tipo.KW_WHILE, "while", 0, 0);
        this.tabela_simbolos.put(tok.getLexema(), tok);
        
        tok = new Token(Tipo.KW_WRITE, "write", 0, 0);
        this.tabela_simbolos.put(tok.getLexema(), tok);
        
        tok = new Token(Tipo.KW_READ, "read", 0, 0);
        this.tabela_simbolos.put(tok.getLexema(), tok);
        
        tok = new Token(Tipo.KW_NUM, "num", 0, 0);
        this.tabela_simbolos.put(tok.getLexema(), tok);
        
        tok = new Token(Tipo.KW_CHAR, "char", 0, 0);
        this.tabela_simbolos.put(tok.getLexema(), tok);
        
        tok = new Token(Tipo.KW_NOT, "not", 0, 0);
        this.tabela_simbolos.put(tok.getLexema(), tok);
        
        tok = new Token(Tipo.KW_OR, "or", 0, 0);
        this.tabela_simbolos.put(tok.getLexema(), tok);
        
        tok = new Token(Tipo.KW_AND, "and", 0, 0);
        this.tabela_simbolos.put(tok.getLexema(), tok);
    }
    
    /**
     * Método que imprime os tokens da tabela de simbolos.
     */
    public void ImprimeTokens(){
        System.out.println("\n    -   Tabela de Símbolos  -");
        for(Token t: tabela_simbolos.values()){
            System.out.println(t.getTipo() + "   " + t.getLexema());
        }
    }
    
    /**
     * Método responsável pela comparação dos IDs reconhecidos pelo lexer com os tokens já presentes na tabela de simbolos.
     * Caso o token esteja na tabela, apenas muda sua linha e coluna e o retorna. Senão, cria um novo token, coloca-o na tabela e o retorna também.
     * Para fazer com que a recuperação tenha o menor custo possível, foi usado o próprio lexema do token como identificador para seu espaço na hash.
     * @param lexema - Lexema encontrado pelo Lexer
     * @param linha - Linha em que o lexema foi encontrado
     * @param coluna - Coluna em que o lexema foi encontrado
     * @return Retorna o token, seja ele um novo token ou um que já estava dentro da tabela de símbolos
     */
    public Token ComparaID(String lexema, int linha, int coluna){
        if(this.tabela_simbolos.containsKey(lexema)){
            Token t = tabela_simbolos.get(lexema);
            t.setLinha(linha);
            t.setColuna(coluna);
            return this.tabela_simbolos.get(lexema);
        }else{
            Token tok = new Token(Tipo.ID, lexema, linha, coluna);
            this.tabela_simbolos.put(lexema, tok);
            return tok;
        }
    } 
}
