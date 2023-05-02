import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String croatiaAlpha = bf.readLine();


        croatiaAlpha = croatiaAlpha.replaceAll("c=","0");
        croatiaAlpha = croatiaAlpha.replaceAll("c-","0");
        croatiaAlpha = croatiaAlpha.replaceAll("dz=","0");
        croatiaAlpha = croatiaAlpha.replaceAll("d-","0");
        croatiaAlpha = croatiaAlpha.replaceAll("lj","0");
        croatiaAlpha = croatiaAlpha.replaceAll("nj","0");
        croatiaAlpha = croatiaAlpha.replaceAll("s=","0");
        croatiaAlpha = croatiaAlpha.replaceAll("z=","0");

        bw.write(String.valueOf(croatiaAlpha.length()));

        bf.close();

        bw.flush();
        bw.close();
    }
    }
