package edu.alu.sudoku.ui.custom.button;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ResetGameButton extends JButton {

    public ResetGameButton(ActionListener actionListener) {
        this.setText("Resetar");
        this.addActionListener(actionListener);
    }
}