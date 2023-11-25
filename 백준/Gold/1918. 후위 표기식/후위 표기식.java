import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static String infixNotation;
    private static int[] opPriority = new int[50];


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    private void solution() throws IOException {
        initInfixNotation();
        printResult(infixNotationToPostfixNotation(infixNotation));
        bw.flush();
        bw.close();
    }

    private void initInfixNotation() throws IOException {
        infixNotation = bf.readLine();
    }

    private String infixNotationToPostfixNotation(String infixNotation) {
        opPriority['*'] = opPriority['/'] = 1;
        opPriority['('] = opPriority[')'] = -1;
        Stack<Character> formulas = new Stack<>();
        StringBuilder postfixNotation = new StringBuilder();

        for (char ch : infixNotation.toCharArray()) {
            if ('A' <= ch && ch <= 'Z') {
                postfixNotation.append(ch);
                continue;
            }
            if (ch == '(') {
                formulas.push(ch);
                continue;
            }
            if (ch == ')') {
                char formula = formulas.pop();
                while (formula != '(') {
                    postfixNotation.append(formula);
                    formula = formulas.pop();
                }
                continue;
            }
            while (!formulas.isEmpty() && opPriority[formulas.peek()] >= opPriority[ch]) {
                postfixNotation.append(formulas.pop());
            }
            formulas.add(ch);
        }

        while (!formulas.isEmpty()) {
            postfixNotation.append(formulas.pop());
        }
        return postfixNotation.toString();
    }



    private void printResult(String result) throws IOException {
        bw.write(result);
    }
}
