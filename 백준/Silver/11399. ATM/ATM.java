import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numPeople = Integer.parseInt(bf.readLine());
        int[] withdrawTimeArr = new int[numPeople];
        int withdrawTime = 0;
        int minTotalWithdrawTime = 0;

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for(int i=0; i<numPeople; i++){
            withdrawTimeArr[i] = Integer.parseInt(st.nextToken());
        }

        withdrawTimeArr = mergeSort(withdrawTimeArr);

        for(int i=0; i<withdrawTimeArr.length; i++) {
            withdrawTime += withdrawTimeArr[i];
            minTotalWithdrawTime += withdrawTime;
        }

        bw.write(String.valueOf(minTotalWithdrawTime));

        bw.flush();
        bw.close();

    }

    static int[] mergeSort(int[] arr){

        if (arr.length < 2) return arr;

        int mid = arr.length / 2;
        int[] lowArr = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] highArr = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));

        int[] mergedArr = new int[arr.length];

        int m = 0, l = 0, h = 0;
        while (l < lowArr.length && h < highArr.length) {
            if (lowArr[l] < highArr[h])
                mergedArr[m++] = lowArr[l++];
            else
                mergedArr[m++] = highArr[h++];
        }
        while (l < lowArr.length) {
            mergedArr[m++] = lowArr[l++];
        }
        while (h < highArr.length) {
            mergedArr[m++] = highArr[h++];
        }
        return mergedArr;

    }
}



