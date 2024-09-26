import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input = "";
        boolean exit=false;
        while(!exit){
            do{
                System.out.println("Introduzca una opción");
                System.out.println("[1] Sudoku");
                System.out.println("[2] Conecta 4");
                System.out.println("[3] Adivina El Numero");
                System.out.println("[4] Ahorcado");
                System.out.println("[exit] Finaliza el programa");
                input = new Scanner(System.in).next();
                if(!input.equals("1")&&!input.equals("2")&&!input.equals("3")&&!input.equals("4")&&!input.equals("exit")){
                    System.out.println("Input no valido");
                }
                if(input.equals("exit")){
                    exit=true;
                }
            }while(!input.equals("1")&&!input.equals("2")&&!input.equals("3")&&!input.equals("4")&&!input.equals("exit"));
            switch(input){
                case "1":
                Sudoku.main(args);
                break;
                case "2":
                Conecta4.main(args);
                break;
                case "3":
                AdivinaElNumero.main(args);
                break;
                case "4":
                Ahorcado.main(args);
                break;
                default:
            }
        }
        
    }
}
