import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] tree;
    static int[][] parent;
    static int[] depth;
    static int maxHeight;
    static int N;
    
    static class FastReader {
        private static final int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
 
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
    }
    
    static class FastWriter {
        private static final int BUFFER_SIZE = 1 << 16;
        private DataOutputStream dout;
        private byte[] buffer;
        private int pointer;
 
        public FastWriter() {
            dout = new DataOutputStream(System.out);
            buffer = new byte[BUFFER_SIZE];
            pointer = 0;
        }
 
        private void writeBuffer() throws IOException {
            if (pointer > 0) {
                dout.write(buffer, 0, pointer);
                pointer = 0;
            }
        }
 
        public void flush() throws IOException {
            writeBuffer();
            dout.flush();
        }
 
        public void close() throws IOException {
            flush();
            dout.close();
        }
 
        public void writeInt(int i) throws IOException {
            if (i == -1) {
                write((byte)'-');
                write((byte)'1');
                write((byte)'\n');
                return;
            }
            
            if (i == 0) {
                write((byte)'0');
                write((byte)'\n');
                return;
            }
            
            if (i < 0) {
                write((byte)'-');
                i = -i;
            }
            
            int digits = 0;
            int temp = i;
            while (temp > 0) {
                digits++;
                temp /= 10;
            }
            
            while (digits > 0) {
                int pow = 1;
                for (int j = 1; j < digits; j++) pow *= 10;
                write((byte)(i / pow + '0'));
                i %= pow;
                digits--;
            }
            write((byte)'\n');
        }
 
        private void write(byte b) throws IOException {
            if (pointer == buffer.length) {
                writeBuffer();
            }
            buffer[pointer++] = b;
        }
    }
    
    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        FastWriter fw = new FastWriter();
        
        N = fr.nextInt();
        
        // Initialize tree
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        
        // Read edges
        for (int i = 0; i < N - 1; i++) {
            int x = fr.nextInt();
            int y = fr.nextInt();
            tree[x].add(y);
            tree[y].add(x);
        }
        
        // Calculate max height for LCA
        maxHeight = (int) Math.ceil(Math.log(N) / Math.log(2));
        parent = new int[N + 1][maxHeight + 1];
        depth = new int[N + 1];
        
        // Initialize depth and parent arrays
        dfs(1, 0);
        fillParent();
        
        // Process queries
        int Q = fr.nextInt();
        while (Q-- > 0) {
            int a = fr.nextInt();
            int b = fr.nextInt();
            int c = fr.nextInt();
            
            fw.writeInt(findCircumcenter(a, b, c));
        }
        
        fw.flush();
        fw.close();
        fr.close();
    }
    
    static void dfs(int current, int prev) {
        depth[current] = depth[prev] + 1;
        parent[current][0] = prev;
        
        for (int next : tree[current]) {
            if (next != prev) {
                dfs(next, current);
            }
        }
    }
    
    static void fillParent() {
        for (int i = 1; i <= maxHeight; i++) {
            for (int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }
    
    static int lca(int a, int b) {
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        
        for (int i = maxHeight; i >= 0; i--) {
            if (depth[b] - depth[a] >= (1 << i)) {
                b = parent[b][i];
            }
        }
        
        if (a == b) return a;
        
        for (int i = maxHeight; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        
        return parent[a][0];
    }
    
    static int getDistance(int a, int b) {
        int lcaNode = lca(a, b);
        return depth[a] + depth[b] - 2 * depth[lcaNode];
    }
    
    static int getNodeAtDistance(int start, int end, int distance) {
        int lcaNode = lca(start, end);
        
        if (depth[start] - depth[lcaNode] >= distance) {
            int current = start;
            for (int i = maxHeight; i >= 0; i--) {
                if (distance >= (1 << i)) {
                    current = parent[current][i];
                    distance -= (1 << i);
                }
            }
            return current;
        } else {
            int distToLca = depth[start] - depth[lcaNode];
            int remaining = distance - distToLca;
            int distFromLca = depth[end] - depth[lcaNode];
            if (remaining > distFromLca) return -1;
            
            int current = end;
            int upDistance = distFromLca - remaining;
            for (int i = maxHeight; i >= 0; i--) {
                if (upDistance >= (1 << i)) {
                    current = parent[current][i];
                    upDistance -= (1 << i);
                }
            }
            return current;
        }
    }
    
    static int findCircumcenter(int a, int b, int c) {
        int distAB = getDistance(a, b);
        int distBC = getDistance(b, c);
        int distCA = getDistance(c, a);
        
        int[] candidates = new int[6];
        candidates[0] = getNodeAtDistance(a, b, distAB / 2);
        candidates[1] = getNodeAtDistance(a, b, (distAB + 1) / 2);
        candidates[2] = getNodeAtDistance(b, c, distBC / 2);
        candidates[3] = getNodeAtDistance(b, c, (distBC + 1) / 2);
        candidates[4] = getNodeAtDistance(c, a, distCA / 2);
        candidates[5] = getNodeAtDistance(c, a, (distCA + 1) / 2);
        
        int result = -1;
        int minDist = Integer.MAX_VALUE;
        
        for (int candidate : candidates) {
            if (candidate == -1) continue;
            
            int distToA = getDistance(candidate, a);
            int distToB = getDistance(candidate, b);
            int distToC = getDistance(candidate, c);
            
            if (distToA == distToB && distToB == distToC && distToA < minDist) {
                minDist = distToA;
                result = candidate;
            }
        }
        
        return result;
    }
}