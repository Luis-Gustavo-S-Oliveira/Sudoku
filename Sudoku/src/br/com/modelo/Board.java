package br.com.modelo;


import java.util.Collection;
import java.util.List;

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
        return EstadoJogo.EM_ANDAMENTO;
    }
    
    
}  

