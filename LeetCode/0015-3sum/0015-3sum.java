import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        Set<Points> pointSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            List<Points> points = twoPoints(i, nums);
            for (Points p : points) {
                pointSet.add(p);
            }
        }
        
        for (Points p : pointSet) {
            result.add(p.toList());
        }
        return result;
    }
    
    private List<Points> twoPoints(int target, int[] nums) {
        List<Points> twoPointList = new ArrayList<>();
        
        int targetNum = -nums[target];

        int start = target + 1;
        int end = nums.length - 1;

        while (start < end) {
            int now = nums[start] + nums[end];

            if (now == targetNum) {
                twoPointList.add(new Points(-targetNum, nums[start], nums[end]));
                start++;
                continue;
            }

            if (now < targetNum) {
                start++;
            } else {
                end--;
            }
        }
        return twoPointList;
    }
    
    
    static class Points {
        int i;
        int j;
        int k;
        
        public Points(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }

        public List<Integer> toList() {
            return List.of(i, j, k);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Points p) {
                if (p.i == this.i && p.j == this.j && p.k == this.k) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            String hashString = i + "" + j + "" + k;
            return hashString.hashCode();
        }
    }
}