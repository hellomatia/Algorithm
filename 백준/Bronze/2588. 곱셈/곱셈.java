import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String firstNum_str, secondNum_str;
        firstNum_str = scanner.nextLine();
        secondNum_str = scanner.nextLine();

        int firstNum = Integer.parseInt(firstNum_str);
        int secondNum = Integer.parseInt(secondNum_str);

        int firstPlaceNum = Integer.parseInt(secondNum_str.substring(2));
        int tenPlaceNum = Integer.parseInt(secondNum_str.substring(1,2));
        int hundredPlaceNum = Integer.parseInt(secondNum_str.substring(0,1));

        System.out.println(firstNum*firstPlaceNum);
        System.out.println(firstNum*tenPlaceNum);
        System.out.println(firstNum*hundredPlaceNum);
        System.out.println(firstNum*secondNum);


    }
}
