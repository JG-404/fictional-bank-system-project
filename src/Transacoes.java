public class Transacoes {
    private String transacao;
    private int valor;

    public Transacoes(String trasacao, int valor){
        this.transacao = trasacao;
        this.valor = valor;
    }

    @Override
    public String toString(){
        return "Tipo: " + this.transacao + "\n Valor: " + this.valor;
    }

    @Override
    public int hashCode(){
        int ret = 1;
        
        ret = ret * 3 + this.transacao.hashCode();
        ret = ret * 3 + ((Integer)this.valor).hashCode();

        if (ret < 0) ret = -ret;
        return ret;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj == this) return true;
        if (this.getClass() != obj.getClass()) return false;
        Transacoes novo = (Transacoes)obj;
        if (!this.transacao.equals(novo.transacao)) return false;
        if (this.valor != novo.valor) return false;
        return true;
    }
}