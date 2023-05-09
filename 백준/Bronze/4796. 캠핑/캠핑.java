import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public void solution() throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = 0;
        int L, P, V;

        while(true){
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            L = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());

            if(L==0){
                break;
            }

            bw.write("Case ");
            bw.write(String.valueOf(++count));
            bw.write(": ");
            bw.write(String.valueOf(((V/P)*L)+Math.min(L,(V%P))));
            bw.write("\n");

        }

        bw.flush();
        bw.close();

    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

