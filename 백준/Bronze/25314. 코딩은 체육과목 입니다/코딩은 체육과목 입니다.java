import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int byteNum = scanner.nextInt();
        byteNum = byteNum/4;

        for (int i = 1 ; i <= byteNum ; i++) {
            System.out.print("long ");
            if (i==byteNum) {
                System.out.print("int");
            }
        }
    }
}
