package br.com.ui.custom.screen;


import br.com.modelo.Espaco;
import br.com.servico.BoardService;
import br.com.servico.EventoEnum;
import br.com.servico.NotifierService;
import br.com.ui.custom.botao.BotaoCheckStatus;
import br.com.ui.custom.botao.BotaoFinalizarGame;
import br.com.ui.custom.botao.BotaoRest;
import br.com.ui.custom.frame.MainFrame;
import br.com.ui.custom.input.TextoNumero;
import br.com.ui.custom.panel.MainPanel;
import br.com.ui.custom.panel.SudokuSector;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class MainScreen {

    private final static Dimension dimension = new Dimension(600, 600);

    private final BoardService boardService;

    private final NotifierService notifierService;


    private JButton botaoCheckStatus;
    private JButton botaoFinalizar;
    private JButton botaoReset;


    public  MainScreen(final Map<String, String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
        this.notifierService = new NotifierService();
    }


    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);
        for (int r =0; r < 9; r+=3) {
            var endlinha = r + 2;
            for (int c = 0; c < 9; c+=3) {
                var endColuna = c + 2;
                var espacos = getEspacosFromSector(boardService.getEspacos(),c ,endColuna,r,endlinha );
                JPanel sectorPanel =  generateSection(espacos);
                mainPanel.add(sectorPanel);
            }
        }
        addResetButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addFinishiGameButton(mainPanel);



        mainFrame.revalidate();
        mainFrame.repaint();
    }


    private List<Espaco> getEspacosFromSector(final List<List<Espaco>> espaco,
                                            final int initColuna, final int endcoluna,
                                            final int initLinha, final int endLinha){
        List<Espaco> spaceSector = new ArrayList<>();
        for (int r = initLinha ; r <= endLinha; r++) {
            for (int c = initColuna; c <= endcoluna; c++) {
                spaceSector.add(espaco.get(c).get(r));
            }
        }
        return spaceSector;
    }

    private JPanel generateSection(final List<Espaco> espaco){
        List<TextoNumero> fields = new ArrayList<>(espaco.stream().map(TextoNumero::new).toList());
        fields.forEach(t -> notifierService.subscribe(EventoEnum.LIMPAR_ESPACO,t));
        return new SudokuSector(fields);
    }

    private void addFinishiGameButton(JPanel mainPanel) {
       botaoFinalizar = new BotaoFinalizarGame(e ->{
            if (boardService.gameTerminado()){
                JOptionPane.showMessageDialog(null, "GAME TERMINADO");
                botaoFinalizar.setEnabled(false);
                botaoCheckStatus.setEnabled(false);
                botaoReset.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Seu Jogo Esta Errado");
            }
        });
        mainPanel.add(botaoFinalizar);

    }

    private void addCheckGameStatusButton(JPanel mainPanel) {
        botaoCheckStatus = new BotaoCheckStatus(e ->{
            var temErro = boardService.temErro();
            var gameStatus = boardService.getEstadoJogo();
            var messagem = switch(gameStatus){
                case NAO_INICIADO -> " o jogo nao foi iniciado";
                case EM_ANDAMENTO ->  " o jogo esta em andamento";
                case COMPLETO ->   " o jogo esta finalizado ";
            };
            messagem += temErro ? " e contém erros " : "e não contém erros";
            JOptionPane.showMessageDialog(null, messagem);
        });
        mainPanel.add(botaoCheckStatus);
    }

    private void addResetButton(JPanel mainPanel) {
        botaoReset = new BotaoRest(e -> {
            var dialogResult = JOptionPane.showConfirmDialog(null,
                    "Deseja realmente Resetar o jogo",
                    "limpar o jogo",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (dialogResult == 0) {
                boardService.rest();
                notifierService.notify(EventoEnum.LIMPAR_ESPACO);
            }
        });
        mainPanel.add(botaoReset);
    }


}
