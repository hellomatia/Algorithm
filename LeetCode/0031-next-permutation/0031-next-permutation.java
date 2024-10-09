class Solution {

    public void nextPermutation(int[] nums) {
        if (!np(nums)) {
            Arrays.sort(nums);
        }
    }

    private boolean np(int[] nums) {
        int length = nums.length;
        int idxA = length - 1;

        while (idxA > 0 && nums[idxA - 1] >= nums[idxA]) {
            idxA--;
        }

        if (idxA == 0) {
            return false;
        }

        int idxB = length - 1;

        while (nums[idxA - 1] >= nums[idxB]) {
            idxB--;
        }

        swap(nums, idxA - 1, idxB);

        int idx = length - 1;
        while (idxA < idx) {
            swap(nums, idxA++, idx--);
        }

        return true;
    }

    private void swap(int[] nums, int idxA, int idxB) {
        int temp = nums[idxA];
        nums[idxA] = nums[idxB];
        nums[idxB] = temp;
    }
}
