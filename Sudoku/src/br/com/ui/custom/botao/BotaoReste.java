package br.com.ui.custom.botao;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BotaoReste extends JButton {


    public BotaoReste(ActionListener actionListener) {
        this.setText("Restart Game");
        this.addActionListener(actionListener);
    }
}
