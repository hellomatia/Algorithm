class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);  // Sort to handle duplicates and allow early stopping
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, result, new ArrayList<>(), 0, 0);
        return result;
    }

    private void backtrack(int[] candidates, int target, List<List<Integer>> result, List<Integer> current, int sum, int start) {
        if (sum == target) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;  // Skip duplicates
            }
            
            if (sum + candidates[i] > target) {
                break;  // No need to continue further, as the array is sorted
            }
            
            current.add(candidates[i]);
            backtrack(candidates, target, result, current, sum + candidates[i], i + 1);
            current.remove(current.size() - 1);  // Backtrack
        }
    }
}
