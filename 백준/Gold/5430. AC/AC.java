import java.io.*;
import java.util.*;

public class Main {
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numOfTestCase = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<numOfTestCase; i++) {

            Deque<String> deque = new ArrayDeque<>();

            Queue<Character> fnQueue = new LinkedList<>();

            String fnStr = bf.readLine();

            int countD = 0;

            for(int j=0; j<fnStr.length(); j++) {
                char function = fnStr.charAt(j);
                fnQueue.add(function);
                if(function=='D') countD++;
            }

            int numCount = Integer.parseInt(bf.readLine());

            String numStr = bf.readLine();
            numStr = numStr.replace("[","");
            numStr = numStr.replace("]", "");

            StringTokenizer st = new StringTokenizer(numStr, ",");
            for(int j=0; j<numCount; j++) {
                deque.offer(st.nextToken());
            }

            if(numCount<countD) {
                sb.append("error\n");
            } else {
                boolean reverse = false;
                while(!fnQueue.isEmpty()) {
                    char function = fnQueue.poll();
                    if(function=='R') reverse = !reverse;
                    else{
                        if(reverse)
                            deque.pollLast();
                        else {
                            deque.pollFirst();
                        }
                    }
                }

                sb.append("[");
                if(reverse) {
                    while(!deque.isEmpty()) {
                        sb.append(deque.pollLast());
                        if(!deque.isEmpty()) sb.append(",");
                    }
                } else {
                    while(!deque.isEmpty()) {
                        sb.append(deque.pollFirst());
                        if(!deque.isEmpty()) sb.append(",");
                    }
                }

                sb.append("]\n");
            }
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
