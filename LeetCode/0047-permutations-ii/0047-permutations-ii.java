class Solution {
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums); // Sort the array to handle duplicates
        List<List<Integer>> result = new ArrayList<>();
        explore(nums, new ArrayList<>(), new boolean[nums.length], result);
        return result;
    }
    
    private void explore(int[] nums, List<Integer> current, boolean[] visited, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current)); // Add a copy of the current permutation
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            // Skip duplicates
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
            current.add(nums[i]);
            explore(nums, current, visited, result);
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }
}
