import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String numString = bf.readLine();
        int[] numCountArr = new int[10];
        int sumOfDigits = 0;

        for(int i=0; i<numString.length(); i++){
            int num = Integer.parseInt(numString.substring(i,i+1));
            numCountArr[num]++;
            sumOfDigits += num;
        }

        StringBuilder sb = new StringBuilder();

        if(numCountArr[0]==0 || sumOfDigits%3 != 0){
            sb.append("-1");
        }
        else {
            for(int i=9; i>=0; i--){
                if(numCountArr[i]>0) {
                    for(int j=0; j<numCountArr[i]; j++){
                        sb.append(i);
                    }
                }
            }
        }

        bw.write(sb.toString());


        bw.flush();
        bw.close();

    }
}

