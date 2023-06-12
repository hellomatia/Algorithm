import java.util.*;

class Word {
    String word;
    int count;
    Word(String word, int count) {
        this.word = word;
        this.count = count;
    }
}

class Solution {
    
    boolean[] isVisited;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        Queue<Word> queue = new LinkedList<>();
        
        queue.add(new Word(begin, 0));
        isVisited = new boolean[words.length];
        
        while(!queue.isEmpty()) {
            Word now = queue.poll();
            
            if(target.equals(now.word)) {
                answer = now.count;
                break;
            }
            
            for(int i=0; i<words.length; i++) {
                if(!isVisited[i]&&check(now.word, words[i])){
                    queue.add(new Word(words[i], now.count+1));
                    isVisited[i] = true;
                }
            }
        }
        
        return answer;
    }
    
    public boolean check(String str1, String str2) {
        int count=0;
        for(int i=0; i<str1.length(); i++) {
            if(str1.charAt(i)!=str2.charAt(i)) {
                count++;
            }
        }
        
        if(count==1) return true;
        else return false;
    }
    
    
    
    
    
}