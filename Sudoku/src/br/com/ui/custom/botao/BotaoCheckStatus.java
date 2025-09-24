package br.com.ui.custom.botao;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BotaoCheckStatus extends JButton {

    public BotaoCheckStatus(final ActionListener actionListener) {

        this.setText("Check Status");
        this.addActionListener(actionListener);
    }
}
