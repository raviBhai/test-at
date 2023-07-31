package com;

public class TimestampCount {

    private long timeStamp;
    private int count;

    public TimestampCount(long timestamp, int count) {
        this.timeStamp = timestamp;
        this.count = count;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
