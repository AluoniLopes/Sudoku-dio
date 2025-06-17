package edu.alu.sudoku;

import static edu.alu.sudoku.util.BoardTemplate.BOARD_TEMPLATE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import edu.alu.sudoku.model.Board;
import edu.alu.sudoku.model.Space;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    private static Board board;

    private final static int BOARD_LIMIT = 9;

    public static void main(String[] args) {
        final var positions = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]));
        var option = -1;
        while (true) {
            System.out.println("Selecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = scanner.nextInt();

            switch (option) {
                case 1 -> startGame(positions);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida, selecione uma das opções do menu");
            }
        }
    }

    private static void startGame(Map<String, String> positions) {
        if (nonNull(board)) {
            System.out.println("o jogo ja foi iniciado");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                String positionConfig = positions.get("%s,%s".formatted(i, j));
                int expected = Integer.parseInt(positionConfig.split(",")[0]);
                boolean fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                Space currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }
        board = new Board(spaces);
        System.out.println("O Jogo está pronto para começar");
    }

    private static void inputNumber() {
        if (isNull(board)) {
            System.out.println("O jogo ainda nao foi iniciado");
            return;
        }

        System.out.println("Informe a coluna que o numero será inserido");
        int row = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha que o numero será inserido");
        int col = runUntilGetValidNumber(0, 8);

        System.out.printf("Informe o numero que irá entrear na posiçao [%s,%s]", col, row);
        int value = runUntilGetValidNumber(1, 9);

        if (!board.changeValue(col, row, value)) {
            System.out.printf("A posicao [%s,%s] tem um valor fixo\n", col, row);
        }
    }

    private static void removeNumber() {
        System.out.println("Informe a coluna que o numero será removido");
        int row = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha que o numero será removido");
        int col = runUntilGetValidNumber(0, 8);

        if (!board.clearValue(col, row)) {
            System.out.printf("A posicao [%s,%s] tem um valor fixo\n", col, row);
        }
    }

    private static void finishGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda nao foi iniciado");
            return;
        }
        System.out.println("are sure you wanna finish the game?[sim/nao]");
        String option = scanner.next().toLowerCase();
        while (option == "sim" || option == "nao") {
            if (board.gameIsfinished()) {
                System.out.println("Parabens, jogo ganho");
                showCurrentGame();
                board = null;

            } else if (board.hasErrors()) {
                System.out.println("Seu jogo contem erros, verifique e tente novamente");
            } else {
                System.out.println("voce ainda precisa preencher os espaços vazios");
                showCurrentGame();
            }
        }
    }

    private static void clearGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("are sure you wanna finish the game?[sim/nao]");
        String option = scanner.next().toLowerCase();
        if (option.equals("sim")) {
            board.reset();
            System.out.println("o jogo foi limpado com sucesso!");
        }

    }

    private static void showGameStatus() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado iniciado");
            return;
        }
        System.out.println("Status de jogo: " + board.getStatus().getLabel());
        if (board.hasErrors()) {
            System.out.println("O jogo contem erros");
        } else {
            System.out.println("O jogo não contem erros");
        }
    }

    private static void showCurrentGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        var args = new Object[BOARD_LIMIT * BOARD_LIMIT];
        var argPos = 0;

        for (int row = 0; row < BOARD_LIMIT; row++) {
            for (int col = 0; col < BOARD_LIMIT; col++) {
                Space space = board.getSpace(row, col);
                args[argPos++] = " " + (isNull(space.getActual()) ? " " : space.getActual());
            }
        }

        System.out.println("Seu jogo se encontra da seguinte forma:");
        System.out.printf("\n" + BOARD_TEMPLATE + "\n", args);
    }

    private static int runUntilGetValidNumber(final int min, final int max) {
        int current = scanner.nextInt();
        while (current < min || current > max) {
            System.out.println("informe um numero entre %s e %s\n".formatted(min, max));
            current = scanner.nextInt();
        }
        return current;
    }

}