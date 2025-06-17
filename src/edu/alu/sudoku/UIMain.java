package edu.alu.sudoku;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.stream.Stream;

import edu.alu.sudoku.ui.custom.screen.MainScreen;

public class UIMain {
    public static void main(String[] args) {
        // System.out.println(args);
        final Map<String, String> gameConfig = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]));
        var mainScreen = new MainScreen(gameConfig);
        mainScreen.buildMainScreen();
    }
}
