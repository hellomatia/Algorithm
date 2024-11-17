class Solution {
    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int max = 0;
        
        while (start < end) {
            int now = Math.min(height[start], height[end]) * (end - start);
            max = Math.max(max, now);
            
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        
        return max;        
    }
}