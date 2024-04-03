package com.sds;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class BowlingGameTest {

    private BowlingGame subject = new BowlingGame();


    @Test
    void whenHitNothingPin_thenReturnZeroScore() {
        hitSamePinsSequentially(0, 20);
        Assertions.assertEquals(0, subject.score());
    }

    @Test
    void whenHitOnlyOnePinInGame_thenReturnScoreOne() {
        subject.roll(1);
        hitSamePinsSequentially(0, 19);
        Assertions.assertEquals(1, subject.score());
    }


    @ValueSource(ints = {-1, 11})
    @ParameterizedTest
    void whenHitCountIsInvalid_thenThrowError(int pin) {
        //given

        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            subject.roll(pin);
        });
    }

    @Test
    void whenFirstSpareThenSecondWillGetBonus() {
        //given
        int firstOneInFirstTurn = 7;
        int spareWithSecondOneInFirstTurn = 3;
        int secondOneWithFirstTurn = 7;

        //when
        subject.roll(firstOneInFirstTurn);
        subject.roll(spareWithSecondOneInFirstTurn);
        subject.roll(secondOneWithFirstTurn);

        hitSamePinsSequentially(0, 10);

        //then
        assertEquals(24, subject.score());
    }

    @Test
    void whenFirstStrikeThenSecondWillGetBonus() {
        //given
        int firstOneInFirstTurn = 10;
        int firstOneWithSecondTurn = 1;
        int secondOneWithSecondTurn = 1;

        //when
        subject.roll(firstOneInFirstTurn);
        subject.roll(firstOneWithSecondTurn );
        subject.roll(secondOneWithSecondTurn);

        hitSamePinsSequentially(0, 10);

        //then
        assertEquals(14, subject.score());
    }

    @Test
    void whenInvalidPinsAdded_thenThrowError() {
        //given
        int firstOneInFirstTurn = 4;
        int firstOneWithSecondTurn = 7;

        //when
        subject.roll(firstOneInFirstTurn);

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            subject.roll(firstOneWithSecondTurn );
        });
    }

    @Test
    void whenPerfectGame_thenReturnRightAnswer() {

        //when
        hitSamePinsSequentially(10, 11);

        //then
        assertEquals(300, subject.score());
    }

//    @Test
//    void doGame_thenReturnRightAnswer() {
//
//        //when
//        List.of(10,9,1,7,0,9,1,10,10,8,2,10,9,1,9,1,7).forEach((element) -> {
//            subject.roll(element);
//
//            System.out.println(subject.score());
//        });
//
//        //the
//        assertEquals(188, subject.score());
//    }

    private void hitSamePinsSequentially(int score, int count) {
        for(int i=0; i<count; i++) {
            subject.roll(score);
        }
    }
}