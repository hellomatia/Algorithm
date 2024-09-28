class Solution {
    public String longestPalindrome(String s) {
        for (int i = s.length(); i > 0; i--) {
            for (int j = 0; j < s.length() - i; j++) {
                if (isPalidromic(s, j, j + i)) {
                    return s.substring(j, j + i + 1);
                }
            }
        }
        return s.substring(0, 1);
    }
    
    public boolean isPalidromic(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
