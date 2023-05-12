import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int multitabNum = Integer.parseInt(st.nextToken());
        int numOfUses = Integer.parseInt(st.nextToken());

        int[] orderOfUse = new int[numOfUses];
        boolean[] isItUse = new boolean[101];

        st = new StringTokenizer(bf.readLine(), " ");

        for(int i=0; i<numOfUses; i++) {
            orderOfUse[i] = Integer.parseInt(st.nextToken());
        }

        int multitabUseCount = 0;
        int minCount = 0;

        for(int i=0; i<numOfUses; i++) {
            int tempUse = orderOfUse[i];

            if(!isItUse[tempUse]){
                if(multitabUseCount<multitabNum) {
                    multitabUseCount++;
                    isItUse[tempUse] = true;
                }
                else {
                    ArrayList<Integer> nextDeviceList = new ArrayList<>();
                    for(int j=i; j<numOfUses; j++) {
                        if(isItUse[orderOfUse[j]]&&!nextDeviceList.contains(orderOfUse[j])) {
                            nextDeviceList.add(orderOfUse[j]);
                        }
                    }
                    if(nextDeviceList.size()!=multitabNum) {
                        for(int j=0; j<isItUse.length; j++){
                            if(isItUse[j]&&!nextDeviceList.contains(j)) {
                                isItUse[j] = false;
                                break;
                            }
                        }
                    }
                    else {
                        int remove = nextDeviceList.get(nextDeviceList.size()-1);
                        isItUse[remove] = false;
                    }
                    isItUse[tempUse] = true;
                    minCount++;
                }
            }
        }

        bw.write(String.valueOf(minCount));
        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }
}

