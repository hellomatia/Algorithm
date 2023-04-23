import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int firstNum = scanner.nextInt();
        int secondNum = scanner.nextInt();

        if (firstNum>secondNum) {
            System.out.println(">");
        } else if (firstNum<secondNum) {
            System.out.println("<");
        }
        else {
            System.out.println("==");
        }

    }
}
