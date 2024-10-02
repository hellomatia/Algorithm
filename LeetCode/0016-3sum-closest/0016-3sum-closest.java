// make sum result.
// 1. sum result is int type
// 2. sum result is 3 number sum
// 3. sum result is closet to target


// my solution
// 1. sorting nums array
// 2. pick one number => int pickNum
// 3. use two pointer algorithm -> find closet to (target - pickNum) sum (2 number)
//    int twoPointTargetNum = target - pickNum;


import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 1.
        Arrays.sort(nums);

        // 2.
        int closetNum = 0;
        int minGap = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int twoPointTargetNum = target - nums[i];
            int twoPointSum = minNumber(nums, i, twoPointTargetNum);
            int gap = Math.abs(twoPointTargetNum - twoPointSum);
            
            if (minGap > gap) {
                minGap = gap;
                closetNum = target - (twoPointTargetNum - twoPointSum);
            }
        }
        return closetNum;
    }

    // 3.
    public int minNumber(int[] nums, int pickNumIdx, int twoPointTargetNum) {
        int start = pickNumIdx + 1;
        int end = nums.length - 1;

        int minGap = 123456789;
        int closetNum = 123456789;
        while (start < end) {
            int sumNum = nums[start] + nums[end];
            
            int gap = Math.abs(twoPointTargetNum - sumNum);
            
            if (minGap > gap) {
                minGap = gap;
                closetNum = sumNum;
            }
            
            if (sumNum < twoPointTargetNum) {
                start++; 
            } else {
                end--;
            }
        }
        return closetNum;
    }
}
