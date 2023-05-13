import java.io.*;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int gameStageCount = Integer.parseInt(bf.readLine());
        int[] gameStageScore = new int[gameStageCount];

        for(int i=0; i<gameStageCount; i++){
            gameStageScore[i] = Integer.parseInt(bf.readLine());
        }

        int modifyCount = 0;

        for(int i=gameStageCount-1; i>=1; i--){
            if(gameStageScore[i]>gameStageScore[i-1]){
                continue;
            }else{
                modifyCount += (gameStageScore[i-1]-(gameStageScore[i]-1));
                gameStageScore[i-1] = gameStageScore[i]-1;
            }
        }

        bw.write(String.valueOf(modifyCount));
        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}

