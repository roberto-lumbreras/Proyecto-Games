import java.util.Random;
import java.util.Scanner;

public class AdivinaElNumero{
    
    //metodo para verificar que el input es un numero entre 0 y 100
    public static boolean esValido(String s){
        int n;
        if(s.equals("exit")||s.equals("restart")){
            return true;
        }
        try {
            n = Integer.parseInt(s);
            if(n < 101 && n > -1){
                return true;
            } else {return false;}
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args) {
        String numeroSecreto;
        String input = "";
        int numeroIntentos;
        //mientras no se introduzca exit para salir del programa seguira ejecutandose
        while(!input.equals("exit")){
            //genera el numero secreto entre 0 y 100
            numeroSecreto = String.valueOf (new Random().nextInt(0, 101));
            //contador del numero de intentos para adivinar el numero
            numeroIntentos = 0;
            input = "";
            //le pide input al usuario
            System.out.println("Juego de adivinar el numero");
            System.out.println("Introduzca un numero entre 0 y 100");
            //mientras no se introduzca restart, exit o se adivine el numero el programa seguira pidiendo numeros
            while(!input.equals(numeroSecreto)&&!input.equals("exit")&&!input.equals("restart")){
                if(!input.equals("")){
                    System.out.println("Prueba otra vez");
                }
                input = new Scanner(System.in).next();
                while(!esValido(input)){
                    System.out.println("Input no valido. Introduzca un numero entre 0 y 100");
                    input = new Scanner(System.in).next();
                }
                if(!input.equals("exit")&&!input.equals("restart")){
                    //si el input es un numero valido registra el intento 
                    numeroIntentos++;
                    //da pistas al jugador si no ha acertado
                    if(Integer.valueOf(input)> Integer.valueOf(numeroSecreto)){
                        System.out.println("El numero secreto es mas pequeño");
                    }else if (Integer.valueOf(input)< Integer.valueOf(numeroSecreto)) {
                        System.out.println("El numero secreto es mas grande");
                    }else{
                        //felicita al jugador por acertar el numero
                        System.out.println("Felicidades, has acertado!! Numero de intentos "+numeroIntentos);
                        input = new Scanner(System.in).next();
                        while(!(input.equals("exit")||input.equals("restart"))){
                            System.out.println("Opcion no valida");
                            input = new Scanner(System.in).next();
                        }
                    }
                }
                if(input.equals("exit")){
                    System.out.println("Fin del programa");
                }
                if(input.equals("restart")){
                    System.out.println("Nueva partida");
                }
            }
        }        
    }
}