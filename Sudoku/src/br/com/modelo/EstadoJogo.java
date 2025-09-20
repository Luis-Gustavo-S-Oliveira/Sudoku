
package br.com.modelo;

public enum  EstadoJogo {

    NAO_INICIADO("nao iniciado"),
    COMPLETO("comleto"),    
    EM_ANDAMENTO("em andamento");

    
    private String label;

    EstadoJogo(final String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
