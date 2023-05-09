import java.io.*;

public class Main {
    public void solution() throws IOException{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String numString = bf.readLine();
        int[] numArr = new int[numString.length()];
        numArr[0] = numString.charAt(0) - '0';
        int count = 0;

        for(int i=1; i<numString.length(); i++){
            numArr[i] = numString.charAt(i) - '0';
            if(numArr[i-1]!=numArr[i]) {
                count++;
            }
        }

        if(count>1&&(count%2)==0){
            count /= 2;
        }
        else if(count>1&&(count%2)==1){
            count /= 2;
            count++;
        }

        bw.write(String.valueOf(count));


        bw.flush();
        bw.close();

    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

