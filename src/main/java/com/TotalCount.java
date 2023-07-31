package com;

import java.util.Deque;

public class TotalCount {

    private int totalCount;
    private Deque<TimestampCount> deque;

    public TotalCount(int totalCount, Deque<TimestampCount> deque) {
        this.totalCount = totalCount;
        this.deque = deque;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Deque<TimestampCount> getDeque() {
        return deque;
    }

    public void setDeque(Deque<TimestampCount> deque) {
        this.deque = deque;
    }
}
