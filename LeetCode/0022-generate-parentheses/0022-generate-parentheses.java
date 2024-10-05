class Solution {
    private Set<String>[] dp;
    
    public List<String> generateParenthesis(int n) {
        dp = new Set[9];
        for (int i = 0; i < 9; i++) {
            dp[i] = new HashSet<>();
        }
        
        dp[0].add("");
        for (int i = 1; i < 9; i++) {
            for (String parenthese : dp[i - 1]) {
                dp[i].add("(" + parenthese + ")");
            }
            
            for (int j = i - 1; j >= 1; j--) {
                for (String par1 : dp[i - j]) {
                    for (String par2 : dp[j]) {
                        dp[i].add(par1 + par2);
                        dp[i].add(par2 + par1);
                    }
                }
            }
        }
        
        List<String> result = new ArrayList<>();
        for (String parenthese : dp[n]) {
            result.add(parenthese);
        }
        
        return result;
    }
}
