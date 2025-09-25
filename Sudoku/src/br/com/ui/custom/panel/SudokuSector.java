package br.com.ui.custom.panel;

import br.com.ui.custom.input.TextoNumero;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

import static java.awt.Color.BLACK;

public class SudokuSector extends JPanel {


    public SudokuSector(final List<TextoNumero> textFields) {
        var dimension = new Dimension(170,170);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBorder(new LineBorder(BLACK, 2, true));
        this.setVisible(true);
        textFields.forEach(this::add);


        
    }

}
