class Solution {
    public int reverse(int x) {
        String numStr = String.valueOf(x);
        
        StringBuilder sb = new StringBuilder();
        
        if (x < 0) {
            sb.append('-');            
        }
        for (int i = numStr.length() - 1; i >= 0; i--) {
            if (numStr.charAt(i) == '-') {
                continue;
            }
            sb.append(numStr.charAt(i));
        }
        
        String newNum = sb.toString();
        try {
            return Integer.parseInt(newNum);
        } catch (Exception e) {
            return 0;
        }
    }
}