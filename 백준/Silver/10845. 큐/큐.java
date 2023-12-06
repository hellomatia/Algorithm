import java.io.*;
import java.util.*;
class Queue {
    
    private ArrayList<Integer> arrayList;

    Queue() {
        this.arrayList = new ArrayList<>();
    }

    public void push(int num) {
        arrayList.add(num);
    }

    public int pop() {
        if (arrayList.isEmpty()) {
            return -1;
        }
        int num = arrayList.get(0);
        arrayList.remove(0);
        return num;
    }

    public int size() {
        return arrayList.size();
    }

    public int empty() {
        if (arrayList.isEmpty()) {
            return 1;
        }
        return 0;
    }

    public int front() {
        if (arrayList.isEmpty()) {
            return -1;
        }
        return arrayList.get(0);
    }

    public int back() {
        if (arrayList.isEmpty()) {
            return -1;
        }
        return arrayList.get(arrayList.size() - 1);
    }
}
public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static Queue queue;


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        for (int i = 0; i < N; i++) {
            readCommand();
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        queue = new Queue();
    }

    private void readCommand() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String command = st.nextToken();

        if(command.equals("push")) {
            int num = Integer.parseInt(st.nextToken());
            queue.push(num);
        }
        if(command.equals("pop")) {
            printResult(queue.pop());
        }
        if(command.equals("size")) {
            printResult(queue.size());
        }
        if(command.equals("empty")) {
            printResult(queue.empty());
        }
        if(command.equals("front")) {
            printResult(queue.front());
        }
        if(command.equals("back")) {
            printResult(queue.back());
        }
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
