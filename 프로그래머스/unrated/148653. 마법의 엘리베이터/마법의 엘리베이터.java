class Solution {
    
    public int solution(int storey) {
        
        int answer = 0;
        //각 자리 수를 비교하기 위해 String으로 변환한 다음 배열로 저장
        String strStorey = String.valueOf(storey);
        int digit = strStorey.length();
        
        int[] num = new int[digit];
        
        // 제일 작은 자리수부터 비교하기 때문에 거꾸로 저장
        for(int i=0; i<digit; i++) {
            num[i] = strStorey.charAt(digit-1-i)-'0';
        }
        
        for(int i=0; i<digit-1; i++) {
            if(num[i]==10) {
                num[i+1]++;
                continue;
            }
            
            if(num[i]>5) {
                num[i+1]++;
                answer += 10-num[i];
            } else if(num[i]<5) {
                answer += num[i];
            } else {
                answer += 5;
                if(num[i+1]>4){
                    num[i+1]++;
                }
            }
        }
        
        if(num[digit-1]==10) {
            answer++;
        } else if(num[digit-1]<=5) {
            answer += num[digit-1];
        } else {
            answer++;
            answer += 10-num[digit-1];
        }
        
        
        return answer;
    }
}