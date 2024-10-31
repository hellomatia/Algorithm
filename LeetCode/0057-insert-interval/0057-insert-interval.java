class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
        List<int[]> temp = new ArrayList<>();
        
        boolean insertMod = false;
        boolean finish = false;
        int[] insertInterval = new int[2];
        for (int[] interval : intervals) {
            if (insertMod) {
                if (insertInterval[1] >= interval[0]) {
                    insertInterval[1] = Math.max(insertInterval[1], interval[1]);
                } else {
                    temp.add(insertInterval);
                    temp.add(interval);
                    insertMod = false;
                    finish = true;
                }
            } else if (finish) {
                temp.add(interval);
            } else {
                if ((interval[0] <= newInterval[0] && newInterval[0] <= interval[1]) || 
                    (interval[0] <= newInterval[1] && newInterval[1] <= interval[1]) ||
                    (interval[1] <= newInterval[1] && newInterval[0] <= interval[0])) {
                    insertMod = true;
                    insertInterval[0] = Math.min(newInterval[0], interval[0]);
                    insertInterval[1] = Math.max(newInterval[1], interval[1]);
                } else {
                    temp.add(interval);
                }
            }
        }
        
        if (!finish) {
            if (insertInterval[0] == 0 && insertInterval[1] == 0) {
                temp.add(newInterval);
            } else {
                temp.add(insertInterval);
            }
        }
        
        int[][] answer = new int[temp.size()][2];
        for (int i = 0; i < temp.size(); i++) {
            answer[i] = temp.get(i);
        }
        
        Arrays.sort(answer, (o1, o2) -> o1[0] - o2[0]);
        return answer;
    }
}
