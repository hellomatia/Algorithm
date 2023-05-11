import java.io.*;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int money = Integer.parseInt(bf.readLine());
        int count=0;
        int tempMoney=0;

        for(int i=money/5; i>=0; i--){
            tempMoney = money;
            count = i;
            tempMoney -= (i*5);
            count += tempMoney/2;
            tempMoney %= 2;
            if(tempMoney==0){
                break;
            }
        }



        if(tempMoney!=0){
            bw.write("-1");
        }
        else {
            bw.write(String.valueOf(count));
        }


        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}

