package com.rankByVotes;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RankByVotesTest {

    @Test
    public void t1() {
        String A = "A";
        String B = "B";
        String C = "C";
        RankByVotes toBeTested = new RankByVotes(5);
        List<Vote> votes = new ArrayList<Vote>() {{
            add(initialize(A, B, C));
            add(initialize(A, C, B));
            add(initialize(A, B, C));
            add(initialize(A, C, B));
            add(initialize(A, C, B));
        }};

        String actual = toBeTested.rankTeams(votes);
        String expected = "ACB";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void t2() {
        String W = "W";
        String X = "X";
        String Y = "Y";
        String Z = "Z";
        RankByVotes toBeTested = new RankByVotes(4);
        List<Vote> votes = new ArrayList<Vote>() {{
            add(initialize(W, X, Y, Z));
            add(initialize(X, Y, Z, W));
        }};

        String actual = toBeTested.rankTeams(votes);
        String expected = "XWYZ";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void t3() {
        String W = "W";
        String X = "X";
        String Y = "Y";
        String Z = "Z";
        RankByVotes toBeTested = new RankByVotes(4);
        List<Vote> votes = new ArrayList<Vote>() {{
            add(initialize(W, X, Y, Z));
        }};

        String actual = toBeTested.rankTeams(votes);
        String expected = "WXYZ";
        Assert.assertEquals(expected, actual);
    }

    private Vote initialize(String... args) {
        List<String> pref = new ArrayList<>();
        for (String s : args) {
            pref.add(s);
        }
        return new Vote(pref);
    }
}
