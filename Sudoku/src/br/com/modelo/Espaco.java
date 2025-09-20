package br.com.modelo;

public class Espaco {

    private Integer valor;
    private final int esperado;
    private final boolean fixo;
    
    public Espaco(int esperado, boolean fixo) {
        this.esperado = esperado;
        this.fixo = fixo;
        if (fixo){
            this.valor = esperado;
        }
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        if (fixo){
           return;
        }
          
        this.valor = valor;

    }

    public int getEsperado() {
        return esperado;
    }

    public boolean isFixo() {
        return fixo;
    }

    public void limparEspaco() {
        if (fixo){
           return;
        }
        this.valor = null;
    }


}
