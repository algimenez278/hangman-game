package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangmanModel {
    private String[] words = {"java", "programacion", "ahorcado", "desarrollo", "software"};
    private String selectedWord;
    private List<Character> guessedLetters = new ArrayList<>();
    private List<Character> incorrectGuesses = new ArrayList<>();
    private int attemptsLeft = 6;

    public HangmanModel() {
        selectedWord = selectRandomWord();
    }

    private String selectRandomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    public String getWordDisplay() {
        StringBuilder display = new StringBuilder();
        for (char letter : selectedWord.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                display.append(letter).append(" ");
            } else {
                display.append("_ ");
            }
        }
        return display.toString().trim();
    }

    public void makeGuess(char guessedLetter) {
        if (!guessedLetters.contains(guessedLetter)) {
            guessedLetters.add(guessedLetter);
            if (!selectedWord.contains(String.valueOf(guessedLetter))) {
                attemptsLeft--;
                incorrectGuesses.add(guessedLetter);
            }
        }
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public List<Character> getIncorrectGuesses() {
        return incorrectGuesses;
    }

    public boolean isGameOver() {
        return attemptsLeft <= 0 || getWordDisplay().replace(" ", "").equals(selectedWord);
    }

    public String getSelectedWord() {
        return selectedWord;
    }
}