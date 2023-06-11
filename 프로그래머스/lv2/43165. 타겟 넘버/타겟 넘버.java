class Solution {
    
    int numOfNumbers;
    int answer;
    int targetNum;
    int[] numArr;
    
    public int solution(int[] numbers, int target) {
        
        numOfNumbers = numbers.length;
        targetNum = target;
        numArr = numbers.clone();
        
        DFS(0, 0, 0);
        
        return answer;
    }
    
    public void DFS(int idx, int num, int count) {
        if(count==numOfNumbers && num==targetNum) {
            answer++;
            return;
        }
        
        for(int i=idx; i<numOfNumbers; i++) {
            DFS(i+1, num+numArr[i], count+1);
            DFS(i+1, num-numArr[i], count+1);
        }
        
        
    }
}