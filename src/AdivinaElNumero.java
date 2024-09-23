import java.util.Random;
import java.util.Scanner;

public class AdivinaElNumero{
    
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
        while(!input.equals("exit")){
            numeroSecreto = String.valueOf (new Random().nextInt(0, 101));
            numeroIntentos = 0;
            input = "";
            System.out.println("Juego de adivinar el numero");
            System.out.println("Introduzca un numero entre 0 y 100 ['exit' para salir/'restart' para reiniciar]");
            while(!input.equals(numeroSecreto)&&!input.equals("exit")&&!input.equals("restart")){
                if(!input.equals("")){
                    System.out.println("Prueba otra vez ['exit' para salir/'restart' para reiniciar]");
                }
                input = new Scanner(System.in).next();
                while(!esValido(input)){
                    System.out.println("Input no valido. Introduzca un numero entre 0 y 100 ['exit' para salir/'restart' para reiniciar]");
                    input = new Scanner(System.in).next();
                }
                if(!input.equals("exit")&&!input.equals("restart")){
                    numeroIntentos++;
                    if(Integer.valueOf(input)> Integer.valueOf(numeroSecreto)){
                        System.out.println("El numero secreto es mas pequeño");
                    }else if (Integer.valueOf(input)< Integer.valueOf(numeroSecreto)) {
                        System.out.println("El numero secreto es mas grande");
                    }else{
                        System.out.println("Felicidades, has acertado!! Numero de intentos "+numeroIntentos+" ['exit' para salir/'restart' para reiniciar]");
                        input = new Scanner(System.in).next();
                        while(!(input.equals("exit")||input.equals("restart"))){
                            System.out.println("Opcion no valida ['exit' para salir/'restart' para reiniciar]");
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