package br.com.ui.custom.input;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.StyledDocument;
import java.util.*;

import static java.util.Objects.isNull;

public class TextoNumeroLimite extends PlainDocument{

    private final List<String> numeros = List.of("1", "2", "3","4", "5", "6","7", "8", "9");

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (isNull(str) || (!numeros.contains(str)))  return;


        if (getLength() + str.length() <= 1){
            super.insertString(offs, str, a);
        }
    }


}
