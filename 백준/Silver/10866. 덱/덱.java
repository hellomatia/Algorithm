import java.io.*;
import java.util.*;
class Deque {

    private ArrayList<Integer> arrayList;

    Deque() {
        this.arrayList = new ArrayList<>();
    }

    public void pushFront(int num) {
        ArrayList<Integer> tempList = new ArrayList<>();
        tempList.add(num);
        tempList.addAll(arrayList);
        arrayList = tempList;
    }

    public void pushBack(int num) {
        arrayList.add(num);
    }

    public int popFront() {
        if (arrayList.isEmpty()) {
            return -1;
        }
        int num = arrayList.get(0);
        arrayList.remove(0);
        return num;
    }

    public int popBack() {
        if (arrayList.isEmpty()) {
            return -1;
        }
        int num = arrayList.get(size() - 1);
        arrayList.remove(size() - 1);
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
        return arrayList.get(size() - 1);
    }
}

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static Deque deque;


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
        deque = new Deque();
    }

    private void readCommand() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String command = st.nextToken();

        if(command.equals("push_front")) {
            int num = Integer.parseInt(st.nextToken());
            deque.pushFront(num);
        }
        if(command.equals("push_back")) {
            int num = Integer.parseInt(st.nextToken());
            deque.pushBack(num);
        }
        if(command.equals("pop_front")) {
            printResult(deque.popFront());
        }
        if(command.equals("pop_back")) {
            printResult(deque.popBack());
        }
        if(command.equals("size")) {
            printResult(deque.size());
        }
        if(command.equals("empty")) {
            printResult(deque.empty());
        }
        if(command.equals("front")) {
            printResult(deque.front());
        }
        if(command.equals("back")) {
            printResult(deque.back());
        }
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
