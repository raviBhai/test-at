package com.graded;

import com.graded.Vote;

import java.util.ArrayList;
import java.util.List;

public class VoteCount {

    int totalVotes;

    List<List<Vote>> votesObtained;

    public VoteCount(int numOfCandidates) {
        votesObtained = new ArrayList<>();
        for (int i = 0; i < numOfCandidates; i++) {
            List<Vote> votes = new ArrayList<>();
            votesObtained.add(votes);
        }
    }

    void incrementTotalVotes() {
        totalVotes++;
    }

}
