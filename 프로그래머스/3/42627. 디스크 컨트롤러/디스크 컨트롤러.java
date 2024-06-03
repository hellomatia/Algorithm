import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Job> allJobs = new PriorityQueue<>((o1, o2) -> {
            if (o1.getStart() == o2.getStart()) {
                return o1.getTime() - o2.getTime();
            }
            return o1.getStart() - o2.getStart();
        });
        
        for (int[] job : jobs) {
            allJobs.add(new Job(job[0], job[1]));
        }
        
        PriorityQueue<Job> readyQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.getTime() == o2.getTime()) {
                return o1.getStart() - o2.getStart();
            }
            return o1.getTime() - o2.getTime();
        });
        
        int currentTime = 0;
        int startToEnd = 0;
        while (!readyQueue.isEmpty() || !allJobs.isEmpty()) {
            while (!allJobs.isEmpty() && currentTime >= allJobs.peek().getStart()) {
                readyQueue.add(allJobs.poll());
            }
            if (readyQueue.isEmpty()) {
                readyQueue.add(allJobs.poll());
                currentTime = readyQueue.peek().getStart();
            }
            
            Job job = readyQueue.poll();
            currentTime += job.getTime();
            startToEnd += currentTime - job.getStart();
        }
        
        return startToEnd / jobs.length;
    }
    
    private static class Job {
        private final int start;
        private final int time;
        
        public Job(int start, int time) {
            this.start = start;
            this.time = time;
        }
        
        public int getStart() {
            return start;
        }
        
        public int getTime() {
            return time;
        }
    }
}
