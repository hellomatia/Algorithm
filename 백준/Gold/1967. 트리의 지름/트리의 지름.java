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
    int furthest;
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

        visited = new boolean[n + 1];
        visited[1] = true;
        exploreTree(1, 0);

        visited = new boolean[n + 1];
        visited[furthest] = true;
        exploreTree(furthest, 0);


        bw.write(maxDiameter + "\n");
        bw.flush();
        bw.close();
    }

    public void exploreTree(int nodeNum, int weight) {

        for (Node node : tree[nodeNum]) {
            if (!visited[node.num]) {
                visited[node.num] = true;
                exploreTree(node.num, weight + node.weight);
            }
        }

        if (maxDiameter < weight) {
            maxDiameter = weight;
            furthest = nodeNum;
        }

    }



    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}