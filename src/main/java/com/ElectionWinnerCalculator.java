package com;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectionWinnerCalculator {

    Map<String, VoteCount> voteBank = new HashMap<>();

    String calculateWinner(List<Vote> votes) {
        for (Vote vote : votes) {
            if (vote.first != null && !voteBank.containsKey(vote.first)) {
                VoteCount voteCount = new VoteCount(0, 0, 0);
                voteBank.put(vote.first, voteCount);
            }

            if (vote.second != null && !voteBank.containsKey(vote.second)) {
                VoteCount voteCount = new VoteCount(0, 0, 0);
                voteBank.put(vote.second, voteCount);
            }

            if (vote.third != null && !voteBank.containsKey(vote.third)) {
                VoteCount voteCount = new VoteCount(0, 0, 0);
                voteBank.put(vote.third, voteCount);
            }

            if (vote.first != null) voteBank.get(vote.first).incrementFirst();
            if (vote.second != null) voteBank.get(vote.second).incrementSecond();
            if (vote.third != null) voteBank.get(vote.third).incrementThird();
        }

        int totalVotes = votes.size();
        for (String key : voteBank.keySet()) {
            if (voteBank.get(key).first > totalVotes / 2) {
                return key;
            }
        }

        return null;
    }
}
