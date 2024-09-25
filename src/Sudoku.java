
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
    static String input = "";
    static int [][]tablero = new int[9][9];
    static int [][]plantilla= {
        {0,8,0,5,7,6,2,0,0},
        {0,0,0,4,0,2,0,0,0},
        {0,0,0,0,3,9,5,4,8},
        {6,3,0,9,0,0,8,5,2},
        {0,9,0,2,0,0,3,7,0},
        {8,0,0,0,5,0,6,9,4},
        {2,5,7,6,0,3,4,8,9},
        {3,0,8,7,0,0,0,2,5},
        {0,4,0,0,0,0,0,0,6}
    }; 
    static int[][] solucion = {
        {9,8,4,5,7,6,2,1,3},
        {5,1,3,4,8,2,9,6,7},
        {7,2,6,1,3,9,5,4,8},
        {6,3,1,9,4,7,8,5,2},
        {4,9,5,2,6,8,3,7,1},
        {8,7,2,3,5,1,6,9,4},
        {2,5,7,6,1,3,4,8,9},
        {3,6,8,7,9,4,1,2,5},
        {1,4,9,8,2,5,7,3,6}
    };

    public static void main(String[] args) {
        while(!input.equals("E")){
            input="";
            System.out.println("Resuelve el sudoku");
            System.out.println("[R]Restart|[E]Exit|[S]Solution");
            for(int i=0;i<tablero.length;i++){
                tablero[i]=Arrays.copyOf(plantilla[i], tablero.length);
            }
            while(!Arrays.deepEquals(tablero, solucion)&&!input.equals("E")&&!input.equals("R")&&!input.equals("S")){
                introducirValor();
            }
            if(Arrays.deepEquals(tablero, solucion)){
                System.out.println("Felicidades has completado el sudoku");
                for(int[]fila:solucion){
                    System.out.println(Arrays.toString(fila));
                }
                do { 
                    System.out.println("[R]Restart|[E]Exit");
                    input = new Scanner(System.in).next().toUpperCase();
                } while (!input.equals("E")&&!input.equals("R"));
            }
            if(input.equals("E")){
                System.out.println("Fin del programa");
            }
            if(input.equals("R")){
                System.out.println("Limpiando el tablero...");
            }
            if(input.equals("S")){
                System.out.println("--Solucion--");
                for(int[]fila:solucion){
                    System.out.println(Arrays.toString(fila));
                }
                do { 
                    System.out.println("[R]Restart|[E]Exit");
                    input = new Scanner(System.in).next().toUpperCase();
                } while (!input.equals("E")&&!input.equals("R"));
            }
        }
        
    }

    private static void introducirValor() {
        int fila;
        int columna;
        int valor;
        boolean done=false;
        String statement="Input no valido";
        do{
            for(int[]f:tablero){
                System.out.println(Arrays.toString(f));
            }
            System.out.println("Introducir fila");
            input = new Scanner(System.in).next().toUpperCase();
            if(esValido(input)&&!input.equals("0")&&!input.equals("E")&&!input.equals("R")&&!input.equals("S")){
                fila=Integer.valueOf(input)-1;
                System.out.println("Introducir columna");
                input = new Scanner(System.in).next().toUpperCase();
                if(esValido(input)&&!input.equals("0")&&!input.equals("E")&&!input.equals("R")&&!input.equals("S")){
                    columna=Integer.valueOf(input)-1;
                    if(esModificable(fila, columna)){
                        System.out.println("Introducir valor");
                        input = new Scanner(System.in).next().toUpperCase();
                        if(esValido(input)&&!input.equals("E")&&!input.equals("R")&&!input.equals("S")){
                            valor = Integer.valueOf(input);
                            if(check(fila, columna, valor)){
                                escribeValor(fila,columna,valor);
                                done=true;
                            }else{
                                statement="Este valor no se puede introducir en el lugar especificado";
                            }
                        } 
                        
                    }
                    
                }
            }
            if(!done&&!input.equals("E")&&!input.equals("R")&&!input.equals("S")){
                System.out.println(statement);
            }
        }while(!done&&!input.equals("E")&&!input.equals("R")&&!input.equals("S"));
    }


    private static void escribeValor(int fila, int columna, int valor) {
        tablero[fila][columna]=valor;
    }

    private static boolean check(int fila, int columna, int valor) {
        boolean check=true;
        //verifica columna
        for(int i = 0;i<tablero.length&&check;i++){
            check = tablero[i][columna]!=valor;
        }
        //vertifica fila
        for(int i = 0;i<tablero.length&&check;i++){
            check = tablero[fila][i]!=valor;
        }
        //verifica subcuadro
        if(check){
            fila = (fila/3)*3;
            columna = (columna/3)*3;
            for(int i = fila;i<fila+3&&check;i++){
                for(int j = columna;j<columna+3&&check;j++){
                    check = tablero[i][j]!=valor;
                }
            }
        }
        return check;
    }

    private static boolean esModificable(int fila, int columna) {
        return plantilla[fila][columna]==0;
    }

    private static boolean esValido(String s) {
        int n;
        if(s.equals("E")||s.equals("R")||s.equals("S")){
            return true;
        }else{
            try {
                n = Integer.parseInt(s);
                return n>=0&&n<10;
            } catch (NumberFormatException e) {
            }
        }
        return false;
    }
}
