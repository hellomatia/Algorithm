// Using Binary Search Algorithm
// 0. find min number and index.
// 1. sorted Arrays.

class Solution {
    public int search(int[] nums, int target) {
        int minIdx = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (min > nums[i]) {
                min = nums[i];
                minIdx = i;
            }
        }
        Arrays.sort(nums);
        
        int idx = Arrays.binarySearch(nums, target);
        if (idx < 0) { return -1; }
        int answer = (minIdx + idx) % nums.length;
        return answer;
    }
}
