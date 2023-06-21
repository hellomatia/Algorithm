import java.util.*;

class Subject {
    String name;
    int startTime;
    int remainingTime;
    Subject(String name, int startTime, int remainingTime) {
        this.name = name;
        this.startTime = startTime;
        this.remainingTime = remainingTime;
    }
}

class Solution {
    PriorityQueue<Subject> subjects;
    Stack<Subject> discontinuedSubject;
    static String[] answer;
    
    public String[] solution(String[][] plans) {
        
        subjects = new PriorityQueue<>((o1, o2) -> {
            return o1.startTime - o2.startTime;
        });
        
        discontinuedSubject = new Stack<>();
                                                             
        for(int i=0; i<plans.length; i++) {
            String name = plans[i][0];
            int hour = Integer.parseInt(plans[i][1].substring(0,2));
            int min = Integer.parseInt(plans[i][1].substring(3));
            int remainingTime = Integer.parseInt(plans[i][2]);
            //System.out.println(remainingTime);
            subjects.offer(new Subject(name, hour*60+min, remainingTime));
        }
        
        
        
        
        answer = new String[plans.length];
        
        work();
        
        return answer;
    }
    
    public void work() {
        int idx = 0;
        int currentTime = subjects.peek().startTime;
    
        while (!subjects.isEmpty() || !discontinuedSubject.isEmpty()) {
            // Check if a new subject starts now
            while (!subjects.isEmpty() && subjects.peek().startTime <= currentTime) {
                Subject now = subjects.poll();
                if (!discontinuedSubject.isEmpty()) {
                    discontinuedSubject.peek().remainingTime -= (now.startTime - currentTime);
                }
                discontinuedSubject.push(now);
                currentTime = now.startTime;
            }

            // Resume the latest discontinued subject if there is any
            while (!discontinuedSubject.isEmpty() && (subjects.isEmpty() || currentTime + discontinuedSubject.peek().remainingTime <= subjects.peek().startTime)) {
                Subject now = discontinuedSubject.pop();
                currentTime += now.remainingTime;
                answer[idx++] = now.name;
            }

            // If there is still some time left until the next subject starts, resume the latest discontinued subject
            if (!discontinuedSubject.isEmpty() && (subjects.isEmpty() || currentTime < subjects.peek().startTime)) {
                discontinuedSubject.peek().remainingTime -= (subjects.peek().startTime - currentTime);
                currentTime = subjects.peek().startTime;
            }

            // If there is no discontinued subject and the current time is earlier than the start time of the next subject, fast forward the current time
            if (discontinuedSubject.isEmpty() && !subjects.isEmpty() && currentTime < subjects.peek().startTime) {
                currentTime = subjects.peek().startTime;
            }
        }
    }

    
}