package me.zetastormy.aoc.challenge;

import me.zetastormy.aoc.AdventOfCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SecondDayChallenge implements Challenge {
    private final Path inputLocation;
    private List<String> input;

    public SecondDayChallenge() {
        this.inputLocation = AdventOfCode.getResource(this);
    }

    @Override
    public void execute() {
        System.out.println("------------------*=| Second day of Advent of Code |=*------------------");
        parseInput();
        firstPart();
        secondPart();
    }

    private void parseInput() {
        try {
            input = Files.readAllLines(inputLocation);
        } catch (IOException e) {
            System.out.println("Something went wrong file reading the file!");
        }
    }

    @Override
    public void firstPart() {
        int safeReports = 0;

        for (String report : input) {
            List<String> reportLevels = Arrays.asList(report.split(" "));

            if (checkIfSafe(reportLevels)) safeReports++;
        }

        System.out.println("- First part result: " + safeReports);
    }

    @Override
    public void secondPart() {
        int safeReports = 0;

        for (String report : input) {
            List<String> reportLevels = Arrays.asList(report.split(" "));

            if (checkIfSafe(reportLevels)) {
                safeReports++;
                continue;
            }

            for (int i = 0; i < reportLevels.size(); i++) {
                List<String> reportLevelsMutable = new ArrayList<>(reportLevels);
                reportLevelsMutable.remove(i);

                if (checkIfSafe(reportLevelsMutable)) {
                    safeReports++;
                    break;
                }
            }
        }

        System.out.println("- Second part result: " + safeReports);
    }

    private boolean checkIfSafe(List<String> reportLevels) {
        boolean increasing = true;

        for (int i = 1; i < reportLevels.size(); i++) {
            int prev = Integer.parseInt(reportLevels.get(i - 1));
            int curr = Integer.parseInt(reportLevels.get(i));
            int levelDiff = prev - curr;

            if (Math.abs(levelDiff) > 3 || Math.abs(levelDiff) < 1) {
                return false;
            }

            if (i == 1 && levelDiff > 0) increasing = false;

            if (increasing) {
                if (i != 1 && levelDiff > 0) {
                    return false;
                }
            } else {
                if (i != 1 && levelDiff < 0) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "secondDay";
    }
}
