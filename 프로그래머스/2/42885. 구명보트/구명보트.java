import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int start = 0;
        int end = people.length - 1;
        
        int count = people.length;
        int answer = 0;
        while (0 < count) {
            if (start != end && people[start] + people[end] <= limit) {
                answer++;
                start++;
                end--;
                count -= 2;
                continue;
            }
            count -= 1;
            end--;
            answer++;
        }
        
        return answer;
    }
}
