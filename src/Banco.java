import java.util.*;

public class Banco implements Cloneable{
    private List<Conta> conta = new ArrayList<>(); //array para guardar as contas criadas

    public Banco() //construtor vazio pq array já é inicializado por padrão
    {}

    //função para criar contas novas e adicionar elas ao array
    public void criaConta(String nomeTitular) throws Exception{
        if (nomeTitular == null || nomeTitular.isBlank()) throw new Exception("Nome vazio");

        Conta novaConta = new Conta(nomeTitular);
        this.conta.add(novaConta);
    }

    //essa função procura uma conta e retorna se ela foi encontrada e retorna ela mesma
    public Object[] encontraConta(int numeroConta) throws Exception{
        if (numeroConta <= 0) throw new Exception("Numero invalido");

        Conta contaEncontrada = null;

        for (int i = 0; i < this.conta.size(); i++){
            if (this.conta.get(i).getNumeroConta() == numeroConta){
                try{
                    contaEncontrada = new Conta(this.conta.get(i));
                }
                catch(Exception error) //conta nunca vai ser null
                {}
                Object[] ret = {true, contaEncontrada};
                return ret;
            }
        }

        Object[] ret = {false};
        return ret;
    }

    //daqui pra baixo eu reescrevi os metodos da classe Object
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

    //Construtor de copia
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