
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int CARD_COUNT = 52;
    private static final int MOD = 10_007;

    private static BigInteger[] factorial;
    private static int N;

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
    	init();
    	printResult(calcResult());
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
    	N = Integer.parseInt(bf.readLine());
    	factorial = new BigInteger[CARD_COUNT + 1];
    	factorial[0] = BigInteger.ONE;
    	for (int i = 1; i <= CARD_COUNT; i++) {
    		factorial[i] = factorial[i - 1].multiply(BigInteger.valueOf(i));
    	}
    }

    private long calcResult() {
    	int fourCardCount = N / 4;
    	BigInteger winCombiCount = BigInteger.ZERO;
    	
    	for (int i = 1; i <= fourCardCount; i += 2) {
        	winCombiCount = winCombiCount.add(calcCombi(13, i).multiply(calcCombi(CARD_COUNT - (4 * i), N - (4 * i))));
    	}
    	for (int i = 2; i <= fourCardCount; i += 2) {
    		winCombiCount = winCombiCount.subtract(calcCombi(13, i).multiply(calcCombi(CARD_COUNT - (4 * i), N - (4 * i))));
    	}
    	    	
    	return winCombiCount.mod(BigInteger.valueOf(MOD)).longValue();
    }
    
    private BigInteger calcCombi(int num, int pick) {
    	return factorial[num].divide(factorial[pick].multiply(factorial[num - pick]));
    }

    private void printResult(long result) throws IOException {
        bw.write(result + "\n");
    }
}