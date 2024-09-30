class Solution {
    
    public String intToRoman(int num) {

        StringBuilder sb = new StringBuilder();

        String numStr = String.valueOf(num);

        for (int i = 0; i < numStr.length(); i++) {
            int n = numStr.charAt(i) - '0';

            int digits = numStr.length() - 1 - i;
            
            if (n == 9 || n == 4) {
                sb.append(startWith4or9(n * (int) Math.pow(10, digits)));
                continue;
            }
            if (digits == 3) {
                sb.append(fourDigitsNum(n * (int) Math.pow(10, digits)));
            } else if (digits == 2) {
                sb.append(threeDigitsNum(n * (int) Math.pow(10, digits)));
            } else if (digits == 1) {
                sb.append(twoDigitsNum(n * (int) Math.pow(10, digits)));
            } else {
                sb.append(oneDigitsNum(n * (int) Math.pow(10, digits)));
            }
        }

        return sb.toString();
    }

    public String startWith4or9(int num) {
        switch (num) {
            case 9:
                return "IX";
            case 90:
                return "XC";
            case 900:
                return "CM";
            case 4:
                return "IV";
            case 40:
                return "XL";
            case 400:
                return "CD";
            default:
                return "";

        }
    }

    public String fourDigitsNum(int num) {
        switch (num) {
            case 1000:
                return "M";
            case 2000:
                return "MM";
            case 3000:
                return "MMM";
            default:
                return "";
        }
    }

    public String threeDigitsNum(int num) {
        switch (num) {
            case 100:
                return "C";
            case 200:
                return "CC";
            case 300:
                return "CCC";
            case 500:
                return "D";
            case 600:
                return "DC";
            case 700:
                return "DCC";
            case 800:
                return "DCCC";
            default:
                return "";
        }
    }

    public String twoDigitsNum(int num) {
        switch (num) {
            case 10:
                return "X";
            case 20:
                return "XX";
            case 30:
                return "XXX";
            case 50:
                return "L";
            case 60:
                return "LX";
            case 70:
                return "LXX";
            case 80:
                return "LXXX";
            default:
                return "";
        }
    }

    public String oneDigitsNum(int num) {
        switch (num) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            default:
                return "";
        }
    }
}