package lexico;

/**
 * Enumerador usado para facilitar a identificação dos tokens da linguagem.
 * @author Iago Arantes, Jean Silva
 */
public enum Tipo {
    // Palavras Chave.
    KW_PROGRAM,
    KW_IF,
    KW_ELSE,
    KW_WHILE,
    KW_WRITE,
    KW_READ,
    KW_NUM,
    KW_CHAR,
    KW_NOT,
    KW_OR,
    KW_AND,
    
    // Simbolos.
    SMB_SEM,
    SMB_COM,
    SMB_CPA,
    SMB_OPA,
    SMB_CBC,
    SMB_OBC,
    
    // Operadores
    OP_MUL,
    OP_DIV,
    OP_LT,
    OP_LE,
    OP_AD,
    OP_MIN,
    OP_GT,
    OP_GE,
    OP_NE,
    OP_ASS,
    OP_EQ,
    
    // Identificador e Literal
    ID,
    LIT,
    
    // Constantes
    CON_CHAR,
    CON_NUM,
    
    // Fim do Arquivo
    EOF,
    
    // Vazio
    
    VAZIO,
    
    // Não terminais da gramática
    P,
    B,
    DL,
    D,
    TY,
    IL,
    IL_,
    SL,
    S,
    AS,
    IS,
    IS_,
    CN,
    WHS,
    SP,
    RS,
    WRS,
    W,
    E,
    E_,
    SE,
    SE_,
    TE,
    TE_,
    FA,
    FA_,
    F,
    R,
    A,
    M,
    CT;
}
