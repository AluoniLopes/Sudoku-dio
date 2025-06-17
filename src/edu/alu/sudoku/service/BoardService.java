package edu.alu.sudoku.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.alu.sudoku.model.Board;
import edu.alu.sudoku.model.GameStatusEnum;
import edu.alu.sudoku.model.Space;

public class BoardService {

    private final static int BOARD_LIMIT = 9;

    private final Board board;

    public BoardService(final Map<String, String> gameConfig) {
        this.board = new Board(InitBoard(gameConfig));
    }

    public List<List<Space>> getSpaces() {
        return this.board.getSpaces();
    }

    public void reset() {
        this.board.reset();
    }

    public boolean hasErrors() {
        return this.board.hasErrors();
    }

    public GameStatusEnum getStatus() {
        return this.board.getStatus();
    }

    public boolean gameIsfinished() {
        return this.board.gameIsfinished();
    }

    private List<List<Space>> InitBoard(Map<String, String> gameConfig) {
        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                String positionConfig = gameConfig.get("%s,%s".formatted(i, j));
                int expected = Integer.parseInt(positionConfig.split(",")[0]);
                boolean fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                Space currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }
        return spaces;
    }

}
