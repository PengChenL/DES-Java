import java.util.ArrayList;
import java.util.List;

public class DESUtils {

    public static int[] concatenateArrays(int[] array1, int[] array2) {
        int[] concatenatedArray = new int[array1.length + array2.length];
        System.arraycopy(array1, 0, concatenatedArray, 0, array1.length);
        System.arraycopy(array2, 0, concatenatedArray, array1.length, array2.length);
        return concatenatedArray;
    }

    /**
     * 将二进制转换为十进制
     * @param binaryList
     * @return 十进制数
     */
    public static int binaryToDecimal(List<Integer> binaryList) {
        int decimal = 0;
        int power = binaryList.size() - 1;
        for (int bit : binaryList) {
            decimal += bit * Math.pow(2, power);
            power--;
        }
        return decimal;
    }

    /**
     * 将十进制转换为二进制
     * @param decimal
     * @param numBits
     * @return 二进制数
     */
    public static List<Integer> decimalToBinary(int decimal, int numBits) {
        List<Integer> binaryList = new ArrayList<>();
        for (int i = numBits - 1; i >= 0; i--) {
            int bit = (decimal >> i) & 1;
            binaryList.add(bit);
        }
        return binaryList;
    }

    /**
     * 将二进制字符串转换为二进制列表
     * @param list
     * @return 二进制列表
     */
    public static String listToBinaryString(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Integer value : list) {
            stringBuilder.append(value);
        }

        return stringBuilder.toString();
    }

    /**
     * 判断输入是否合法
     * @param input
     * @return true or false
     */
    public static boolean isLegalInput(String input) {
        boolean flag = false;
        if(input.matches("[01]{8}")) {
            flag = true;
        }

        if(input.length() == 1 && (int) input.charAt(0) >= 0 && (int) input.charAt(0) <= 127){
            flag = true;
        }

        return flag;

    }
    public static boolean isLegalKey(String key){
        return key.matches("[01]{10}");
    }

    /**
     * 将字长度为1的字符串换为二进制字符串
     * @param s
     * @return 二进制字符串
     */
    public static String charToBinaryString(String s) {
        char character = s.charAt(0);
        int asciiValue = (int) character;

        String binaryRepresentation = Integer.toBinaryString(asciiValue);
        while (binaryRepresentation.length() < 8) {
            binaryRepresentation = "0" + binaryRepresentation;
        }

        return binaryRepresentation;
    }

    /**
     * 将二进制字符串转换为长度为1的字符串
     * @param binaryString
     * @return 字符串
     */
    public static String binaryStringToChar(String binaryString) {
        int asciiValue = Integer.parseInt(binaryString, 2);
        char character = (char) asciiValue;

        String res = String.valueOf(character);

        return res;
    }


}
