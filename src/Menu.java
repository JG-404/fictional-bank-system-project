import java.util.Scanner;

public class Menu {
    Scanner teclado = new Scanner(System.in);
    Banco banco = new Banco();

    //Quando o menu for inicializado ele vai rodar o codigo em loop até ser interrompido
    public Menu(){
        while (true){
            System.out.println("1) Sacar");
            System.out.println("2) Depositar");
            System.out.println("3) Encontrar conta");
            System.out.println("4) Criar conta");
            System.out.println("0) Sair");

            OpcaoAcoes opcao = opcoesValidas();
            this.teclado.nextLine();

            System.out.println();
            switch (opcao) {
                case SACAR:
                    caseSacar();
                    break;
                case DEPOSITAR:
                    caseDepositar();
                    break;
                case CRIAR_CONTA:
                    caseCriaConta();
                    break;
                case ENCONTRAR_CONTA:
                    caseEncontrarConta();
                    break;
                default:
                    break;
            }
            System.out.println();
        }
    }

    //metodo para procurar se a opção digitada existe no menu
    private static Object[] procuraOpção(int opcaoInserida, OpcaoAcoes[] listaDeOpcoes){
        for (int i = 0; i < listaDeOpcoes.length; i++){
            if (listaDeOpcoes[i].getAcao() == opcaoInserida){
                Object ret[] = {true, listaDeOpcoes[i]};
                return ret;
            }
        }
        Object[] ret = {false};
        return ret;
    }

    //metodo para realizar a validação da opção do menu
    private OpcaoAcoes opcoesValidas(){
        OpcaoAcoes[] opcoes = {OpcaoAcoes.SACAR, OpcaoAcoes.DEPOSITAR, OpcaoAcoes.ENCONTRAR_CONTA, OpcaoAcoes.CRIAR_CONTA, OpcaoAcoes.SAIR};

        while (true){
            try{
                System.out.print(">>>: ");
                int opcao = this.teclado.nextInt();

                Object[] procurou = procuraOpção(opcao, opcoes);
                boolean achou = (boolean)procurou[0];

                if (!achou) throw new Exception("Opção invalida");
                OpcaoAcoes escolha = (OpcaoAcoes)procurou[1];
                return escolha;
            }
            catch(Exception error){
                System.err.println(error.getMessage());
                teclado.next();
            }
        }
    }

    //metodo pra verificar se a digitação do nome não está vazia
    private String digitouCorretamente(){
        while (true) {
            try{
                System.out.print(">>>: ");
                String texto = this.teclado.nextLine();
                if (texto == null || texto.isBlank()) throw new Exception("Texto vazio");
                return texto;
            }
            catch(Exception error){
                System.err.println(error.getMessage());
            }
        }   
    }

    //metodo que realiza a opção de sacar quando for selecionado no menu (contem bug)
    private void caseSacar(){
        while (true){
            try{
                System.out.println("Numero da conta");
                System.out.print(">>>: ");
                int numeroConta = this.teclado.nextInt(); //valores em branco estão sendo computados

                Object[] achouConta = this.banco.encontraConta(numeroConta);
                boolean achou = (boolean)achouConta[0];

                if (!achou) throw new Exception("Conta não encontrada");
                Conta conta = (Conta)achouConta[1];
                System.out.println(conta);

                System.out.println("Valor a sacar: ");
                int valorASacar = this.teclado.nextInt(); //valores em branco estão sendo computados
                
                conta.sacar(valorASacar);
                break;
            }
            catch(Exception error){
                System.err.println(error.getMessage());
                teclado.next();
            }
        }
    }

    private void caseDepositar(){
        //Aqui eu vou fazer a parte de depositar
    }

    private void caseEncontrarConta(){
        while (true){
            try{
                System.out.println("Numero da conta");
                System.out.print(">>>: ");
                int numeroConta = this.teclado.nextInt(); //valores em branco estão sendo computados 

                Object[] achouConta = this.banco.encontraConta(numeroConta);
                boolean achou = (boolean)achouConta[0];

                if (!achou) throw new Exception("Conta não encontrada");
                Conta conta = (Conta)achouConta[1];
                System.out.println(conta);
                break;
            }
            catch(Exception error){
                System.err.println(error.getMessage());
                teclado.next();
            }
        }
    }

    private void caseCriaConta(){
        System.out.println("Insira o nome do titular: ");
        String nome = digitouCorretamente();
        try{
            this.banco.criaConta(nome);
        }
        catch(Exception error) //nunca vai cair aqui pq digitouCorretamente() já faz a validação
        {}
    }
}