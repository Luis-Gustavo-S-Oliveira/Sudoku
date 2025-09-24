package br.com.servico;

import br.com.modelo.Board;
import br.com.modelo.Espaco;
import br.com.modelo.EstadoJogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardService {
    private final static int TAMANHO_BOARD = 9;

    private final Board board ;

    public BoardService(final Map<String,String> gameconfig) {
        this.board = new Board(initBoard(gameconfig));
    }

    private List<List<Espaco>> initBoard(final Map<String,String> gameconfig) {

        List<List<Espaco>> espacos = new ArrayList<>();

        for (int i = 0; i < TAMANHO_BOARD; i++) {
            espacos.add(new ArrayList<>());
            for (int j = 0; j < TAMANHO_BOARD; j++) {
                String posicaoConfig = gameconfig.get("%s,%s".formatted(i,j));
                int esperado  = Integer.parseInt(posicaoConfig.split(",")[0]);
                boolean fixo = Boolean.parseBoolean(posicaoConfig.split(",")[1]);
                Espaco espacoCerto = new Espaco(esperado, fixo);
                espacos.get(i).add(espacoCerto);
            }
        }

       return espacos;
    }

    public List<List<Espaco>> getEspacos(){
        return this.board.getBoard();
    }

    public void rest(){
        board.reset();

    }

    public boolean temErro(){
        return  board.temErro();
    }

    public EstadoJogo getEstadoJogo(){
        return board.verificarEstadoJogo();
    }

    public boolean gameTerminado(){
        return board.estaCompleto();
    }





}
