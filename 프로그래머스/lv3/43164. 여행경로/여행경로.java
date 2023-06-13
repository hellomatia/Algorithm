import java.util.*;

class Solution {
    boolean[] isUsed;
    List<String> answer;

    public String[] solution(String[][] tickets) {
        isUsed = new boolean[tickets.length];
        answer = new ArrayList<>();
        
        // 출발지를 기준으로 항공권 정렬
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1]));

        List<String> temp = new ArrayList<>();
        temp.add("ICN"); // 출발지는 항상 "ICN"
        dfs(tickets, "ICN", temp);
        
        // List<String>를 String[]로 변환
        String[] result = new String[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }
        
        return result;
    }

    private void dfs(String[][] tickets, String cur, List<String> temp) {
        if (temp.size() == tickets.length + 1) {
            // 경로가 answer에 없거나 알파벳 순서가 더 빠른 경우 갱신
            if (answer.isEmpty() || compare(temp, answer) < 0) {
                answer = new ArrayList<>(temp);
            }
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!isUsed[i] && tickets[i][0].equals(cur)) {
                isUsed[i] = true;
                temp.add(tickets[i][1]);
                dfs(tickets, tickets[i][1], temp);
                temp.remove(temp.size() - 1);
                isUsed[i] = false;
            }
        }
    }
    
    private int compare(List<String> list1, List<String> list2) {
        for (int i = 0; i < list1.size(); i++) {
            int cmp = list1.get(i).compareTo(list2.get(i));
            if (cmp != 0) {
                return cmp;
            }
        }
        return 0;
    }
}
