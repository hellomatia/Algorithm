import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String board = bf.readLine();

        StringTokenizer st = new StringTokenizer(board, ".");

        String[] xParts = new String[st.countTokens()];

        boolean hasOdd=false;

        int i=0;
        while(st.hasMoreTokens()) {
            xParts[i] = st.nextToken();
            if(xParts[i].length()%2==1){
                hasOdd = true;
            }
            i++;
        }

        st = new StringTokenizer(board,"X");
        String[] dotParts = new String[st.countTokens()];

        i=0;
        while(st.hasMoreTokens()) {
            dotParts[i] = st.nextToken();
            i++;
        }

        if(!hasOdd){
            if(board.charAt(0)=='X'){
                for(i=0; i<xParts.length; i++) {
                    int temp = xParts[i].length()/4;
                    for(int j=0; j<temp; j++) {
                        bw.write("AAAA");
                    }
                    for(int j=0; j<(xParts[i].length()-temp*4)/2; j++){
                        bw.write("BB");
                    }
                    if(i > dotParts.length-1) {
                        break;
                    }
                    bw.write(dotParts[i]);
                }
            } else {
                for(i=0; i<dotParts.length; i++) {
                    bw.write(dotParts[i]);
                    if(i > xParts.length-1) {
                        break;
                    }
                    int temp = xParts[i].length()/4;
                    for(int j=0; j<temp; j++) {
                        bw.write("AAAA");
                    }
                    for(int j=0; j<(xParts[i].length()-temp*4)/2; j++){
                        bw.write("BB");
                    }


                }
            }

        } else {
            bw.write("-1");
        }

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}