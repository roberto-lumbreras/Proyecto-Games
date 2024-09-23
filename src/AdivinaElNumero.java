import java.util.Random;
import java.util.Scanner;

public class AdivinaElNumero{
    public static void main(String[] args) {
        int numeroSecreto = new Random().nextInt(0, 101);
        int numeroIntentos = 0;
        int input = -2;
        System.out.println("Juego de adivinar el numero");
        System.out.println("Introduzca un numero entre 0 y 100");
        while(input!=numeroSecreto){
            if(input!=-2){
                System.out.println("Prueba otra vez");
            }
            input = new Scanner(System.in).nextInt();
            while(input<0||input>100){
                System.out.println("Input no valido. Introduzca un numero entre 0 y 100");
                input = new Scanner(System.in).nextInt();
            }
            numeroIntentos++;
            if(input>numeroSecreto){
                System.out.println("El numero secreto es mas pequeño");
            }else if (input<numeroSecreto) {
                System.out.println("El numero secreto es mas grande");
            }
        }
    }
}