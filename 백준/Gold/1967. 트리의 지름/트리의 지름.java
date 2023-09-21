import java.io.*;
import java.util.*;
class Node {
    int num;
    int weight;
    Node (int num, int weight) {
        this.num = num;
        this.weight = weight;
    }
}

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n;
    ArrayList<Node>[] tree;
    boolean[] visited;
    int maxDiameter = -1;
    public void solution() throws IOException {

        n = Integer.parseInt(bf.readLine());

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
             StringTokenizer st = new StringTokenizer(bf.readLine());
             int parentNode = Integer.parseInt(st.nextToken());
             int childNode = Integer.parseInt(st.nextToken());
             int weight = Integer.parseInt(st.nextToken());

             tree[parentNode].add(new Node(childNode, weight));
             tree[childNode].add(new Node(parentNode, weight));
        }


        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            visited[i] = true;
            exploreTree(i, 0);
        }

        bw.write(maxDiameter + "\n");
        bw.flush();
        bw.close();


    }

    public void exploreTree(int startNode, int weight) {

        for (Node node : tree[startNode]) {
            if (!visited[node.num]) {
                visited[node.num] = true;
                exploreTree(node.num, weight + node.weight);
            }
        }

        maxDiameter = Math.max(maxDiameter, weight);

    }



    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}