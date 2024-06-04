import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long start = 1;
        long end = (long) n * times[times.length - 1];
        long answer = Long.MAX_VALUE;
        while (start <= end) {
            long now = (start + end) >>> 1;
            long num = 0;
            for (int time : times) {
                num += (now / time);
            }
            if (num < n) {
                start = now + 1;
            } else {
                answer = Math.min(now, answer);
                end = now - 1;
            }
        }
        
        return answer;
    }
}