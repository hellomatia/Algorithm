import java.io.*;
import java.util.*;


public class Main {
    ArrayList<Character> vowel = new ArrayList<>();
    ArrayList<Character> consonant = new ArrayList<>();
    ArrayList<String> codeList = new ArrayList<>();

    int L, C;
    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        L = Integer.parseInt(st.nextToken()); // 만들어야 하는 암호 글자 수
        C = Integer.parseInt(st.nextToken()); // 알파벳 개수


        st = new StringTokenizer(bf.readLine());

        for(int i=0; i<C; i++) {
            String str = st.nextToken();
            char ch = str.charAt(0);
            //모음일 경우 vowel, 자음일 경우 consonant
            if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') {
                vowel.add(ch);
            } else {
                consonant.add(ch);
            }
        }

        StringBuilder code = new StringBuilder();

        makeCodeVowelPart(code, 0, 0);

        Collections.sort(codeList);

        for(int i=0; i<codeList.size(); i++) {
            bw.write(codeList.get(i));
            bw.write("\n");
        }
        


        bw.flush();
        bw.close();
    }

    public void makeCodeVowelPart(StringBuilder code, int idx, int count) {
        if(code.length()>=L-1){
            return;
        }

        //System.out.println(1);

        if(0<count && count<L-1) {
            makeCodeConsonantPart(code, 0, 0);
        }

        for(int i=idx; i<vowel.size(); i++) {
            code.append(vowel.get(i));
            makeCodeVowelPart(code, i+1, count+1);
            code.deleteCharAt(code.length()-1);
        }
    }

    public void makeCodeConsonantPart(StringBuilder code, int idx, int count) {
        if(code.length()>L) {
            return;
        }

        if(code.length()==L) {
            sortCode(code);
        }

        for(int i=idx; i<consonant.size(); i++) {
            code.append(consonant.get(i));
            makeCodeConsonantPart(code, i+1, count+1);
            code.deleteCharAt(code.length()-1);
        }
    }

    public void sortCode(StringBuilder code) {

        char[] chrArr = code.toString().toCharArray();

        Arrays.sort(chrArr);

        String str = new String(chrArr);

        codeList.add(str);
    }

    public static void main (String[] args) throws IOException {
        new Main().solution();
    }


}