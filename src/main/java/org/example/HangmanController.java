package org.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class HangmanController extends Application {
    private HangmanModel model;
    private HangmanView view;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        model = new HangmanModel();
        view = new HangmanView(primaryStage);

        updateView();

        view.setGuessButtonAction(this::handleGuess);
       view.setResetButtonAction(this::resetGame);
        view.drawHangman(model.getAttemptsLeft());
    }

    private void updateView() {
        view.setWordDisplay(model.getWordDisplay());
        view.setAttemptsLeft(model.getAttemptsLeft());
        view.setIncorrectGuesses(model.getIncorrectGuesses());
    }

    private void resetGame() {
        model.reset();
        updateView();
        view.drawHangman(model.getAttemptsLeft());
    }

    private void handleGuess() {
        String input = view.getInput();
        if (input.length() == 1) {
            char guessedLetter = input.charAt(0);
            model.makeGuess(guessedLetter);
            view.clearInput();
            view.setWordDisplay(model.getWordDisplay());
            view.setAttemptsLeft(model.getAttemptsLeft());
            view.setIncorrectGuesses(model.getIncorrectGuesses());
            view.drawHangman(model.getAttemptsLeft());
            checkGameStatus();
        }
    }

    private void checkGameStatus() {
        if (model.isGameOver()) {
            String message = model.getAttemptsLeft() <= 0 ? "¡Perdiste! La palabra era: " + model.getSelectedWord() : "¡Ganaste! La palabra era: " + model.getSelectedWord();
            view.showGameOver(message);
        }
    }
}