package com.graded;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectionWinnerGraded {

    private int numOfCandidates;

    ElectionWinnerGraded(int numOfCandidates) {
        this.numOfCandidates = numOfCandidates;
    }

    Map<String, VoteCount> voteBank = new HashMap<>();
    int totalVotes = 0;

    String calculateWinner(List<Vote> votes) {
        totalVotes = votes.size();
        for (Vote vote : votes) {

            for (int pref = 0; pref < vote.preferences.size(); pref++) {
                String candidate = vote.preferences.get(pref);
                if (!voteBank.containsKey(candidate)) {
                    VoteCount voteCount = new VoteCount(numOfCandidates);
                    voteBank.put(candidate, voteCount);
                }
                VoteCount voteCount = voteBank.get(candidate);
                voteCount.votesObtained.get(pref).add(vote);

                if (pref == 0) {
                    voteCount.incrementTotalVotes();
                }
            }

        }

        String winner = null;
        int adjustPref = 0;
        while (true) {
            winner = getWinner(voteBank);
            if (winner == null) {
                adjustPref = adjustVotes(voteBank, adjustPref);
            } else {
                break;
            }
        }

        return winner;
    }

    private int adjustVotes(Map<String, VoteCount> voteBank, int adjustPref) {
        int min = Integer.MAX_VALUE;
        String loser = null;
        for (String key : voteBank.keySet()) {
            if (voteBank.get(key).totalVotes < min) {
                min = voteBank.get(key).totalVotes;
                loser = key;
            }
        }
        VoteCount voteCount = voteBank.get(loser);
        voteBank.remove(loser);
        List<Vote> lostVotes = voteCount.votesObtained.get(adjustPref);
        for (Vote vote : lostVotes) {
            if (adjustPref + 1 < vote.preferences.size()) {
                String nextPreferredCandidate = vote.preferences.get(adjustPref + 1);
                if (voteBank.containsKey(nextPreferredCandidate)) {
                    voteBank.get(nextPreferredCandidate).incrementTotalVotes();
                    totalVotes++;
                }
            }
        }
        return adjustPref + 1;
    }

    private String getWinner(Map<String, VoteCount> voteBank) {

        if (voteBank.size() == 1) {
            for (String key : voteBank.keySet()) {
                return key;
            }
        }

        int max = Integer.MIN_VALUE;
        String winner = null;
        for (String key : voteBank.keySet()) {
            if (max < voteBank.get(key).totalVotes) {
                max = voteBank.get(key).totalVotes;
                winner = key;
            }
        }
        if (voteBank.get(winner).totalVotes > totalVotes / 2) {
            return winner;
        }
        return null;
    }
}
