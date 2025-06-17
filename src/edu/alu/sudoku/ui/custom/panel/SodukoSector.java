package edu.alu.sudoku.ui.custom.panel;

import static java.awt.Color.black;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import edu.alu.sudoku.ui.custom.input.NumberText;

public class SodukoSector extends JPanel {

    public SodukoSector(final List<NumberText> textFields) {
        var dimension = new Dimension(170, 170);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBorder(new LineBorder(black, 2, true));
        this.setVisible(true);
        textFields.forEach(this::add);
    }
}
