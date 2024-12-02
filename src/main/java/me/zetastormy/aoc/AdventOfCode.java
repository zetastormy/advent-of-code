package me.zetastormy.aoc;

import me.zetastormy.aoc.challenge.Challenge;
import team.unnamed.inject.Inject;
import team.unnamed.inject.Injector;

import java.nio.file.Path;
import java.util.*;

public class AdventOfCode {
    @Inject private final Set<Challenge> challenges = new HashSet<>();

    public void run() {
        challenges.forEach(Challenge::execute);
    }

    public static void main(String[] args) {
        Injector injector = Injector.create(new ChallengeModule());
        AdventOfCode aoc = injector.getInstance(AdventOfCode.class);
        aoc.run();
    }

    public static Path getResource(Challenge challenge) {
        return Path.of(Objects.requireNonNull(AdventOfCode.class
                .getClassLoader()
                .getResource("data/" + challenge.toString() + ".txt")).getPath());
    }
}
