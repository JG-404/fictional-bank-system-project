import java.util.*;

public class Banco implements Cloneable{
    private List<Conta> conta = new ArrayList<>();

    public Banco()
    {}

    public void criaConta(String nomeTitular) throws Exception{
        if (nomeTitular.isBlank() || nomeTitular == null) throw new Exception("Nome vazio");

        Conta novaConta = new Conta(nomeTitular);
        this.conta.add(novaConta);
    }

    public Conta encontraConta(int numeroConta) throws Exception{
        if (numeroConta <= 0) throw new Exception("Numero invalido");

        Conta ret = null;

        for (int i = 0; i < this.conta.size(); i++){
            if (this.conta.get(i).getNumeroConta() == numeroConta){
                try{
                    ret = new Conta((Conta)this.conta.get(i));
                }
                catch(Exception error)
                {}
                return ret;
            }
        }

        throw new Exception("Conta inexistente");
    }

    @Override
    public String toString(){
        String todasContas = "";
        
        for (int i = 0; i < this.conta.size(); i++){
            todasContas += this.conta.get(i).toString() + "\n";
        }

        return todasContas;
    }

    @Override
    public int hashCode(){
        int ret = 1;

        for (int i = 0; i < this.conta.size(); i++){
            if (this.conta.get(i) != null){
                ret = ret * 3 + this.conta.get(i).hashCode();
            }
        }

        if (ret < 0) ret = -ret;
        return ret;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj == this) return true;
        if (this.getClass() != obj.getClass()) return false;
        Banco novo = (Banco)obj;
        if (this.conta.size() != novo.conta.size()) return false;
        for (int i = 0; i < this.conta.size(); i++){
            if (!this.conta.get(i).equals(novo.conta.get(i))) return false;
        }
        return true;
    }

    public Banco(Banco modelo) throws Exception{
        if (modelo == null) throw new Exception("Modelo vazio");

        for (Conta copia : this.conta){
            this.conta.add(new Conta(copia));
        }
    }

    @Override
    public Object clone(){
        Banco ret = null;

        try{
            ret = new Banco(this);
        }
        catch(Exception error)
        {}

        return ret;
    }
}