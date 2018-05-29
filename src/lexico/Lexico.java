package lexico;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Lexico.java - Classe principal do Analisador Léxico.
 * @author Iago Arantes, Jean Silva
 */
public class Lexico {
    /**
     * Instancia da Tabela de Simbolos.
     */
    public static TabelaSimbolos tabela = new TabelaSimbolos();
    /**
     * Variável que guarda o estado atual do automato.
     */
    public static int estado = 0;
    /**
     * Variável que auxilia a contagem de linha de cada token.
     */
    public static int linha = 1;
    /**
     * Variável que auxilia a contagem de coluna de cada token.
     */
    public static int coluna = 0;
    /**
     * Variável do arquivo que contém o programa a ser reconhecido pelo lexer.
     */
    public static RandomAccessFile arquivo;
    
    public Lexico(){
        try{
            LeArquivo();
            
        }catch(Exception e){
            System.out.println("Erro no arquivo!");
        }
    }
    
    public Token proximoToken(){
        try{
            Token t = ProximoToken();
            System.out.println("\n< " + t.getTipo() + "  ,  '" + t.getLexema() + "' >    Linha: " + t.getLinha() + " ,\tColuna: " + t.getColuna());
            return t;
        }catch(Exception e){
            System.out.println("\nErro no arquivo!");
            return null;
        }
    }
    /**
     * Função responsável pela leitura inicial do arquivo.
     * @throws FileNotFoundException  - Caso o arquivo não seja encontrado
     */
    public static void LeArquivo() throws FileNotFoundException{
        arquivo = new RandomAccessFile("teste.txt", "r");
    }
    
    /**
     * Função que volta o ponteiro do caracter atual do arquivo, usada em casos específicos do automato.
     * @throws IOException - Caso ocorra algum erro na leitura do arquivo
     */
    private static void voltaChar() throws IOException {
        if (arquivo.read() == -1){
            return;
        }else{
            arquivo.seek(arquivo.getFilePointer() - 2);
            coluna--;
        }
    }
    
    /**
     * Método responsável pela análise de cada caracter do arquivo, montando lexemas e retornando seus respectivos tokens, identificando seus tipos e posição no programa, além de identificar erros léxicos.
     * @return Retorna um token identificado pelas regras do automato com base nos caracteres apresentados no arquivo.
     * @throws IOException - Caso ocorra algum erro na leitura do arquivo.
     */
    private static Token ProximoToken() throws IOException{
        StringBuilder lexema = new StringBuilder();
        
        estado = 0;
        
        do{
            int c_atual_cod;
            char c_atual;
            c_atual_cod = arquivo.read();
            c_atual = (char) c_atual_cod;
            Character.toLowerCase(c_atual);
            coluna++;

            switch(estado){
                case 0:
                    if(c_atual == ';'){
                        estado = 1;
                        return new Token(Tipo.SMB_SEM, ";", linha, coluna);
                    }else if(c_atual == ','){
                        estado = 2;
                        return new Token(Tipo.SMB_COM, ",", linha, coluna);
                    }else if(c_atual == ')'){
                        estado = 3;
                        return new Token(Tipo.SMB_CPA, ")", linha, coluna);
                    }else if(c_atual == '('){
                        estado = 4;
                        return new Token(Tipo.SMB_OPA, "(", linha, coluna);
                    }else if(c_atual == '}'){
                        estado = 5;
                        return new Token(Tipo.SMB_CBC, "}", linha, coluna);
                    }else if(c_atual == '{'){
                        estado = 6;
                        return new Token(Tipo.SMB_OBC, "{", linha, coluna);
                    }else if(c_atual == '*'){
                        estado = 7;
                        return new Token(Tipo.OP_MUL, "*", linha, coluna);
                    }else if(c_atual == '<'){
                        estado = 8;
                    }else if(c_atual == '+'){
                        estado = 11;
                        return new Token(Tipo.OP_AD, "+", linha, coluna);
                    }else if(c_atual == '-'){
                        estado = 12;
                        return new Token(Tipo.OP_MIN, "-", linha, coluna);
                    }else if(c_atual == '>'){
                        estado = 13;
                    }else if(c_atual == '!'){
                        estado = 16;
                    }else if(c_atual == '='){
                        estado = 18;
                    }else if(Character.isLetter(c_atual)){
                        estado = 21;
                        lexema.append(c_atual);
                    }else if(c_atual == '"'){
                        estado = 23;
                        lexema.append(c_atual);
                    }else if(c_atual == ('\'')){
                        estado = 26;
                        lexema.append(c_atual);
                    }else if(Character.isDigit(c_atual)){
                        estado = 30;
                        lexema.append(c_atual);
                    }else if(c_atual == '/'){
                        estado = 34;
                        lexema.append(c_atual);
                    }else if(c_atual == '\n'){
                        linha++;
                        coluna = 0;
                    }else if(c_atual == '\b' || c_atual == '\r' || c_atual == ' ' || c_atual == '\t'){
                        
                    }else if(c_atual_cod == -1){
                        return new Token(Tipo.EOF, "EOF", linha, coluna);
                    }else{
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". '" + c_atual + "' não pode ser reconhecido.");
                    }
                    break;
                case 8:
                    if(c_atual == '='){
                        estado = 9;
                        return new Token(Tipo.OP_LE, "<=", linha, coluna);
                    }else{
                        estado = 10;
                        voltaChar();
                        return new Token(Tipo.OP_LT, "<", linha, coluna);
                    }
                case 13:
                    if(c_atual == '='){
                        estado = 14;
                        return new Token(Tipo.OP_GE, ">=", linha, coluna);
                    }else{
                        estado = 15;
                        voltaChar();
                        return new Token(Tipo.OP_GT, ">", linha, coluna);
                    }
                case 16:
                    if(c_atual == '='){
                        estado = 17;
                        return new Token(Tipo.OP_NE, "!=", linha, coluna);
                    }else if(c_atual == '\n'){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". '=' esperado.");
                        linha++;
                        coluna = 0;
                    }else if(c_atual_cod == -1){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Fim do arquivo alcançado terminar token '!='.");
                        return new Token(Tipo.EOF, "EOF", linha, coluna);
                    }else{
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". '=' esperado.");
                    }
                    break;
                case 18:
                    if(c_atual == '='){
                        estado = 19;
                        return new Token(Tipo.OP_EQ, "==", linha, coluna);
                    }else{
                        estado = 20;
                        voltaChar();
                        return new Token(Tipo.OP_ASS, "=", linha, coluna);
                    }
                case 21:
                    if(Character.isLetter(c_atual) || Character.isDigit(c_atual)){
                        lexema.append(c_atual);
                    }else{
                        estado = 22;
                        voltaChar();
                        return tabela.ComparaID(lexema.toString(), linha, coluna);
                    }
                    break;
                case 23:
                    if(c_atual_cod == -1){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Fim do arquivo alcançado sem fechar aspas duplas.");
                        return new Token(Tipo.EOF, "EOF", linha, coluna);
                    }else if(c_atual == '\n'){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Quebra de linha sem fechar aspas duplas.");
                        linha++;
                        coluna = 0;
                        estado = 39;
                    }else if(c_atual != '\r'){
                        estado = 24;
                        lexema.append(c_atual);
                    }
                    break;
                case 24:
                    if(c_atual == '"'){
                        estado = 25;
                        lexema.append(c_atual);
                        return new Token(Tipo.LIT, lexema.toString(), linha, coluna);
                    }else if(c_atual == '\n'){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Quebra de linha sem fechar aspas duplas.");
                        linha++;
                        coluna = 0;
                        estado = 39;
                    }else if(c_atual_cod == -1){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Fim do arquivo alcançado sem fechar aspas duplas.");
                        return new Token(Tipo.EOF, "EOF", linha, coluna);
                    }else if(c_atual != '\r'){
                        lexema.append(c_atual);
                    }
                    break;
                case 26:
                    if(c_atual_cod == -1){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Fim do arquivo alcançado sem fechar aspas simples.");
                        return new Token(Tipo.EOF, "EOF", linha, coluna);
                    }else if(c_atual == '\\'){
                        estado = 27;
                        lexema.append(c_atual);
                    }else if(c_atual == '\n'){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Quebra de linha sem fechar aspas simples.");
                    }else if(c_atual != '\r'){
                        estado = 28;
                        lexema.append(c_atual);
                    }
                    break;
                case 27:
                    if(c_atual == 'r' || c_atual == 't' || c_atual == 'b' || c_atual == 'n'){
                        estado = 28;
                        lexema.append(c_atual);
                    }else if(c_atual_cod == -1){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Fim do arquivo alcançado sem fechar aspas simples.");
                        return new Token(Tipo.EOF, "EOF", linha, coluna);
                    }else{
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Só pode haver um caracter entre aspas simples.");
                    }
                    break;
                case 28:
                    if(c_atual == '\''){
                        estado = 29;
                        if(lexema.toString().length() < 2){
                            System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Caracter Vazio.");
                            estado = 0;
                            lexema = new StringBuilder();
                        }else{
                            lexema.append(c_atual);
                            return new Token(Tipo.CON_CHAR, lexema.toString(), linha, coluna);
                        }
                    }else if(c_atual_cod == -1){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Fim do arquivo alcançado sem fechar aspas simples.");
                        return new Token(Tipo.EOF, "EOF", linha, coluna);
                    }else{
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Só pode haver um caracter entre aspas simples.");
                    }
                    break;
                case 30:
                    if(Character.isDigit(c_atual)){
                        lexema.append(c_atual);
                    }else if(c_atual == '.'){
                        estado = 31;
                        lexema.append(c_atual);
                    }else{
                        estado = 32;
                        voltaChar();
                        return new Token(Tipo.CON_NUM, lexema.toString(), linha, coluna);
                    }
                    break;
                case 31:
                    if(Character.isDigit(c_atual)){
                        estado = 33;
                        lexema.append(c_atual);
                    }else if(c_atual == '\n'){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Dígito esperado.");
                        linha++;
                        coluna = 0;
                    }else if(c_atual_cod == -1){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Fim do arquivo alcançado sem completar número.");
                        return new Token(Tipo.EOF, "EOF", linha, coluna);
                    }else{
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Dígito esperado.");
                    }
                    break;
                case 33:
                    if(Character.isDigit(c_atual)){
                        lexema.append(c_atual);
                    }else{
                        estado = 32;
                        voltaChar();
                        return new Token(Tipo.CON_NUM, lexema.toString(), linha, coluna);
                    }
                    break;
                case 34:
                    if(c_atual == '/'){
                        estado = 35;
                    }else if(c_atual == '*'){
                        estado = 36;
                    }else{
                        estado = 37;
                        voltaChar();
                        return new Token(Tipo.OP_DIV, "/", linha, coluna);
                    }
                    break;
                case 35:
                    if(c_atual == '\n'){
                        estado = 0;
                        linha ++;
                        coluna = 0;
                        lexema = new StringBuilder();
                    }else if(c_atual_cod == -1){
                        return new Token(Tipo.EOF, "EOF", linha, coluna);
                    }
                    break;
                case 36:
                    if(c_atual == '*'){
                        estado = 38;
                    }else if(c_atual == '\n'){
                        linha ++;
                        coluna = 1;
                    }else if(c_atual_cod == -1){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Fim do arquivo alcançado sem fechar aspas simples.");
                        return new Token(Tipo.EOF, "EOF", linha, coluna);
                    }
                    break;
                case 38:
                    if(c_atual == '*'){   
                    }else if(c_atual == '/'){
                        estado = 0;
                        lexema = new StringBuilder();
                    }else if(c_atual_cod == -1){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Fim do arquivo alcançado sem fechar o comentário.");
                        return new Token(Tipo.EOF, "EOF", linha, coluna);
                    }else{
                        estado = 36;
                    }
                    break;      
                case 39:
                    if(c_atual == '"'){
                        estado = 25;
                        if(lexema.toString().length() < 2){
                            System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Literal Vazio.");
                            estado = 0;
                            lexema = new StringBuilder();
                        }else{
                            lexema.append(c_atual);
                            return new Token(Tipo.LIT, lexema.toString(), linha, coluna);
                        }
                    }else if(c_atual_cod == -1){
                        System.out.println("Erro Léxico na linha " + linha + " e coluna " + coluna + ". Fim do arquivo alcançado sem fechar aspas duplas.");
                        return new Token(Tipo.EOF, "EOF", linha, coluna);
                    }
                    break;
                    
        }
        }while(true);
    } 
    
    /**
     * Método Principal, que trata as exceções de arquivo, mostra os tokens encontrados pelo lexer e os tokens da tabela de símbolos. 
     * @param args .
     */
    
    /*
    public static void main(String[] args){
        try{
            LeArquivo();
            Token t;
            do{
                t = ProximoToken();
                System.out.println("< " + t.getTipo() + "  ,  '" + t.getLexema() + "' >    Linha: " + t.getLinha() + " ,\tColuna: " + t.getColuna());
            }while(t.getTipo() != Tipo.EOF);
            tabela.ImprimeTokens();
        }catch(Exception e){
            System.out.println("Erro no arquivo!");
        }
    }*/
}