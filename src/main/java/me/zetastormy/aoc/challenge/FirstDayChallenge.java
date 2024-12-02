package me.zetastormy.aoc.challenge;

import me.zetastormy.aoc.AdventOfCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FirstDayChallenge implements Challenge {
    private final Path inputLocation;
    private List<Integer> firstListIds;
    private List<Integer> secondListIds;

    public FirstDayChallenge() {
        this.inputLocation = AdventOfCode.getResource(this);
    }

    @Override
    public void execute() {
        System.out.println("------------------*=| First day of Advent of Code |=*------------------");
        parseInput();
        firstPart();
        secondPart();
    }

    private void parseInput() {
        try {
            List<String> input = Files.readAllLines(inputLocation);
            firstListIds = new ArrayList<>();
            secondListIds = new ArrayList<>();

            for (String numberPair : input) {
                int firstId = Integer.parseInt(numberPair.split(" {3}")[0]);
                int secondId = Integer.parseInt(numberPair.split(" {3}")[1]);

                firstListIds.add(firstId);
                secondListIds.add(secondId);
            }

            firstListIds.sort(Comparator.comparingInt(a -> a));
            secondListIds.sort(Comparator.comparingInt(a -> a));
        } catch (IOException e) {
            System.out.println("Something went wrong file reading the file!");
        }
    }

    @Override
    public void firstPart() {
        int result = 0;

        for (int i = 0; i < firstListIds.size(); i++) {
            result += Math.abs(firstListIds.get(i)- secondListIds.get(i));
        }

        System.out.println("- First part result: " + result);
    }

    @Override
    public void secondPart() {
        int result = 0;

        for (int firstId : firstListIds) {
            result += (int) (firstId * secondListIds.stream().filter(secondId -> secondId.equals(firstId)).count());
        }

        System.out.println("- Second part result: " + result);
    }

    @Override
    public String toString() {
        return "firstDay";
    }
}
