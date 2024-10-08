class Solution {
    public int divide(int dividend, int divisor) {
        long answer = (long) dividend / (long) divisor;
        
        
        if (answer >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        
        if (answer <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        
        return (int) answer;
    }
}