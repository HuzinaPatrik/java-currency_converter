package com.currencyconverter.utils;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class ThreeCharTextField extends JTextField {
    public ThreeCharTextField() {
        super();
        this.setDocument(new ThreeCharDocument());
    }

    private static class ThreeCharDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }

            if ((getLength() + str.length()) > 3) {
                return;
            }

            for (int i = 0; i < str.length(); i++) {
                if (!Character.isLetter(str.charAt(i))) {
                    return;
                }
            }

            super.insertString(offs, str, a);
        }
    }
}