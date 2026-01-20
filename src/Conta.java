public class Conta implements Cloneable{
    private int numeroConta;
    private String nomeTitular;
    private int saldo;
    private static int qtdContas = 0;

    public Conta(String nomeTitular) throws Exception{
        if (nomeTitular.isBlank() || nomeTitular.equals(null)) throw new Exception("Nome vazio");
        qtdContas++;

        this.nomeTitular = nomeTitular;
        this.numeroConta = qtdContas;
        this.saldo = 0;
    }

    public void depositar(int valor) throws Exception{
        if (valor <= 0) throw new Exception("Valor invalido, insira um valor acima de 0");

        this.saldo += valor;
    }

    public void sacar(int valor) throws Exception{
        if (valor <= 0) throw new Exception("Valor invalido, insira um valor acima de 0");
        if (valor > saldo) throw new Exception("Saldo o insuficiente");

        this.saldo -= valor;
    }

    @Override
    public String toString(){
        return "Conta de: " + nomeTitular + "\nNumero da conta: " + numeroConta + "\n Saldo: " + saldo;
    }

    @Override
    public int hashCode(){
        int ret = 1;

        ret = ret * 3 + ((Integer)numeroConta).hashCode();
        ret = ret * 3 + nomeTitular.hashCode();
        ret = ret * 3 + ((Integer)saldo).hashCode();

        if (ret < 0) ret = -ret;
        return ret;
    }

    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        Conta valida = (Conta)obj;
        if (!this.nomeTitular.equals(valida.nomeTitular)) return false;
        if (this.saldo != valida.saldo) return false;
        if (this.numeroConta != valida.numeroConta) return false;
        return true;
    }

    public Conta(Conta modelo) throws Exception{
        if (modelo == null) throw new Exception("Modelo vazio");

        this.nomeTitular = modelo.nomeTitular;
        this.numeroConta = modelo.numeroConta;
        this.saldo = modelo.saldo;
    }

    public Object clone(){
        Object ret = null;
        try{
            ret = new Conta(this);
        }
        catch (Exception error)
        {}

        return ret;
    }
}