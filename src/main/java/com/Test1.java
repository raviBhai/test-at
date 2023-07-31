package com;


import java.time.Instant;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Test1 {

    private static final Map<Integer, TotalCount> map = new HashMap<>();
    private static final Integer LIMIT = 3;
    private static final long TIME_RANGE = 1;

    public boolean shouldRateLimit(int customerId) {
        long currentTime = currentTime();
        if (!map.containsKey(customerId)) {
            Deque<TimestampCount> deque = new LinkedList();
            deque.addLast(new TimestampCount(currentTime, 1));
            TotalCount totalCount = new TotalCount(1, deque);
            map.put(customerId, totalCount);
            return true;
        } else {
            TotalCount totalCount = map.get(customerId);
            Deque<TimestampCount> deque = totalCount.getDeque();

            long lastWindow = currentTime - TIME_RANGE;
            while (!deque.isEmpty()) {
                if (deque.peekFirst().getTimeStamp() <= lastWindow) {
                    TimestampCount rateCount = deque.removeFirst();
                    totalCount.setTotalCount(totalCount.getTotalCount() - rateCount.getCount());
                } else {
                    break;
                }
            }
            if (totalCount.getTotalCount() >= LIMIT) {
                return false;
            } else {
                TimestampCount latestTimeStampCount = deque.peekLast();
                if (latestTimeStampCount != null && latestTimeStampCount.getTimeStamp() == currentTime) {
                    latestTimeStampCount.setCount(latestTimeStampCount.getCount() + 1);
                } else {
                    TimestampCount newCount = new TimestampCount(currentTime, 1);
                    deque.addLast(newCount);
                }
                totalCount.setTotalCount(totalCount.getTotalCount() + 1);
                return true;
            }
        }
    }

    private long currentTime() {
        return Instant.now().getEpochSecond();
    }
}
