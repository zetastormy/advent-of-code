package me.zetastormy.aoc;

import me.zetastormy.aoc.challenge.*;
import team.unnamed.inject.Binder;
import team.unnamed.inject.Module;

public class ChallengeModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.multibind(Challenge.class).asSet()
                .to(FirstDayChallenge.class)
                .to(SecondDayChallenge.class)
                .to(ThirdDayChallenge.class)
                .to(FourthDayChallenge.class);
    }
}
