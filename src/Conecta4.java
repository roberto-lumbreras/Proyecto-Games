
import java.util.Random;
import java.util.Scanner;

public class Conecta4 {
    static char[][]cuadricula = new char[6][7];
    
    static final char j1 = 'O';
    static final char j2 = 'X';
    static String input="";
    static int nColumna;
    static char gameMode;
    static String turno;

    private static void introduceFicha(char c, int seleccionaColumna) {
        boolean fichaIntroducida=false;
        for(int i = 5;!fichaIntroducida;i--){
            if(cuadricula[i][seleccionaColumna-1]!=j1&&cuadricula[i][seleccionaColumna-1]!=j2){
                cuadricula[i][seleccionaColumna-1]=c;
                fichaIntroducida=true;
            }
        } 
    }

    public static boolean esValido(String s){
        int n;
        try {
            n = Integer.parseInt(s);
            if(n < 8 && n > 0 && cuadricula[0][n-1]!=j1 && cuadricula[0][n-1]!=j2){
                return true;
            } else {return false;}
        } catch (NumberFormatException e) {
            return input.equals("exit")||input.equals("restart");
        }
    }

    public static int seleccionaColumna(char c){
        System.out.println("Seleccionar columna para introducir ficha "+c);
        input = new Scanner(System.in).next();
        while(!esValido(input)){
            System.out.println("Input no valido");
            System.out.println("Seleccionar columna para introducir ficha");
            input = new Scanner(System.in).next();
        }
        if(input.equals("exit")||input.equals("restart")){
            return 0;
        }
        return Integer.parseInt(input);
    }

    public static boolean tableroLleno(){
        for(int i = 0;i<cuadricula.length;i++){
            if(cuadricula[0][i]!=j1
            ||cuadricula[0][i]!=j2){
                return false;
            }
        }
        return true; 
    }

    public static boolean hayGanador(){

        // Comprobacion vertical
        for(int i = 0;i<cuadricula.length;i++){
            for(int j = 0;j<cuadricula[0].length-3;j++){
                if((cuadricula[i][j]==j1||cuadricula[i][j]==j2)
                &&cuadricula[i][j]==cuadricula[i][j+1]
                &&cuadricula[i][j]==cuadricula[i][j+2]
                &&cuadricula[i][j]==cuadricula[i][j+3]){
                    return true;
                }
            }
        }
        // Comprobacion horizontal
        for(int i = 0;i<cuadricula.length-3;i++){
            for(int j = 0;j<cuadricula[0].length;j++){
                if((cuadricula[i][j]==j1||cuadricula[i][j]==j2)
                &&cuadricula[i][j]==cuadricula[i+1][j]
                &&cuadricula[i][j]==cuadricula[i+2][j]
                &&cuadricula[i][j]==cuadricula[i+3][j]){
                    return true;
                }
            }
        }
        // Comprobacion diagonal
        for(int i = 0;i<cuadricula.length;i++){
            for(int j = 0;j<cuadricula[0].length;j++){
                try {
                    if((cuadricula[i][j]==j1||cuadricula[i][j]==j2)
                    &&cuadricula[i][j]==cuadricula[i+1][j+1]
                    &&cuadricula[i][j]==cuadricula[i+2][j+2]
                    &&cuadricula[i][j]==cuadricula[i+3][j+3]){
                    return true;
                }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    if((cuadricula[i][j]==j1||cuadricula[i][j]==j2)
                    &&cuadricula[i][j]==cuadricula[i-1][j-1]
                    &&cuadricula[i][j]==cuadricula[i-2][j-2]
                    &&cuadricula[i][j]==cuadricula[i-3][j-3]){
                    return true;
                }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    if((cuadricula[i][j]==j1||cuadricula[i][j]==j2)
                    &&cuadricula[i][j]==cuadricula[i+1][j-1]
                    &&cuadricula[i][j]==cuadricula[i+2][j-2]
                    &&cuadricula[i][j]==cuadricula[i+3][j-3]){
                    return true;
                }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    if((cuadricula[i][j]==j1||cuadricula[i][j]==j2)
                    &&cuadricula[i][j]==cuadricula[i-1][j+1]
                    &&cuadricula[i][j]==cuadricula[i-2][j+2]
                    &&cuadricula[i][j]==cuadricula[i-3][j+3]){
                    return true;
                }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        while(!input.equals("exit")){
            input="";
            System.out.println("Conecta 4 para ganar");
            do{
                System.out.println("Seleccionar modo de juego [[S]Solitario|[M]Multijugador]");
                gameMode=new Scanner(System.in).next().toLowerCase().charAt(0);
            }while(gameMode!='s'&&gameMode!='m');
            for(int i=0;i<cuadricula.length;i++){
                for(int j = 0;j<cuadricula[0].length;j++){
                    cuadricula[i][j]=' ';
                }
            }
            while(!hayGanador()&&!tableroLleno()&&!input.equals("exit")&&!input.equals("restart")){
                mostrarCuadricula();
                turno="Jugador 1";
                nColumna=seleccionaColumna(j1);
                if(nColumna!=0){
                    introduceFicha(j1,nColumna);
                }
                if(!hayGanador()&&!tableroLleno()&&!input.equals("exit")&&!input.equals("restart")){
                    mostrarCuadricula();
                    turno="Jugador 2";
                    if(gameMode=='m'){
                        nColumna=seleccionaColumna(j2);
                    }
                    if(gameMode=='s'){
                        do { 
                            nColumna=new Random().nextInt(1, 8);
                        } while (!esValido(String.valueOf(nColumna)));
                    }
                    if(nColumna!=0){
                        introduceFicha(j2,nColumna);
                    }
                }
            }
            if(hayGanador()){
                System.out.println(turno+" es el ganador!!!");
                mostrarCuadricula();

            }
            if(tableroLleno()){
                System.out.println("Empate...");
                mostrarCuadricula();
            }
            while(!input.equals("exit")&&!input.equals("restart")){
                System.out.println("[exit/restart]");
                input = new Scanner(System.in).next();
            }
        }    
    }

    private static void mostrarCuadricula() {
        for (int i = 0;i<cuadricula.length;i++){
            System.out.println("+---+---+---+---+---+---+---+");
            for(int j=0;j<cuadricula[0].length;j++){
                System.out.print("| "+cuadricula[i][j]+" ");
            }
            System.out.println("|");
        }
        System.out.println("+---+---+---+---+---+---+---+");
    }
}
