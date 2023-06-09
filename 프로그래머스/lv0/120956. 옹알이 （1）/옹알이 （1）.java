class Solution {
    public int solution(String[] babbling) {
        
        int count = 0;
        
        for(int i=0; i<babbling.length; i++) {
            String str = babbling[i];
            str = str.replaceAll("aya","*");
            str = str.replaceAll("ye","*");
            str = str.replaceAll("woo","*");
            str = str.replaceAll("ma","*");
            for(int j=0; j<str.length(); j++) {
                if(str.charAt(j)!='*') break;
                if(j==str.length()-1) count++;
            }
        }
        
        return count;
    }
}