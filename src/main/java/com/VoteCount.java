package com;

public class VoteCount {

    int first;
    int second;
    int third;

    public VoteCount(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    void incrementFirst() {
        first++;
    }

    void incrementSecond() {
        second++;
    }

    void incrementThird() {
        third++;
    }
}
