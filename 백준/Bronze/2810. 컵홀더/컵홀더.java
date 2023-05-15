import java.io.*;

public class Main {

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int seatNum = Integer.parseInt(bf.readLine());
        String seat = bf.readLine();

        String convertToAllS = seat.replaceAll("LL", "s");

        if(seat.length()!=convertToAllS.length()) {
            bw.write(String.valueOf(convertToAllS.length()+1));
        } else {
            bw.write(String.valueOf(convertToAllS.length()));
        }

        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}