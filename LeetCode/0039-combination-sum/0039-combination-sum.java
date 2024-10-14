// All array is unique
// I will use DFS algorithm.
// DFS usally implement by recucive method.


import java.util.*;

class Solution {

    private List<List<Integer>> result;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        init();
        dfs(candidates, new ArrayList<Integer>(), 0, target, 0);
        return result;
    }

    private void init() {
        result = new ArrayList<>();
    }

    private void dfs(int[] candidates, List<Integer> now, int sum, int target, int start) {
        if (sum == target) {
            result.add(now);
            return;
        } else if (sum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            List<Integer> next = new ArrayList<>();
            next.addAll(now);
            next.add(candidates[i]);
            dfs(candidates, next, sum + candidates[i], target, i);
        }
    }
}
