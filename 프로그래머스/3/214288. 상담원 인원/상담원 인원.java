import java.util.*;

class Solution {
    private List<Meeting>[] reqList;
    private int[][] requiredTime;
    private int K, N;

    public int solution(int k, int n, int[][] reqs) {
        reqList = new List[k];
        for (int i = 0; i < k; i++) reqList[i] = new ArrayList<>();
        for (int i = 0; i < reqs.length; i++)
            reqList[reqs[i][2] - 1].add(new Meeting(reqs[i][0], reqs[i][1]));
        requiredTime = new int[k][n - k + 2];
        K = k; N = n;
        for (int i = 0; i < k; i++)
            for (int j = 1; j < n - k + 2; j++)
                requiredTime[i][j] = calTime(i, j);
        return calTotalMinTime();
    }

    public int calTotalMinTime() {
        int remain = N - K;
        int[] mentorCnt = new int[K];
        Arrays.fill(mentorCnt, 1);
        while(remain-- > 0) {
            int maxDiff = 0;
            int maxIndex = 0;
            for (int i = 0; i < K; i++) {
                if (mentorCnt[i] == N - K + 1) continue;
                int diff = requiredTime[i][mentorCnt[i]] - requiredTime[i][mentorCnt[i] + 1];
                if (maxDiff < diff) {
                    maxDiff = diff;
                    maxIndex = i;
                }
            }
            mentorCnt[maxIndex]++;
        }
        int sum = 0;
        for (int i = 0; i < K; i++)
            sum += requiredTime[i][mentorCnt[i]];
        return sum;
    }

    public int calTime(int k, int cnt) {
        int result = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < cnt; i++) q.add(0);
        for (Meeting cust : reqList[k]) {
            Integer cur = q.poll();
            if (cur <= cust.getStart())
                q.add(cust.getStart() + cust.getEnd());
            else {
                result += cur - cust.getStart();
                q.add(cur + cust.getEnd());
            }
        }
        return result;
    }
    
    static class Meeting {
        private final int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public int getStart() {
            return start;
        }
        
        public int getEnd() {
            return end;
        }
    }
}