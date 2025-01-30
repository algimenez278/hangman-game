package org.example;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

import static javafx.geometry.Pos.BOTTOM_CENTER;
import static javafx.geometry.Pos.CENTER;

public class HangmanView {
    private Label wordLabel;
    private Label attemptsLabel;
    private Label incorrectGuessesLabel;
    private TextField inputField;
    private Button guessButton;
    private Canvas canvas;

    public HangmanView(Stage primaryStage) {
        primaryStage.setTitle("Juego del Ahorcado");

        wordLabel = new Label();
        attemptsLabel = new Label();
        incorrectGuessesLabel = new Label();
        inputField = new TextField();
        inputField.setPromptText("Introduce una letra");
        guessButton = new Button("Adivinar");
        canvas = new Canvas(200, 200);

        HBox canvasAndBoard = new HBox(10, canvas);
        VBox info= new VBox(10, createBoard(), wordLabel, attemptsLabel, incorrectGuessesLabel, inputField, guessButton);
        HBox layout = new HBox(10, canvasAndBoard, info);
        Scene scene = new Scene(layout, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setWordDisplay(String display) {
        wordLabel.setText(display);
    }

    public void setAttemptsLeft(int attempts) {
        attemptsLabel.setText("Intentos restantes: " + attempts);
    }

    public void setIncorrectGuesses(List<Character> incorrectGuesses) {
        incorrectGuessesLabel.setText("Letras incorrectas: \n" + getIncorrectGuessesDisplay(incorrectGuesses));
    }

    private String getIncorrectGuessesDisplay(List<Character> incorrectGuesses) {
        StringBuilder display = new StringBuilder();
        for (char letter : incorrectGuesses) {
            display.append(letter).append(" ");
        }
        return display.toString().trim();
    }

    public void drawHangman(int attemptsLeft) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Poste
        gc.strokeLine(50, 150, 50, 20);
        gc.strokeLine(50, 20, 100, 20);
        gc.strokeLine(100, 20, 100, 50);

        // Muñeco
        switch (attemptsLeft) {
            case 5: gc.strokeOval(90, 50, 20, 20); break;
            case 4: gc.strokeOval(90, 50, 20, 20); gc.strokeLine(100, 70, 100, 120); break;
            case 3: gc.strokeOval(90, 50, 20, 20); gc.strokeLine(100, 70, 100, 120); gc.strokeLine(100, 80, 80, 100); break;
            case 2: gc.strokeOval(90, 50, 20, 20); gc.strokeLine(100, 70, 100, 120); gc.strokeLine(100, 80, 80, 100); gc.strokeLine(100, 80, 120, 100); break;
            case 1: gc.strokeOval(90, 50, 20, 20); gc.strokeLine(100, 70, 100, 120); gc.strokeLine(100, 80, 80, 100); gc.strokeLine(100, 80, 120, 100); gc.strokeLine(100, 120, 80, 150); break;
            case 0: gc.strokeOval(90, 50, 20, 20); gc.strokeLine(100, 70, 100, 120); gc.strokeLine(100, 80, 80, 100); gc.strokeLine(100, 80, 120, 100); gc.strokeLine(100, 120, 80, 150); gc.strokeLine(100, 120, 120, 150); break;
        }
    }

    public String getInput() {
        return inputField.getText();
    }

    public void clearInput() {
        inputField.clear();
    }

    public void setGuessButtonAction(Runnable action) {
        guessButton.setOnAction(e -> action.run());
    }

    public void showGameOver(String message) {
        wordLabel.setText(message);
        guessButton.setDisable(true);
    }

    private VBox createBoard() {
        VBox board = new VBox(10, incorrectGuessesLabel);
        return board;
    }
}