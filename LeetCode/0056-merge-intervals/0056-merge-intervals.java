class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });
        
        
        List<int[]> temp = new ArrayList<>();
        temp.add(intervals[0]);
        
        for (int[] interval : intervals) {
            int[] now = temp.get(temp.size() - 1);
            if (now[1] >= interval[0]) {
                now[1] = Math.max(now[1], interval[1]);
            } else {
                temp.add(interval);
            }
        }
        
        int[][] answer = new int[temp.size()][2];
        for (int i = 0; i < temp.size(); i++) {
            answer[i] = temp.get(i);
        }
        return answer;
    }
}