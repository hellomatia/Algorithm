import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int rootNode;
    static int deleteNode;
    static int leafNodeCount = 0;
    static ArrayList<Integer>[] tree;

    public void solution() throws IOException {

        int nodeCount = Integer.parseInt(bf.readLine());

        tree = new ArrayList[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < nodeCount; i++) {
            int parentNode = Integer.parseInt(st.nextToken());

            if (parentNode == -1) {
                rootNode = i;
                continue;
            }

            tree[parentNode].add(i);
        }

        deleteNode = Integer.parseInt(bf.readLine());

        if (rootNode != deleteNode) {
            exploreTree(rootNode);
        }


        bw.write(leafNodeCount + "\n");

        bw.flush();
        bw.close();
    }

    public void exploreTree(int node) {

        if (tree[node].isEmpty()) {
            leafNodeCount++;
            return;
        }

        for (int i = 0; i < tree[node].size(); i++) {
            int nextNode = tree[node].get(i);

            if (nextNode == deleteNode) {
                if (tree[node].size() == 1) {
                    leafNodeCount++;
                }
                continue;
            }

            exploreTree(nextNode);
        }

    }

    public static void main (String[] args) throws IOException {

        new Main().solution();

    }
}