class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        explore(nums, new boolean[nums.length], result, new ArrayList<>());
        return result;
    }
    
    private void explore(int[] nums, boolean[] visited, List<List<Integer>> result, List<Integer> now) {
        if (now.size() == nums.length) {
            List<Integer> answer = new ArrayList<>();
            answer.addAll(now);
            result.add(answer);
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == true) {
                continue;
            }
            visited[i] = true;
            now.add(nums[i]);
            explore(nums, visited, result, now);
            visited[i] = false;
            now.remove((Integer) nums[i]);
        }
    }
}