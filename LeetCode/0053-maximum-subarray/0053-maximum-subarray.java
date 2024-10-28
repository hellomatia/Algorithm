class Solution {
    public int maxSubArray(int[] nums) {
        int answer = Integer.MIN_VALUE;
        int total = 0;
        
        for (int num : nums) {
            if (total < 0) {
                total = 0;
            }
            total += num;
            answer = Math.max(answer, total);
        }
        
        return answer;
    }
}