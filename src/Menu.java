import java.util.Scanner;

public class Menu {
    Scanner teclado = new Scanner(System.in);
    Banco banco = new Banco();

    public Menu(){
        while (true){
            System.out.println("1) Sacar");
            System.out.println("2) Depositar");
            System.out.println("3) Encontrar conta");
            System.out.println("4) Criar conta");
            System.out.println("5) Sair");
        }
    }
}