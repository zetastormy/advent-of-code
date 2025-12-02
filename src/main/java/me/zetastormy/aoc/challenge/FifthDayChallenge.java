package me.zetastormy.aoc.challenge;

import me.zetastormy.aoc.AdventOfCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FifthDayChallenge implements Challenge {
    private final Path inputLocation;
    private List<String> input;

    public FifthDayChallenge() {
        this.inputLocation = AdventOfCode.getResource(this);
    }

    @Override
    public void execute() {
        System.out.println("------------------*=| Fifth day of Advent of Code |=*------------------");
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
        for (int i = 1; i < input.size(); i++) {
            String previousPageNumber = input.get(i - 1);
            String currentPageNumber = input.get(i);


        }
    }

    @Override
    public void secondPart() {

    }

    @Override
    public String toString() {
        return "fifthDay";
    }
}
