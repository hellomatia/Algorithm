import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private int numOfBuildings;
    private int[] buildings;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        numOfBuildings = Integer.parseInt(bf.readLine());

        buildings = new int[numOfBuildings];
        for (int i = 0; i < numOfBuildings; i++) {
            buildings[i] = Integer.parseInt(bf.readLine());
        }
    }

    private String calcAns() {
        long ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numOfBuildings; i++) {
            while (!stack.isEmpty()) {
                if (stack.peek() <= buildings[i]) {
                    stack.pop();
                } else {
                    break;
                }
            }
            ans += stack.size();
            stack.push(buildings[i]);
        }
        return ans + "";
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans);
        bw.flush();
    }
}