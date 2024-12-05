package me.zetastormy.aoc.challenge;

import me.zetastormy.aoc.AdventOfCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FourthDayChallenge implements Challenge {
    private final Path inputLocation;
    private List<String> input;

    public FourthDayChallenge() {
        this.inputLocation = AdventOfCode.getResource(this);
    }

    @Override
    public void execute() {
        System.out.println("------------------*=| Fourth day of Advent of Code |=*------------------");
        parseInput();
        firstPart();
        secondPart();
    }

    private void parseInput() {
        try {
            input = Files.readAllLines(inputLocation);
        } catch (IOException e) {
            System.out.println("Failed to read input!");
        }
    }

    @Override
    public void firstPart() {
        int result = 0;

        for (int i = 0; i < input.size(); i++) {
            String currentLine = input.get(i);

            for (int j = 0; j < currentLine.length(); j++) {
                char current = currentLine.charAt(j);

                if (current != 'X') continue;

                // Horizontal normal
                if (j + 3 < currentLine.length()) {
                    if (currentLine.startsWith("XMAS", j)) result++;
                }

                // Horizontal reversed
                if (j - 3 >= 0) {
                    if (currentLine.substring(j - 3, j + 1).equals("SAMX")) result++;
                }

                // Vertical normal
                if (i + 3 < input.size()) {
                    StringBuilder wordCandidate = new StringBuilder();

                    for (int k = i; k <= i + 3; k++) wordCandidate.append(input.get(k).toCharArray()[j]);

                    if (wordCandidate.toString().equals("XMAS")) result++;
                }

                // Vertical reversed
                if (i - 3 >= 0) {
                    StringBuilder wordCandidate = new StringBuilder();

                    for (int k = i; k >= i - 3; k--) wordCandidate.append(input.get(k).toCharArray()[j]);

                    if (wordCandidate.toString().equals("XMAS")) result++;
                }

                int k = 0;
                StringBuilder wordCandidate = new StringBuilder();

                // Right diagonal down
                while (i + k < input.size() && j + k < currentLine.length() && k < 4) {
                    wordCandidate.append(input.get(i + k).toCharArray()[j + k]);
                    k++;
                }

                if (wordCandidate.toString().equals("XMAS")) result++;

                // Left diagonal up
                k = 0;
                wordCandidate.delete(0, wordCandidate.length());

                while (i - k >= 0 && j - k >= 0 && k < 4) {
                    wordCandidate.append(input.get(i - k).toCharArray()[j - k]);
                    k++;
                }

                if (wordCandidate.toString().equals("XMAS")) result++;

                // Left diagonal down
                k = 0;
                wordCandidate.delete(0, wordCandidate.length());

                while (i + k < input.size() && j - k >= 0 && k < 4) {
                    wordCandidate.append(input.get(i + k).toCharArray()[j - k]);
                    k++;
                }

                if (wordCandidate.toString().equals("XMAS")) result++;

                // Right diagonal up
                k = 0;
                wordCandidate.delete(0, wordCandidate.length());

                while (i - k >= 0 && j + k < currentLine.length() && k < 4) {
                    wordCandidate.append(input.get(i - k).toCharArray()[j + k]);
                    k++;
                }

                if (wordCandidate.toString().equals("XMAS")) result++;
            }
        }

        System.out.println("- First part result: " + result);
    }

    @Override
    public void secondPart() {
        int result = 0;

        for (int i = 0; i < input.size(); i++) {
            String currentLine = input.get(i);

            for (int j = 0; j < currentLine.length(); j++) {
                char current = currentLine.charAt(j);

                if (current != 'M' && current != 'S') continue;

                if (isCrossMas(i, j, false)) result++;
            }
        }

        System.out.println("- Second part result: " + result);
    }

    private boolean isCrossMas(int i, int j, boolean reverse) {
        String currentLine = input.get(i);
        int k = 0;
        StringBuilder wordCandidate = new StringBuilder();

        if (reverse) {
            // Right diagonal up
            while (i - k >= 0 && j + k < currentLine.length() && k < 3) {
                wordCandidate.append(input.get(i - k).toCharArray()[j + k]);
                k++;
            }

            return wordCandidate.toString().equals("MAS") || wordCandidate.toString().equals("SAM");
        }

        // Right diagonal down
        while (i + k < input.size() && j + k < currentLine.length() && k < 3) {
            wordCandidate.append(input.get(i + k).toCharArray()[j + k]);
            k++;
        }

        if (!wordCandidate.toString().equals("MAS") && !wordCandidate.toString().equals("SAM")) return false;

        return isCrossMas(i + 2, j, true);
    }

    @Override
    public String toString() {
        return "fourthDay";
    }
}
