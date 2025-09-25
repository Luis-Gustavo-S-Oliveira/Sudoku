package br.com.ui.custom.botao;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BotaoRest extends JButton {


    public BotaoRest(ActionListener actionListener) {
        this.setText("Restart Game");
        this.addActionListener(actionListener);
    }
}
