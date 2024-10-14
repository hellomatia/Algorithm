// Count and Say

// Recurcive method

import java.util.*;

class Solution {

    public static void main(String[] args) {
        new Solution().countAndSay(4);
    }

    public String countAndSay(int n) {
        return recuciveCountAndSay(n, "1");
    }

    private String recuciveCountAndSay(int count, String number) {
        if (count == 1) {
            return number;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < number.length();) {
            int sameNumberCount = sameNumberCount(i, number);
            result.append(sameNumberCount);
            result.append(number.charAt(i));
            i += sameNumberCount;
        }

        return recuciveCountAndSay(count - 1, result.toString());
    }

    private int sameNumberCount(int start, String number) {
        char[] numberChars = number.toCharArray();
        int count = 1;
        char target = numberChars[start];
        for (int i = start + 1; i < number.length(); i++) {
            if (target != numberChars[i]) {
                break;
            }
            count++;
        }
        return count;
    }
}
