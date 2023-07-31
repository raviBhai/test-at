package com.graded;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ElectionWinnerGradedTest {

    @Test
    public void when_majorityWithFirstPref_then_winnerWithFirstPref() {

        String A = "A";
        String B = "B";
        String C = "C";

        List<Vote> votes = new ArrayList<Vote>() {{
            add(instantiate(A, B, C));
            add(instantiate(A, B, C));
            add(instantiate(A, B, C));
            add(instantiate(A, B, C));
            add(instantiate(A, B, C));
            add(instantiate(A, B, C));
            add(instantiate(B, A, C));
            add(instantiate(B, A, C));
            add(instantiate(B, A, C));
            add(instantiate(B, A, C));
        }};

        ElectionWinnerGraded toBeTested = new ElectionWinnerGraded(3);
        String actual = toBeTested.calculateWinner(votes);
        String expected = A;
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void when_majorityAfterSecondPref_then_winnerAfterRebalancing() {

        String A = "A";
        String B = "B";
        String C = "C";

        List<Vote> votes = new ArrayList<Vote>() {{
            add(instantiate(A, B, C));
            add(instantiate(A, B, C));
            add(instantiate(A, B, C));
            add(instantiate(A, B, C));
            add(instantiate(B, A, C));
            add(instantiate(B, A, C));
            add(instantiate(B, C, A));
            add(instantiate(B, C, A));
            add(instantiate(C, B, A));
            add(instantiate(C, B, A));
        }};

        ElectionWinnerGraded toBeTested = new ElectionWinnerGraded(3);
        String actual = toBeTested.calculateWinner(votes);
        String expected = B;
        Assert.assertEquals(expected, actual);

    }

    private Vote instantiate(String... candidates) {
        List<String> preferences = new ArrayList<>();
        for (String candidate : candidates) {
            preferences.add(candidate);
        }
        Vote vote = new Vote(preferences);
        return vote;
    }
}
