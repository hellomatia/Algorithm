import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] visited;
    public void solution() throws IOException {


        int numOfComputers = Integer.parseInt(bf.readLine());
        int computerPairCount = Integer.parseInt(bf.readLine());

        ArrayList<Integer>[] computerConnections = new ArrayList[numOfComputers + 1];

        for (int i = 1; i <= numOfComputers; i++) {
            computerConnections[i] = new ArrayList<>();
        }

        for (int i = 0; i < computerPairCount; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int computer1 = Integer.parseInt(st.nextToken());
            int computer2 = Integer.parseInt(st.nextToken());

            computerConnections[computer1].add(computer2);
            computerConnections[computer2].add(computer1);
        }

        visited = new boolean[numOfComputers + 1];
        visited[1] = true;

        Queue<Integer> queue = new LinkedList<>();

        if (!computerConnections[1].isEmpty()) {
            for (int i = 0; i < computerConnections[1].size(); i++) {
                queue.offer(computerConnections[1].get(i));
            }
        }



        while(!queue.isEmpty()) {
            int computer = queue.poll();
            visited[computer] = true;

            for (int i = 0; i < computerConnections[computer].size(); i++) {
                int nextComputer = computerConnections[computer].get(i);

                if (visited[nextComputer]) continue;

                queue.offer(nextComputer);

            }
        }

        int infectedComputerCount = 0;
        for (int i = 2; i <= numOfComputers; i++) {
            if (visited[i]) infectedComputerCount++;
        }


        bw.write(infectedComputerCount + "\n");

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}
