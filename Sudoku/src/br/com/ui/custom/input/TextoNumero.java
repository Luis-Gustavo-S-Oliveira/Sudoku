package br.com.ui.custom.input;

import br.com.modelo.Espaco;
import br.com.servico.EventoEnum;
import br.com.servico.EventoListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class TextoNumero extends JTextField implements EventoListener {

    private final Espaco espaco;

    public TextoNumero(final Espaco espaco) {
        this.espaco = espaco;
        var dimension = new Dimension(50,50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setDocument(new TextoNumeroLimite());
        this.setEnabled(!espaco.isFixo());

        if (this.espaco.isFixo()) {
            this.setText(espaco.getValor().toString());
        }
        this.getDocument().addDocumentListener(new DocumentListener() {

            private void changeEspaco() {
                if (getText().isEmpty()) {
                    espaco.limparEspaco();
                    return;
                }
                espaco.setValor(Integer.parseInt(getText()));
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changeEspaco();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeEspaco();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeEspaco();
            }
        });


    }

    @Override
    public void update(EventoEnum tipoEvento) {
        if(tipoEvento.equals(EventoEnum.LIMPAR_ESPACO)&& this.isEnabled()) {
            this.setText("");
        }
    }
}
