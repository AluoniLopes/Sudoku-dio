package edu.alu.sudoku.model;

public enum GameStatusEnum {

    NON_STARTED("não iniciado"),
    IMCOMPLETED("incompleto"),
    COMPLETE("completo");

    public String getLabel() {
        return label;
    }

    private String label;

    GameStatusEnum(final String label) {
        this.label = label;
    }

}
