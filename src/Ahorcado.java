import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
    public static void main(String[] args) {
    //
        String palabraSecreta = obtenerPalabraSecreta(); // ya tengo la palabra secreta.
        char [] letrasGuiones = obtenerGuionesFromPalabra(palabraSecreta); // ya tengo guiones en en vez de letras.
        boolean gameOver = false;
        Scanner lector = new Scanner(System.in);
        int intentos = 3; // contador para modificarse si no aciertas irá incrementandose.

        do{
            System.out.println("Tienes " + intentos +" intentos");
            System.out.println(letrasGuiones);
            System.out.println("Introduce una letra");
            char letra = lector.next().charAt(0);
            boolean algunaLetraAcertada = false;
            for(int i=0; i<palabraSecreta.length(); i++) {
                if(palabraSecreta.charAt(i) == letra) {
                    letrasGuiones[i] = letra;
                    algunaLetraAcertada = true;
                }
            }
            if(!algunaLetraAcertada) {
                System.out.println("No has acertado");
                --intentos;
                if(intentos==0){
                    System.out.println("Has perdido, agotaste todos los intentos"); // el juego terminará cuando el usuario agote sus intentos
                    gameOver = true;
                }
            }
            else {
                boolean gameWon = !hayGuiones(letrasGuiones);
                if(gameWon) {
                    System.out.println("Well done, game won");
                    gameOver = true;
                }
            }
        }while(!gameOver);
        
        lector.close();
    }
    
    static String obtenerPalabraSecreta(){
        String [] palabras = {"Galaxia","Estrella","Luna"}; // array para definir las palabras secretas que serán 3 en este caso.
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