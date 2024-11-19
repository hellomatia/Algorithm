class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double now = 0;
        for (int i = 0; i < k; i++) {
            now += (double) nums[i] / k;
        }
        
        double max = now;
        for (int i = k; i < nums.length; i++) {
            now -= (double) nums[i - k] / k;
            now += (double) nums[i] / k;
            max = Math.max(now, max);
        }
        
        return max;
    }
}