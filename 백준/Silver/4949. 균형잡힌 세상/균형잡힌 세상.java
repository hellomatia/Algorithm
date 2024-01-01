import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Stack<Character> stack;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        String string = init();
        while (!string.equals(".")){
            calcResult(string);
            string = init();
        }
        bw.flush();
        bw.close();
    }

    private String init() throws IOException {
        return bf.readLine();
    }

    private void calcResult(String string) throws IOException {
        stack = new Stack<>();
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if (ch == '(' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']') {
                if (stack.isEmpty()) {
                    printResult("no");
                    return;
                } else if (ch == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (ch == ']' && stack.peek() == '[') {
                    stack.pop();
                } else {
                    printResult("no");
                    return;
                }
            }
        }
        if (stack.isEmpty()) {
            printResult("yes");
            return;
        }
        printResult("no");
    }

    private void printResult(String result) throws IOException {
        bw.write(result + "\n");
    }
}
