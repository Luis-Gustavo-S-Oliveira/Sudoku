package br.com.modelo;

public class Espaco {

    private Integer valor;
    private final int esperado;
    private final boolean fixo;
    
    public Espaco(int exeperado, boolean fixo) {
        this.esperado = exeperado;
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
