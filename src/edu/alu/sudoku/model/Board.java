package edu.alu.sudoku.model;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import java.util.Collection;
import java.util.List;

public class Board {
    private final List<List<Space>> spaces;

    public Board(List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public Space getSpace(int x, int y) {
        return spaces.get(x).get(y);
    }

    public GameStatusEnum getStatus() {
        if (spaces.stream().flatMap(Collection::stream)
                .noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))) {
            return GameStatusEnum.NON_STARTED;
        } else
            return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getActual()))
                    ? GameStatusEnum.IMCOMPLETED
                    : GameStatusEnum.COMPLETE;
    }

    public boolean hasErrors() {
        if (getStatus() == GameStatusEnum.NON_STARTED) {
            return false;
        }

        return spaces.stream().flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getActual()) && !s.getActual().equals(s.getExpected()));
    }

    public boolean changeValue(final int col, final int row, final int value) {
        Space space = this.spaces.get(col).get(row);
        if (space.isFixed()) {
            return false;
        } else {
            space.setActual(value);
            return true;
        }
    }

    public boolean clearValue(final int col, final int row) {
        Space space = this.spaces.get(col).get(row);
        if (space.isFixed()) {
            return false;
        } else {
            space.clearSpace();
            return true;
        }
    }

    public void reset() {
        spaces.forEach(s -> s.forEach(Space::clearSpace));
    }

    public boolean gameIsfinished() {
        return !hasErrors() && getStatus().equals(GameStatusEnum.COMPLETE);
    }

}