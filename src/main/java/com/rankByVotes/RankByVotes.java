package com.rankByVotes;

import java.util.*;

public class RankByVotes {

    Map<String, List<Integer>> voteBank = new HashMap<>();
    int numOfTeams = 0;

    RankByVotes(int numOfTeams) {
        this.numOfTeams = numOfTeams;
    }

    public String rankTeams(List<Vote> votes) {
        for (Vote vote : votes) {

            for (int i = 0; i < vote.preferences.size(); i++) {
                String votedTeam = vote.preferences.get(i);
                if (!voteBank.containsKey(votedTeam)) {
                    List<Integer> votesByRank = initializeVoteList(numOfTeams);
                    voteBank.put(votedTeam, votesByRank);
                }
                List<Integer> votesByRank = voteBank.get(votedTeam);
                votesByRank.set(i, votesByRank.get(i) + 1);
            }
        }

        List<String> result = new ArrayList<>();
        while (!voteBank.isEmpty()) {
            for (int i = 0; i < numOfTeams; i++) {
                List<String> leadersAtPosition = getLeadersAtPosition(voteBank, i);
                List<String> tempLeaders;
                if (leadersAtPosition.size() == 1) {
                    result.add(leadersAtPosition.get(0));
                    voteBank.remove(leadersAtPosition.get(0));
                    if (voteBank.size() == 0) {
                        break;
                    }
                    i = -1;
                } else {
                    int position = i;
                    while (true) {
                        tempLeaders = leadersAtPosition;
                        leadersAtPosition = getLeadersAtPositionFromLeaders(voteBank, position + 1, leadersAtPosition);
                        if (leadersAtPosition == null) {
                            Collections.sort(tempLeaders);
                            result.addAll(tempLeaders);
                            for (String key : tempLeaders) {
                                voteBank.remove(key);
                            }
                            i = -1;
                            break;
                        } else if (leadersAtPosition.size() > 1) {
                            position++;
                        } else if (leadersAtPosition.size() == 1) {
                            result.add(leadersAtPosition.get(0));
                            voteBank.remove(leadersAtPosition.get(0));
                            i = -1;
                            break;
                        }
                    }


                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (String key : result) {
            sb.append(key);
        }
        return sb.toString();
    }

    private List<String> getLeadersAtPosition(Map<String, List<Integer>> voteBank, int position) {
        int max = Integer.MIN_VALUE;
        List<String> leaders = new ArrayList<>();
        for (String key : voteBank.keySet()) {
            if (max < voteBank.get(key).get(position)) {
                max = voteBank.get(key).get(position);
                leaders = new ArrayList<>();
                leaders.add(key);
            } else if (max == voteBank.get(key).get(position)) {
                leaders.add(key);
            }
        }
        return leaders;
    }

    private List<String> getLeadersAtPositionFromLeaders(Map<String, List<Integer>> voteBank, int position,
                                                         List<String> existingLeaders) {

        if (position > voteBank.size()) {
            return null;
        }

        int max = Integer.MIN_VALUE;
        List<String> leaders = new ArrayList<>();
        for (String key : existingLeaders) {
            if (max < voteBank.get(key).get(position)) {
                max = voteBank.get(key).get(position);
                leaders = new ArrayList<>();
                leaders.add(key);
            } else if (max == voteBank.get(key).get(position)) {
                leaders.add(key);
            }
        }
        return leaders;
    }

    private List<Integer> initializeVoteList(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(0);
        }
        return list;
    }
}
