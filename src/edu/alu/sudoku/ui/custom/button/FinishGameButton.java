package edu.alu.sudoku.ui.custom.button;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FinishGameButton extends JButton {
    public FinishGameButton(final ActionListener actionListener) {
        this.setText("Concluir");
        this.addActionListener(actionListener);
    }
}
