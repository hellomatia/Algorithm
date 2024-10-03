// Letter Combinations of a Phone Number
// 1.Give a string containing digits from 2-9
// return all possible letter combinations that the number could represent


// Mapping Integer to Strings
// 2: "a", "b", "c"
// 3: "d", "e", "f"
// 4: "g", "h", "i"
// 5: "j", "k", "l"
// 6: "m", "n", "o"
// 7: "p", "q", "r", "s"
// 8: "t", "u", "v"
// 9: "w", "x", "y", "z"

import java.util.*;

class Solution {
    Map<Integer, List<String>> phoneNumberMap;
    List<String> answer;
    
    public List<String> letterCombinations(String digits) {
        phoneNumberMap = new HashMap<>();
        
        phoneNumberMap.put(2, List.of("a", "b", "c"));
        phoneNumberMap.put(3, List.of("d", "e", "f"));
        phoneNumberMap.put(4, List.of("g", "h", "i"));
        phoneNumberMap.put(5, List.of("j", "k", "l"));
        phoneNumberMap.put(6, List.of("m", "n", "o"));
        phoneNumberMap.put(7, List.of("p", "q", "r", "s"));
        phoneNumberMap.put(8, List.of("t", "u", "v"));
        phoneNumberMap.put(9, List.of("w", "x", "y", "z"));

        answer = new ArrayList<>();
        if (digits.equals("")) {
            return answer;
        }
        dfs(0, digits, "");

        return answer;
    }

    public void dfs(int count, String digits, String word) {
        if (count == digits.length()) {
            answer.add(word);
            return;
        }

        for (String s : phoneNumberMap.get(digits.charAt(count) - '0')) {
            dfs(count + 1, digits, word + s);
        }
    }
}
