package br.com.modelo;


import java.util.Collection;
import java.util.List;
import static java.util.Objects.*;

public class Board {
 
   
    private List<List<Espaco>> board;

    public Board(List<List<Espaco>> board) {
        this.board = board;
    }


    
    public List<List<Espaco>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Espaco>> board) {
        this.board = board;
    }

    public EstadoJogo verificarEstadoJogo(){
        if(board.stream().flatMap(Collection::stream)
        .noneMatch(s -> !s.isFixo() && s.getValor() == null)){return EstadoJogo.NAO_INICIADO;}
        return board.stream().flatMap(Collection::stream)
        .anyMatch(s -> isNull(s.getValor())) ? EstadoJogo.EM_ANDAMENTO : EstadoJogo.COMPLETO;
    }
    
    public boolean temErro(){

        
        return board.stream().flatMap(Collection::stream)
        .anyMatch(s -> !isNull(s.getValor()) && !s.getValor().equals(s.getEsperado()));
    }

    public boolean alteralValor(final int coluna,final int linha,final int valor){
        
        Espaco espaco = board.get(coluna).get(linha);
        if(espaco.isFixo()){
            return false;
        }
        espaco.setValor(valor);
        return true;

     
    }

    public  boolean limparvalores(final int coluna,final int linha){
        Espaco espaco = board.get(coluna).get(linha);
        if(espaco.isFixo()){
            return false;
        }

        espaco.limparEspaco();
        return true;
    }

    public void reset(){
        board.forEach(s -> s.forEach(Espaco::limparEspaco));
    }
  
    public boolean estaCompleto(){
        return verificarEstadoJogo() == EstadoJogo.COMPLETO && !temErro();
    }
}  

