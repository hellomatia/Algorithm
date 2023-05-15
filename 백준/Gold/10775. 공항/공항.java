import java.io.*;

public class Main {

    public static int[] parent;
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x!=y) {
            parent[x] = y;
        }
    }
    public static int find(int x) {
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int gateCount = Integer.parseInt(bf.readLine());
        int airplaneCount = Integer.parseInt(bf.readLine());
        parent = new int[gateCount+1];

        for(int i=1; i<gateCount+1; i++) {
            parent[i] = i;
        }

        int ans = 0;

        for(int i=0; i<airplaneCount; i++){
            int gate = Integer.parseInt(bf.readLine());
            int emptyGate = find(gate);

            if(emptyGate == 0) {
                break;
            }
            ans++;
            union(emptyGate, emptyGate-1);

        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}