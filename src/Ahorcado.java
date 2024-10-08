
import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
    static boolean gameOver=false;
    static String input="";
    static Scanner lector;
    public static void main(String[] args) {
        input = "";
        while(!input.equals("exit")){
            gameOver=false;
            input = "";
            String palabraSecreta = obtenerPalabraSecreta(); // ya tengo la palabra secreta.
            char [] letrasGuiones = obtenerGuionesFromPalabra(palabraSecreta); // ya tengo guiones en en vez de letras.
            lector = new Scanner(System.in);
            int intentos = 8; // contador para modificarse si no aciertas irá incrementandose.

        do{
            mostrarAhorcado(intentos);
            if(!gameOver){
            System.out.println("Tienes " + intentos +" intentos");
            System.out.println(letrasGuiones);
            System.out.println("Introduce una letra");
            //Recogemos la letra introducida por consola y la pasamos a mayúsculas para hacer la comparación
            input = lector.next();
            char letra = input.toUpperCase().charAt(0);
            if(!input.equals("restart")&&!input.equals("exit")){
                while(!esValido(letra)){
                    System.out.println("Input no valido");
                    System.out.println("Introduce una letra");
                    letra = lector.next().toUpperCase().charAt(0);
                }
                boolean algunaLetraAcertada = false;
                for(int i=0; i<palabraSecreta.length(); i++) {
                    if(palabraSecreta.charAt(i) == letra) { // para saber si la letra de la palabra secreta ubicada en i es igual a la letra introducida por el usuario
                        letrasGuiones[i] = letra;
                        algunaLetraAcertada = true; // si es igual continua el juego
                    }
                }
                if(!algunaLetraAcertada) { // si no es igual pues decrementa los intentos y se dibuja una parte del ahorcado
                    System.out.println("No has acertado");
                    intentos--;
                    if(intentos==-1){
                        System.out.println("Has perdido, agotaste todos los intentos"); // el juego terminará cuando el usuario agote sus intentos
                        gameOver = true;
                    }
                }
                else {
                    boolean gameWon = !hayGuiones(letrasGuiones);
                    if(gameWon) {
                        System.out.println(palabraSecreta);
                        System.out.println("Well done, game won");
                        gameOver = true;
                    }
                }
            
            }
            }
            
        }while(!gameOver&&!input.equals("exit")&&!input.equals("restart"));
            if(gameOver){
                do{
                    System.out.println("[exit]|[restart]");
                    input = new Scanner(System.in).next();
                }while(!input.equals("exit")&&!input.equals("restart"));
            }
        }
    }
    
    private static boolean esValido(char letra) {  //creamos un char array y condicionamos con if para admitir solo letras y evitar que se
        boolean esta = false;                     // le reste intentos al jugador si ingresa un caracter de otro tipo.
        char [] abecedario = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for(int i=0;i<26;i++){
            if(letra==abecedario[i]){
                esta = true;
            }
        }
        return esta;
    }

    private static void mostrarAhorcado(int i) { // con switch se ejecutarán los diferentes bloques que representarán los cambios que se generen
        switch (i) {                             //durante los 8 intentos que tiene el jugador, a medida que falle saldrá una parte del dibujo del ahorcado.

            case 7:
            System.out.println(" | ");
            System.out.println(" | ");
            System.out.println(" | ");
            System.out.println(" | ");
            System.out.println(" | ");
            System.out.println(" | ");
            System.out.println(" | ");

            for (int j = 0; j< 10; j++) {
                System.out.println(" |");

            }
            System.out.println("__________");
            break;                              //con break conseguimos que cuando el jugador acierte la letra, no se ejecute el switch y el juego continúe,
                                                // de lo contrario los bloques con las diferentes partes del dibujo del ahorcado se generarían en el primer desacierto.
            case 6:
            System.out.println(" ---------------------");
            System.out.println(" | ");
            System.out.println(" | ");
            System.out.println(" | ");
            System.out.println(" | ");
            System.out.println(" | ");
            System.out.println(" | ");

            for (int j = 0; j< 10; j++) {
                System.out.println(" |");

            }
            System.out.println("__________");
            break;

            case 5:
            System.out.println(" ---------------------");
            System.out.println(" |                     |");
            System.out.println(" |                     |");
            System.out.println(" |                  -------");
            System.out.println(" |                 | -  -  |");
            System.out.println(" |                 |   -   |");
            System.out.println(" |                  -------");
            for (int j = 0; j<10; j++) {
                System.out.println(" |");

            }
            System.out.println("__________");
            break;

        case 4:
            System.out.println(" ---------------------");
            System.out.println(" |                     |");
            System.out.println(" |                     |");
            System.out.println(" |                  -------");
            System.out.println(" |                 | -  -  |");
            System.out.println(" |                 |   -   |");
            System.out.println(" |                  -------");
            System.out.println(" |                     |   ");
            System.out.println(" |                     |   ");
            System.out.println(" |                     |   ");
            System.out.println(" |                     |   ");
            System.out.println(" |                     |   ");
            for (int j = 0; j<5; j++) {
                System.out.println(" |");

            }
            System.out.println("__________");
            break;

        case 3:
            System.out.println(" ---------------------");
            System.out.println(" |                     |");
            System.out.println(" |                     |");
            System.out.println(" |                  -------");
            System.out.println(" |                 | -  -  |");
            System.out.println(" |                 |   -   |");
            System.out.println(" |                  -------");
            System.out.println(" |                     |   ");
            System.out.println(" |                  /  |   ");
            System.out.println(" |                 /   |   ");
            System.out.println(" |                /    |   ");
            System.out.println(" |                     |   ");
            for (int j = 0; j<5; j++) {
                System.out.println(" |");

            }
            System.out.println("__________");
            break;

        case 2:
            System.out.println(" ---------------------");
            System.out.println(" |                     |");
            System.out.println(" |                     |");
            System.out.println(" |                  -------");
            System.out.println(" |                 | -  -  |");
            System.out.println(" |                 |   -   |");
            System.out.println(" |                  -------");
            System.out.println(" |                     |   ");
            System.out.println(" |                   / | \\ ");
            System.out.println(" |                  /  |  \\ ");
            System.out.println(" |                 /   |   \\ ");
            System.out.println(" |                     |   ");
            for (int j = 0; j<5; j++) {
                System.out.println(" |");

            }
            System.out.println("__________");
            break;

        case 1:
            System.out.println(" ---------------------");
            System.out.println(" |                     |");
            System.out.println(" |                     |");
            System.out.println(" |                  -------");
            System.out.println(" |                 | -  -  |");
            System.out.println(" |                 |   n   |");
            System.out.println(" |                  -------");
            System.out.println(" |                     |   ");
            System.out.println(" |                   / | \\ ");
            System.out.println(" |                  /  |  \\ ");
            System.out.println(" |                 /   |   \\ ");
            System.out.println(" |                     |   ");
            System.out.println(" |                    /  ");
            System.out.println(" |                   /      ");
            System.out.println(" |                  /       ");
            for (int j = 0; j<2; j++) {
                System.out.println(" |");

            }
            System.out.println("__________");
            break;

        case 0:
            System.out.println(" ---------------------");
            System.out.println(" |                     |");
            System.out.println(" |                     |");
            System.out.println(" |                  -------");
            System.out.println(" |                 | X  X  |");
            System.out.println(" |                 |   -   |");
            System.out.println(" |                  -------");
            System.out.println(" |                     |   ");
            System.out.println(" |                   / | \\ ");
            System.out.println(" |                  /  |  \\ ");
            System.out.println(" |                 /   |   \\ ");
            System.out.println(" |                     |   ");
            System.out.println(" |                    / \\");
            System.out.println(" |                   /   \\  ");
            System.out.println(" |                  /     \\ ");
            for (int j = 0; j<2; j++) {
                System.out.println(" |");
            }
            System.out.println("__________");
            System.out.println("GAME OVER");
            gameOver=true;
            break;
        }
                
    }
    static String obtenerPalabraSecreta(){
        String [] palabras = {"GALAXIA","ESTRELLA","LUNA"}; // array para definir las palabras secretas que serán 3 en este caso.
        Random r = new Random(); // clase para generar un numero aleatorio que definirá la palabras secreta.
        int n = r.nextInt(palabras.length); // numero aleatorio=palabra secreta.
        return palabras[n]; // la palabra secreta será aleatoria.
    }

    static char [] obtenerGuionesFromPalabra(String palabra){
        int nLetrasPalabraSecreta = palabra.length(); //Quiero que aparezcan guiones en lugar de la palabra. Primero inicializo una variable que me dirá la cantidad de letras de la palabra secreta
        char [] letrasGuiones = new char[nLetrasPalabraSecreta]; // y luego otra variable que permita aparecer guiones en lugar de letras.

        for(int i=0; i<letrasGuiones.length; i++){         // un bucle for para que la cantidad de guiones sean iguales a la cantidad de letras que tenga la palabra secreta.
            letrasGuiones[i] = '_';
        }
        return letrasGuiones;
    }

    static boolean hayGuiones(char[] array) { // metodo para evaluar si un array que le paso tiene guion bajo.
    for(char l:array) {
        if(l=='_')return true;
    }
    return false;
    }
}
    