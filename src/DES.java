import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DES {
    // DES算法

    // IP 置换
    public static List<Integer> ipPermutation(String plaintext) {
        int[] IP = {2, 6, 3, 1, 4, 8, 5, 7};
        List<Integer> result = new ArrayList<>();

        for (int i : IP) {
            result.add(Character.getNumericValue(plaintext.charAt(i - 1)));
        }

        return result;
    }

    // IP 逆置换
    public static List<Integer> ipInversePermutation(String plaintext) {
        int[] IP_INV = {4, 1, 3, 5, 7, 2, 8, 6};
        List<Integer> result = new ArrayList<>();

        for (int i : IP_INV) {
            result.add(Character.getNumericValue(plaintext.charAt(i - 1)));
        }

        return result;
    }

    // P10 置换
    public static List<Integer> p10Permutation(String key) {
        int[] P10 = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
        List<Integer> result = new ArrayList<>();

        for (int i : P10) {
            result.add(Character.getNumericValue(key.charAt(i - 1)));
        }

        return result;
    }

    // 左移
    public static String leftShift(String key, int numShifts) {
        int length = key.length();
        numShifts %= length; // 防止超出字符串长度

        // 将字符串分为两部分，分别左移和右移
        String leftPart = key.substring(0, numShifts);
        String rightPart = key.substring(numShifts);

        // 合并左移和右移后的部分
        String shiftedKey = rightPart + leftPart;

        return shiftedKey;
    }

    // P8 置换
    public static List<Integer> p8Perxmutation(String key) {
        int[] P8 = {6, 3, 7, 4, 8, 5, 10, 9};
        List<Integer> result = new ArrayList<>();

        for (int i : P8) {
            result.add(Character.getNumericValue(key.charAt(i - 1)));
        }

        return result;
    }

    // EP 扩展置换

    /**
     * EP 扩展置换
     * @param rightHalf
     * @return List<Integer>
     */
    public static List<Integer> epPermutation(String rightHalf) {
        int[] EP = {4, 1, 2, 3, 2, 3, 4, 1};
        List<Integer> result = new ArrayList<>();

        for (int i : EP) {
            result.add(Character.getNumericValue(rightHalf.charAt(i - 1)));
        }

        return result;
    }

    /**
     * sp 置换
     * @param s
     * @return int[]
     */
    public static int[] spPermutation(String s) {
        int[] SP_BOX = {2, 4, 3, 1};
        int[] spResult = new int[SP_BOX.length];

        for (int i = 0; i < SP_BOX.length; i++) {
            spResult[i] = s.charAt(SP_BOX[i] - 1) - '0'; // 将字符转换为整数
        }
        return spResult;
    }

    /**
     * 生成K1
     * @param encryptedKey
     * @return List<Integer>
     */
    public static List<Integer> generateK1(String encryptedKey) {
        String leftHalf = encryptedKey.substring(0, 5);
        String rightHalf = encryptedKey.substring(5);

        // 左移操作
        String leftShiftedHalf = leftShift(leftHalf,1);// 左移1位
        String rightShiftedHalf = leftShift(rightHalf,1);

        // 合并左右两部分
        String beforeK1 = leftShiftedHalf + rightShiftedHalf;

        // P8 置换
        List<Integer> k1List = p8Perxmutation(beforeK1);

        return k1List;
    }


    /**
     * 生成K2
     * @param encryptedKey
     * @return List<Character>
     */
    public static List<Integer> generateK2(String encryptedKey) {
        String leftHalf = encryptedKey.substring(0, 5);
        String rightHalf = encryptedKey.substring(5);

        // 左移操作
        String leftShiftedHalf = leftShift(leftHalf, 2);
        String rightShiftedHalf = leftShift(rightHalf, 2);

        // 合并左右两部分
        String beforeK2 = leftShiftedHalf + rightShiftedHalf;

        // P8 置换
        List<Integer> k2List = p8Perxmutation(beforeK2);

        return k2List;
    }

    public static List<Integer> xor(List<Integer> arr1, List<Integer> arr2) {
        List<Integer> result = new ArrayList<>();

        // 确保两个数组具有相同的长度
        if (arr1.size() != arr2.size()) {
            throw new IllegalArgumentException("Input arrays must have the same length");
        }

        // 执行逐位异或操作
        for (int i = 0; i < arr1.size(); i++) {
            int element1 = arr1.get(i);
            int element2 = arr2.get(i);
            int xorResult = element1 ^ element2;
            result.add(xorResult);
        }

        return result;
    }


    public static List<Integer> sBox(List<Integer> R1_eo_K1) {
        List<Integer> leftHalf = R1_eo_K1.subList(0, 4);
        List<Integer> rightHalf = R1_eo_K1.subList(4, 8);

        int[][] SBOX1 = {
                {1, 0, 3, 2},
                {3, 2, 1, 0},
                {0, 2, 1, 3},
                {3, 1, 0, 2}
        };

        int[][] SBOX2 = {
                {0, 1, 2, 3},
                {2, 3, 1, 0},
                {3, 0, 1, 2},
                {2, 1, 0, 3}
        };

        int[] SP_BOX = {2, 4, 3, 1};

        // 分割左半部分和右半部分的位
        List<Integer> leftHalf1 = new ArrayList<>();
        leftHalf1.add(leftHalf.get(1));
        leftHalf1.add(leftHalf.get(2));

        List<Integer> leftHalf2 = new ArrayList<>();
        leftHalf2.add(leftHalf.get(0));
        leftHalf2.add(leftHalf.get(3));

        List<Integer> rightHalf1 = new ArrayList<>();
        rightHalf1.add(rightHalf.get(1));
        rightHalf1.add(rightHalf.get(2));

        List<Integer> rightHalf2 = new ArrayList<>();
        rightHalf2.add(rightHalf.get(0));
        rightHalf2.add(rightHalf.get(3));

        // 转换为字符串表示并从二进制转为十进制
        int leftDecimal1 = DESUtils.binaryToDecimal(leftHalf1);
        int leftDecimal2 = DESUtils.binaryToDecimal(leftHalf2);
        int rightDecimal1 = DESUtils.binaryToDecimal(rightHalf1);
        int rightDecimal2 = DESUtils.binaryToDecimal(rightHalf2);

        // 在S盒中查找对应的值
        int sLeft = SBOX1[leftDecimal2][leftDecimal1];
        int sRight = SBOX2[rightDecimal2][rightDecimal1];

        // 将S盒输出转换为二进制表示
        List<Integer> sLeft1 = DESUtils.decimalToBinary(sLeft, 2);
        List<Integer> sRight1 = DESUtils.decimalToBinary(sRight, 2);

        // 合并S盒输出
        List<Integer> F1 = new ArrayList<>();
        F1.addAll(sLeft1);
        F1.addAll(sRight1);

        // 执行SP盒置换
        List<Integer> F_SP1 = new ArrayList<>();
        for (int i = 0; i < SP_BOX.length; i++) {
            F_SP1.add(F1.get(SP_BOX[i] - 1));
        }

        return F_SP1;
    }


    /**
     * 加密1
     * @param plaintext
     * @param key
     * @return String
     */
    public static String encrypt(String plaintext, String key) {
        // 密钥侧处理
        List<Integer> encryptedKey = p10Permutation(key);
        String encryptedKeyString = DESUtils.listToBinaryString(encryptedKey);

        List<Integer> k1 = generateK1(encryptedKeyString);
        List<Integer> k2 = generateK2(encryptedKeyString);

        // 初始置换IP
        List<Integer> encryptedPlaintext = ipPermutation(plaintext);
        List<Integer> leftHalf = encryptedPlaintext.subList(0, 4);
        List<Integer> rightHalf = encryptedPlaintext.subList(4, 8);

        String leftHalfString = DESUtils.listToBinaryString(leftHalf);
        String rightHalfString = DESUtils.listToBinaryString(rightHalf);

        // 第一轮
        List<Integer> expandedRightHalf = epPermutation(leftHalfString);
        List<Integer> R1XorK1 = xor(expandedRightHalf, k1);
        List<Integer> F1 = sBox(R1XorK1);
        List<Integer> leftHalf1 = xor(leftHalf, F1);

        // 左右交换
        List<Integer> temp1 = leftHalf1;
        List<Integer> leftHalf2 = rightHalf;
        List<Integer> rightHalf2 = temp1;

        String rightHalf2String = DESUtils.listToBinaryString(rightHalf2);
        // 第二轮
        List<Integer> expandedRightHalf2 = epPermutation(rightHalf2String);
        List<Integer> R2XorK2 = xor(expandedRightHalf2, k2);
        List<Integer> F2 = sBox(R2XorK2);
        List<Integer> leftHalf3 = xor(leftHalf2, F2);

        // 合并左半部分和右半部分，得到加密前的数据
        List<Integer> beforeCiphertext = new ArrayList<>();
        beforeCiphertext.addAll(leftHalf3);
        beforeCiphertext.addAll(rightHalf2);


        String beforeCiphertextString = DESUtils.listToBinaryString(beforeCiphertext);
        // IP逆得到密文
        List<Integer> ciphertext = ipInversePermutation(beforeCiphertextString);

        String ciphertextString = DESUtils.listToBinaryString(ciphertext);

        return ciphertextString;
    }


    public static String decrypt(String ciphertext, String key) {
        // 密钥侧处理
        List<Integer> encryptedKey = p10Permutation(key);

        String encryptedKeyString = DESUtils.listToBinaryString(encryptedKey);

        List<Integer> k1 = generateK1(encryptedKeyString);
        List<Integer> k2 = generateK2(encryptedKeyString);

        // 初始置换IP
        List<Integer> initialCiphertext = ipPermutation(ciphertext);
        List<Integer> leftHalf = initialCiphertext.subList(0, 4);
        List<Integer> rightHalf = initialCiphertext.subList(4, 8);

        String rightHalfString = DESUtils.listToBinaryString(rightHalf);

        // 第一轮解密
        List<Integer> expandedRightHalf = epPermutation(rightHalfString);
        List<Integer> R1XorK1 = xor(expandedRightHalf, k2);
        List<Integer> F1 = sBox(R1XorK1);
        List<Integer> leftHalf1 = xor(leftHalf, F1);

        // 左右交换
        List<Integer> leftHalf2 = rightHalf;
        List<Integer> rightHalf2 = leftHalf1;

        String rightHalf2String = DESUtils.listToBinaryString(rightHalf2);
        // 第二轮解密
        List<Integer> expandedRightHalf2 = epPermutation(rightHalf2String);
        List<Integer> R2XorK2 = xor(expandedRightHalf2, k1);
        List<Integer> F2 = sBox(R2XorK2);
        List<Integer> leftHalf3 = xor(leftHalf2, F2);

        // 重组
        List<Integer> beforePlaintext = new ArrayList<>();
        beforePlaintext.addAll(leftHalf3);
        beforePlaintext.addAll(rightHalf2);

        String beforePlaintextString = DESUtils.listToBinaryString(beforePlaintext);

        // IP逆置换得到明文
        List<Integer> plaintext = ipInversePermutation(beforePlaintextString);

        String plaintextString = DESUtils.listToBinaryString(plaintext);

        return plaintextString;
    }

    public static void main(String[] args) {
        String ciphertext = "10110110"; // 示例密文
        String plaintext = "11111111"; // 示例明文
        String key = "0101101010"; // 示例密钥


        String decryptedPlaintext = decrypt(ciphertext, key);
        String encryptedCiphertext = encrypt(plaintext, key);

        System.out.println("解密后的明文：" + decryptedPlaintext);
        System.out.println("加密后的密文：" + encryptedCiphertext);
    }
}







