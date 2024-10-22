import java.math.BigInteger;

class Solution {
    public String multiply(String num1, String num2) {
        BigInteger bigInteger1 = new BigInteger(num1);
        BigInteger bigInteger2 = new BigInteger(num2);
        BigInteger answer = bigInteger1.multiply(bigInteger2);
        return answer.toString();
    }
}
