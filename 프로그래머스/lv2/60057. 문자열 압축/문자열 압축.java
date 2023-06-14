import java.util.*;

class UnitString{
    String str;
    int count;
    UnitString(String str, int count) {
        this.str = str;
        this.count = count;        
    }
}

class Solution {
    int answer;
    
    public int solution(String s) {
        answer = s.length();
        
        //cutString(s, 2);
        
        
        for(int i=1; i<=s.length(); i++) {
            cutString(s, i);
        }
        
        
        return answer;
    }
    
    public void cutString(String s, int count) {
        
        int sLength = count;
        StringBuilder tempSb = new StringBuilder();
        UnitString us;
        
        for(int i=0; i<count; i++) {
            tempSb.append(s.charAt(i));
        }
        
        us = new UnitString(tempSb.toString(), 0);
        
        for(int i=count; i<s.length(); i+=count) {
            
            //자른 문자열과 같은지 비교
            if(i+count>s.length()) continue;
            
            for(int j=0; j<count; j++) {
                
                //다를 경우
                if(us.str.charAt(j)!=s.charAt(i+j)) {
                    sLength += count;
                    
                    //새로 문자열을 자름
                    tempSb = new StringBuilder();
                    for(int k=0; k<count; k++) {
                        tempSb.append(s.charAt(i+k));
                    }                        
                    
                    us = new UnitString(tempSb.toString(), 0);
                    
                    break;
                }
                
                //같을 경우
                if(j==count-1) {
                    
                    if(us.count==0) {
                        sLength += 1;
                        us.count++;
                    } else if(us.count+1==10) {
                        sLength += 1;
                    } else if(us.count+1==100) {
                        sLength += 1;
                    } else if(us.count+1==1000) {
                        sLength += 1;
                    }
                    
                    us.count++;
                }
            }
        }
        
        sLength += (s.length()%count);
        
        answer = Math.min(sLength, answer);
    }
}