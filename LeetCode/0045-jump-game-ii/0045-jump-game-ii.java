import java.util.*;

class Solution {
    
    private int minCount = Integer.MAX_VALUE;
    
    public int jump(int[] nums) {
        // explore1(0, 0, nums);
        // explore2(nums);
        int jumps = 0;
        int curEnd = 0;
        int curFarthest = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + nums[i]);
            
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }
    
    private void explore1(int count, int now, int[] nums) {
        if (now >= nums.length - 1) {
            minCount = Math.min(count, minCount);
            return;
        }
        int maxJump = nums[now];
        for (int i = 1; i <= maxJump; i++) {
            explore1(count + 1, now + i, nums);
        }
    }

    private void explore2(int[] nums) {
        int[] visited = new int[nums.length];
        Arrays.fill(visited, Integer.MAX_VALUE);
        
        PriorityQueue<Point> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.count == o2.count) {
                return o2.idx - o1.idx;
            }
            return o1.count - o2.count;
        });

        queue.add(new Point(0, 0));
        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (visited[now.idx] <= now.count) {
                continue;
            }

            if (now.idx == nums.length - 1) {
                minCount = Math.min(now.count, minCount);
                break;
            }
            
            if (nums[now.idx] == 0) { continue; }
            
            for (int i = 1; i <= nums[now.idx]; i++) {
                int next = now.idx + i;
                if (next > nums.length) { break; }
                queue.add(new Point(next, now.count + 1));
            }
        }
    }
    
    static class Point {
        int idx;
        int count;
        
        Point(int idx, int count) {
            this.idx = idx;
            this.count = count;
        }
    }
}
