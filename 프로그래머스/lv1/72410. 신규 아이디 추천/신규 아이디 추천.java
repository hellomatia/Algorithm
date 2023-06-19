import java.util.*;

class Solution {
    public String solution(String new_id) {
                
        // 1단계
        new_id = new_id.toLowerCase();
        
        // 2단계
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<new_id.length(); i++) {
            char ch = new_id.charAt(i);
            if('a'<=ch&&ch<='z') {
                sb.append(ch);
            } else if('0'<=ch&&ch<='9') {
                sb.append(ch);
            } else if(ch=='-'||ch=='_'||ch=='.') {
                sb.append(ch);
            }
        }
        
        //3단계
        boolean beforeDot = false;
        
        for(int i=0; i<sb.length(); i++) {
            char ch = sb.charAt(i);
            if(ch=='.'&&beforeDot) {
                sb.delete(i,i+1);
                i--;
                System.out.println(i);
            } else if (ch=='.'&&!beforeDot) {
                beforeDot = true;
            } else {
                beforeDot = false;
            }
        }
        
        System.out.println(sb.toString());
        
        //4단계
        if(sb.length()!=0&&sb.charAt(0)=='.') {
            sb.delete(0,1);
        }
        if(sb.length()!=0&&sb.charAt(sb.length()-1)=='.') {
            sb.delete(sb.length()-1,sb.length());
        }
        
        System.out.println(sb.toString());
        
        //5단계
        if(sb.length()==0) {
            sb.append("a");
        }
        
        //6단계
        if(16<=sb.length()) {
            sb.delete(15, sb.length());
        }
        if(sb.charAt(sb.length()-1)=='.') {
            sb.delete(sb.length()-1, sb.length());
        }
        
        //7단계
        if(sb.length()<=2) {
            char ch = sb.charAt(sb.length()-1);
            while(sb.length()!=3) {
                sb.append(ch);
            }
        }
        
        
    
        String answer = sb.toString();
        return answer;
    }
}