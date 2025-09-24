package br.com.ui.custom.botao;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BotaoFinalizarGame extends JButton{

    public BotaoFinalizarGame(final ActionListener  actionListener ){
        this.setText("Finalizar Game");
        this.addActionListener(actionListener);
    }


}
