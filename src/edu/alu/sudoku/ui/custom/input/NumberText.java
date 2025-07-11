package edu.alu.sudoku.ui.custom.input;

import java.awt.Dimension;
import java.awt.Font;

import static edu.alu.sudoku.service.EventEnum.CLEAR_SPACE;
import static java.awt.Font.PLAIN;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.alu.sudoku.model.Space;
import edu.alu.sudoku.service.EventEnum;
import edu.alu.sudoku.service.EventListener;

public class NumberText extends JTextField implements EventListener {

    private Space space;

    public NumberText(final Space space) {
        this.space = space;
        Dimension dimension = new Dimension(50, 50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!space.isFixed());
        if (space.isFixed()) {
            this.setText(space.getActual().toString());
        }
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeSpace();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeSpace();

            }

            private void changeSpace() {
                if (getText().isEmpty()) {
                    space.clearSpace();
                    return;
                }
                space.setActual(Integer.parseInt(getText()));
            }

        });
    }

    @Override
    public void update(EventEnum eventType) {
        if (eventType.equals(CLEAR_SPACE) && (this.isEnabled())) {
            this.setText("");
        }
    }

}
