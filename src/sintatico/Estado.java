package sintatico;

/**
 *
 * @author Iago
 */
public class Estado {
    private final int id;
    private final String[] entradas;
    private final int[] saidas;
    private final int[][] gotos;

    public Estado(int id, String[] entradas, int[] saidas, int[][] gotos) {
        this.id = id;
        System.out.println("\n" + this.id + ":");
        this.entradas = entradas;
        this.saidas = saidas;
        this.gotos = gotos;
        shift();
        goTo();
    }
    
    public void shift(){
        for(int i=0; i<saidas.length;i++){
            System.out.println(entradas[i] + " " + saidas[i] );
        }
    }
    
    public void goTo(){
        for(int i=0; i<gotos.length;i++){
            System.out.println(gotos[i][0] + " " + gotos[i][1]);
        }
    }
}
