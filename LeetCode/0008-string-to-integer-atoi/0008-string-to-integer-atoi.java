class Solution {
    public int myAtoi(String s) {
        
        StringBuilder sb = new StringBuilder();
        
        boolean flag = false;
        boolean negative = false;
        for (int i = 0; i < s.length(); i++) {
            if (sb.length() == 0 && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
                sb.append(s.charAt(i));
                flag = true;
                if (s.charAt(i) == '-') {
                    negative = true;
                }
            } else {
                if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                    sb.append(s.charAt(i));
                } else if (sb.length() == 0 && s.charAt(i) == ' ') {
                    continue;
                } else {
                    break;
                }
            }
        }
        
        if (sb.length() == 0) {
            return 0;
        } else if (sb.length() == 1 && flag) {
            return 0;
        }
        
        try {
            return Integer.parseInt(sb.toString());
        } catch (Exception e) {
            if (flag && negative) {
                return Integer.MIN_VALUE;
            }
            return Integer.MAX_VALUE;
        }
    }
}
