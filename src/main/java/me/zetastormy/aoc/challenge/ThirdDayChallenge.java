package me.zetastormy.aoc.challenge;

import me.zetastormy.aoc.AdventOfCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class ThirdDayChallenge implements Challenge {
    private final Path inputLocation;
    private List<String> input;

    public ThirdDayChallenge() {
        this.inputLocation = AdventOfCode.getResource(this);
    }

    @Override
    public void execute() {
        System.out.println("------------------*=| Third day of Advent of Code |=*------------------");
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

        for (String instruction : input) {
            result += executeMulInstructions(instruction);
        }

        System.out.println("- First part result: " + result);
    }

    @Override
    public void secondPart() {
        int result = 0;
        boolean canExecute = true;

        for (String instruction : input) {
            int executeFromIndex = 0;
            int executeUntilIndex = 0;

            for (char insChar : instruction.toCharArray()) {
                if (insChar != 'd') {
                    executeUntilIndex++;
                    continue;
                }

                String instructionCandidate = instruction.substring(executeUntilIndex, executeUntilIndex + 7);

                if (instructionCandidate.equals("don't()") && canExecute) {
                    result += executeMulInstructions(instruction
                            .substring(executeFromIndex, executeUntilIndex));
                    canExecute = false;
                } else if (instructionCandidate.startsWith("do()") && !canExecute) {
                    executeFromIndex = executeUntilIndex;
                    canExecute = true;
                }

                executeUntilIndex++;
            }

            if (canExecute) {
                result += executeMulInstructions(instruction
                        .substring(executeFromIndex, executeUntilIndex));
            }
        }

        System.out.println("- Second part result: " + result);
    }

    private int executeMulInstructions(String instruction) {
        int result = 0;
        int currentIndex = 0;

        for (char insChar : instruction.toCharArray()) {
            if (insChar != 'm') {
                currentIndex++;
                continue;
            }

            String instructionCandidate = instruction.substring(currentIndex);

            if (instructionCandidate.startsWith("mul(")) {
                int i = 4;
                char currentChar = instructionCandidate.charAt(i);
                StringBuilder firstNumber = new StringBuilder();
                StringBuilder secondNumber = new StringBuilder();
                boolean isSecondNumber = false;

                while ((Character.isDigit(currentChar) || currentChar == ',') && currentChar != ')') {
                    currentChar = instructionCandidate.charAt(i++);
                    if (currentChar == ')') break;

                    if (currentChar == ',') {
                        isSecondNumber = true;
                        continue;
                    }

                    if (!isSecondNumber) {
                        firstNumber.append(currentChar);
                        continue;
                    }

                    secondNumber.append(currentChar);
                }

                if (!firstNumber.isEmpty() && !secondNumber.isEmpty() && currentChar == ')') {
                    result += Integer.parseInt(firstNumber.toString()) * Integer.parseInt(secondNumber.toString());
                }
            }

            currentIndex++;
        }

        return result;
    }

    @Override
    public String toString() {
        return "thirdDay";
    }
}
