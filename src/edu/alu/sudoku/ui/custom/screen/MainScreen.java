package edu.alu.sudoku.ui.custom.screen;

import java.awt.Dimension;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import static edu.alu.sudoku.service.EventEnum.CLEAR_SPACE;

import edu.alu.sudoku.model.GameStatusEnum;
import edu.alu.sudoku.model.Space;
import edu.alu.sudoku.service.BoardService;
import edu.alu.sudoku.service.NotifierService;
import edu.alu.sudoku.ui.custom.button.CheckGameStatusButton;
import edu.alu.sudoku.ui.custom.button.FinishGameButton;
import edu.alu.sudoku.ui.custom.button.ResetGameButton;
import edu.alu.sudoku.ui.custom.frame.MainFrame;
import edu.alu.sudoku.ui.custom.input.NumberText;
import edu.alu.sudoku.ui.custom.panel.MainPanel;
import edu.alu.sudoku.ui.custom.panel.SodukoSector;

public class MainScreen {
    private final static Dimension dimension = new Dimension(600, 600);

    private final BoardService boardService;
    private final NotifierService notifierService;

    private JButton finishButton;
    private JButton checkGameStatusButton;
    private JButton resetButton;

    public MainScreen(final Map<String, String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
        this.notifierService = new NotifierService();
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);
        for (int r = 0; r < 9; r += 3) {
            int endRow = r + 2;
            for (int c = 0; c < 9; c += 3) {
                int endCol = c + 2;
                List<Space> spaces = getSpacesFromSector(boardService.getSpaces(), c, endCol, r, endRow);
                JPanel sector = genereateSection(spaces);
                mainPanel.add(sector);
            }
        }
        addResetButton(mainPanel);
        addShowGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private List<Space> getSpacesFromSector(final List<List<Space>> spaces,
            final int initCol, final int endCol,
            final int initRow, final int endRow) {
        List<Space> spaceSector = new ArrayList<>();
        for (int r = initRow; r <= endRow; r++) {
            for (int c = initCol; c <= endCol; c++) {
                spaceSector.add(spaces.get(c).get(r));
            }
        }
        return spaceSector;
    }

    private JPanel genereateSection(final List<Space> spaces) {
        List<NumberText> fields = new ArrayList<>(spaces.stream().map(NumberText::new).toList());
        fields.forEach(t -> notifierService.subscriber(CLEAR_SPACE, t));
        return new SodukoSector(fields);
    }

    private void addFinishGameButton(JPanel mainPanel) {
        finishButton = new FinishGameButton(e -> {
            if (boardService.gameIsfinished()) {
                JOptionPane.showMessageDialog(null, "Parabens, voce concluiu o jogo");
                resetButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                finishButton.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Seu jogo contem inconsistencias, ajuste novamente");
            }
        });
        mainPanel.add(finishButton);
    }

    private void addResetButton(JPanel mainPanel) {
        resetButton = new ResetGameButton(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(
                    null,
                    "Deseja realmente reinicar o jogo",
                    "Reiniciar", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (dialogResult == 0) {
                boardService.reset();
                notifierService.notify(CLEAR_SPACE);
            }
        });
        mainPanel.add(resetButton);
    }

    private void addShowGameStatusButton(JPanel mainPanel) {
        checkGameStatusButton = new CheckGameStatusButton(e -> {
            boolean hasErrors = boardService.hasErrors();
            GameStatusEnum status = boardService.getStatus();
            String message = switch (status) {
                case NON_STARTED -> "O jogo não foi iniciado";
                case IMCOMPLETED -> "o jogo está incompleto";
                case COMPLETE -> "O jogo está completo";
            };
            message += hasErrors ? " e contem erros" : " e não contém erros";
            JOptionPane.showMessageDialog(null, message);
        });
        mainPanel.add(checkGameStatusButton);
    }
}
