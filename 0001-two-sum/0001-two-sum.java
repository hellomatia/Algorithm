import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            nodes.add(new Node(i, nums[i]));
        }

        Collections.sort(nodes, (o1, o2) -> o1.getNum() - o2.getNum());

        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int sum = nodes.get(start).getNum() + nodes.get(end).getNum();

            if (sum == target) {
                return new int[]{nodes.get(start).getIdx(), nodes.get(end).getIdx()};
            }
            if (sum < target) {
                start++;
            } else {
                end--;
            }
        }
        
        return new int[]{nodes.get(start).getIdx(), nodes.get(end).getIdx()};
    }

    static class Node {
        private final int idx;
        private final int num;

        public Node(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }

        public int getIdx() {
            return idx;
        }

        public int getNum() {
            return num;
        }
    }
}
