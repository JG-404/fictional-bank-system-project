public enum OpcaoAcoes{
    SACAR(1), 
    DEPOSITAR(2), 
    ENCONTRAR_CONTA(3), 
    CRIAR_CONTA(4),
    SAIR(0);

    private final int acao;

    private OpcaoAcoes(int opcao){
        this.acao = opcao;
    }

    public int getAcao(){
        return acao;
    }
}