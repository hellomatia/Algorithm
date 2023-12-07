import java.io.*;
import java.util.*;
class Stack {

    private ArrayList<Integer> arrayList;

    Stack() {
        this.arrayList = new ArrayList<>();
    }

    public void push(int num) {
        arrayList.add(num);
    }

    public int pop() {
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

    public int top() {
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
    private static Stack stack;


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
        stack = new Stack();
    }

    private void readCommand() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String command = st.nextToken();

        if(command.equals("push")) {
            int num = Integer.parseInt(st.nextToken());
            stack.push(num);
        }
        if(command.equals("pop")) {
            printResult(stack.pop());
        }
        if(command.equals("size")) {
            printResult(stack.size());
        }
        if(command.equals("empty")) {
            printResult(stack.empty());
        }
        if(command.equals("top")) {
            printResult(stack.top());
        }
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}
