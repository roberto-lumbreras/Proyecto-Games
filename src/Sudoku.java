
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
    //esta clase simula un juego de sudoku en el terminal
    static String input = "";
    static int [][]tablero = new int[9][9];
    //tablero plantilla
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
    //tablero solucion
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
        //mientras no se indique explictamente el juego continuará
        while(!input.equals("exit")){
            input="";
            System.out.println("Resuelve el sudoku");
            //indicación de los posibles comandos ejecutables en cualquier momento del programa
            System.out.println("Comandos -> [restart]|[exit]|[solution]");
            //se crea una copia de la matriz que sirve de la plantilla del ejercicio, el jugador escribirá en la copia
            for(int i=0;i<tablero.length;i++){
                tablero[i]=Arrays.copyOf(plantilla[i], tablero.length);
            }
            //este bucle evalua las variables que pueden resultar en la finalizacion del juego
            while(!Arrays.deepEquals(tablero, solucion)&&!input.equals("exit")&&!input.equals("restart")&&!input.equals("solution")){
                introducirValor();
            }
            //manejo de los posibles resultados del programa
            //felicita al jugador por completar el tablero
            if(Arrays.deepEquals(tablero, solucion)){
                System.out.println("Felicidades has completado el sudoku!");
                mostrarCuadricula(tablero);
                do { 
                    System.out.println("[restart]|[exit]");
                    input = new Scanner(System.in).next();
                } while (!input.equals("exit")&&!input.equals("restart"));
            }
            //finaliza el proceso
            if(input.equals("exit")){
                System.out.println("Fin del programa");
            }
            //comienza el bucle principal de nuevo
            if(input.equals("restart")){
                System.out.println("Limpiando el tablero...");
            }
            //enseña la solucion
            if(input.equals("solution")){
                System.out.println("--Solucion--");
                mostrarCuadricula(solucion);
                do { 
                    System.out.println("[restart]|[exit]");
                    input = new Scanner(System.in).next();
                } while (!input.equals("exit")&&!input.equals("restart"));
            }
        }
        
    }

    //este método condensa la lógica principal del juego, para ello se respalda en otros métodos
    //que aseguraran el cumplimiento de las normas del Sudoku y el correcto funcionamiento del programa 
    private static void introducirValor() {
        int fila;
        int columna;
        int valor;
        boolean done=false;
        String statement;
        do{
            statement="Input no valido";
            mostrarCuadricula(tablero);
            //Pide al usuario que introduzca una fila y verifica si es valido
            System.out.println("Introducir fila");
            input = new Scanner(System.in).next();
            if(esValido(input)&&!input.equals("0")&&!input.equals("exit")&&!input.equals("restart")&&!input.equals("solution")){
                fila=Integer.valueOf(input)-1;
                //Pide al usuario que introduzca una fila y verifica si es valido
                System.out.println("Introducir columna"); 
                input = new Scanner(System.in).next();
                if(esValido(input)&&!input.equals("0")&&!input.equals("exit")&&!input.equals("restart")&&!input.equals("solution")){
                    columna=Integer.valueOf(input)-1;
                    //verifica si la coordenada seleccionada se puede modificar en funcion de si viene dada por la plantilla
                    if(esModificable(fila, columna)){
                        //Pide al usuario que introduzca una valor y verifica si es valido
                        System.out.println("Introducir valor");
                        input = new Scanner(System.in).next();
                        if(esValido(input)&&!input.equals("exit")&&!input.equals("restart")&&!input.equals("solution")){
                            valor = Integer.valueOf(input);
                            //Comprueba que se cumplan las normas del sudoku y da feedback al jugador.Si se cumplen, introduce el valor en el tablero
                            if(check(fila, columna, valor)){
                                escribeValor(fila,columna,valor);
                                done=true;
                                System.out.println("Valor introducido correctamente");
                            }else{
                                statement="Este valor no se puede introducir en el lugar especificado";
                            }
                        } 
                        
                    }
                    
                }
            }
            //da feedback de por qué se ha interrumpido el flujo del juego[input no valido/no se cumplen las normas]
            //hasta que no se completen las condiciones del flujo este seguira pidiendo datos de insercion
            if(!done&&!input.equals("exit")&&!input.equals("restart")&&!input.equals("solution")){
                System.out.println(statement);
            }
        }while(!done&&!input.equals("exit")&&!input.equals("restart")&&!input.equals("solution"));
    }

    //abstraccion de asignacion de valor
    private static void escribeValor(int fila, int columna, int valor) {
        tablero[fila][columna]=valor;
    }

    //metodo para verificar si un input no rompe las reglas del juego, devuelve 'false' si no es posible la insercion
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

    //la posicion elegida es modificable si en la plantilla original tenia valor 0
    private static boolean esModificable(int fila, int columna) {
        return plantilla[fila][columna]==0;
    }

    //controla que no se produzcan errores por introduccion de datos por consola
    private static boolean esValido(String s) {
        int n;
        if(s.equals("exit")||s.equals("restart")||s.equals("solution")){
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

    //metodo para mostrar el tablero de manera bonita
    private static void mostrarCuadricula(int[][]board) {
        for (int i = 0;i<board.length;i++){
            System.out.println("  +---+---+---+---+---+---+---+---+---+");
            System.out.print((i+1)+" |");
            for(int j=0;j<board[0].length;j++){
                System.out.print(" "+board[i][j]+" |");
            }
            System.out.println("");
        }
        System.out.println("  +---+---+---+---+---+---+---+---+---+");
        System.out.println("    1   2   3   4   5   6   7   8   9");
    }
}
