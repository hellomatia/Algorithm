import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 0. Sort the array
        Arrays.sort(nums);

        List<List<Integer>> answer = new ArrayList<>();
        Set<List<Integer>> results = new HashSet<>();

        // 1. Two loops to pick 2 numbers
        for (int i = 0; i < nums.length - 3; i++) {
            // Skip duplicates for the first number
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            for (int j = i + 1; j < nums.length - 2; j++) {
                // Skip duplicates for the second number
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // Convert the first two numbers to long to avoid overflow
                long pickNumSum = (long) nums[i] + nums[j];
                long twoPointerTarget = target - pickNumSum;

                // 2. Get two numbers using the two-pointer technique
                var twoPointerResult = calcTwoPointerNums(twoPointerTarget, j + 1, nums);
                for (var twoPointerNum : twoPointerResult) {
                    List<Integer> result = List.of(nums[i], nums[j], twoPointerNum.start, twoPointerNum.end);
                    results.add(result);
                }
            }
        }

        // 4. Convert Set to List
        for (var result : results) {
            answer.add(result);
        }

        return answer;
    }

    // 2. Two-pointer method to find the remaining two numbers
    public List<TwoPointerNum> calcTwoPointerNums(long target, int start, int[] nums) {
        List<TwoPointerNum> twoPointerNums = new ArrayList<>();
        int end = nums.length - 1;

        while (start < end) {
            long sum = (long) nums[start] + nums[end]; // Use long for sum to avoid overflow

            if (sum == target) {
                twoPointerNums.add(new TwoPointerNum(nums[start], nums[end]));

                // Skip duplicates for the third and fourth numbers
                while (start < end && nums[start] == nums[start + 1]) start++;
                while (start < end && nums[end] == nums[end - 1]) end--;

                start++;
                end--;
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }

        return twoPointerNums;
    }

    static class TwoPointerNum {
        int start;
        int end;

        public TwoPointerNum(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
