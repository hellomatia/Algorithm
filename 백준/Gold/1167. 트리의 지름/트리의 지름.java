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
    int v;
    ArrayList<Node>[] tree;
    int[] parentsNode;
    boolean[] visitedNode;
    boolean[] visitedTree;
    int maxDiameter = -1;
    int furthest;
    public void solution() throws IOException {

        v = Integer.parseInt(bf.readLine());

        tree = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            tree[i] = new ArrayList<>();
        }

        parentsNode = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parentsNode[i] = i;
        }

        for (int i = 1; i <= v; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            while (to != -1) {
                int weight = Integer.parseInt(st.nextToken());

                tree[from].add(new Node(to, weight));
                union(from, to);

                to = Integer.parseInt(st.nextToken());
            }
        }

        visitedTree = new boolean[v + 1];


        for (int i = 1; i <= v; i++) {

            int treeNum = find(i);

            if (visitedTree[treeNum]) continue;

            visitedTree[treeNum] = true;

            visitedNode = new boolean[v + 1];
            visitedNode[i] = true;
            exploreTree(i, 0);

            visitedNode = new boolean[v + 1];
            visitedNode[furthest] = true;
            exploreTree(furthest, 0);
        }

        bw.write(maxDiameter + "\n");
        bw.flush();
        bw.close();
    }
    public void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if (x > y) parentsNode[x] = y;
        else parentsNode[y] = x;
    }
    public int find(int x) {
        if (parentsNode[x] == x) return x;
        return find(parentsNode[x]);
    }

    public void exploreTree(int nodeNum, int weight) {

        for (Node node : tree[nodeNum]) {
            if (!visitedNode[node.num]) {
                visitedNode[node.num] = true;
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