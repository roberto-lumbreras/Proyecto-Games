import java.util.Random;
import java.util.Scanner;

public class AdivinaElNumero{
    public static boolean esValido(String s){
        int n;
        try {
            n = Integer.parseInt(s);
            if(n < 101|| n > -1){
                return true;
            } else {return false;}
            
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args) {
        String numeroSecreto = String.valueOf (new Random().nextInt(0, 101));
        int numeroIntentos = 0;
        String input = "";
        System.out.println("Juego de adivinar el numero");
        System.out.println("Introduzca un numero entre 0 y 100");
        while(input!=numeroSecreto){
            if(! input.equals("")){
                System.out.println("Prueba otra vez");
            }
            input = new Scanner(System.in).next();
            while(! esValido(input)){
                System.out.println("Input no valido. Introduzca un numero entre 0 y 100");
                input = new Scanner(System.in).next();
            }
            numeroIntentos++;
            if(Integer.valueOf(input)> Integer.valueOf(numeroSecreto)){
                System.out.println("El numero secreto es mas pequeño");
            }else if (Integer.valueOf(input)< Integer.valueOf(numeroSecreto)) {
                System.out.println("El numero secreto es mas grande");
            }
        }
    }
}