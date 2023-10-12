import java.util.Arrays;

public class BruteForce {
    public static void main(String[] args) {
        String key = "0101101010";
        String plaintext1 = "11111111";
        String plaintext2 = "11111110";
        String plaintext3 = "11111101";
        String plaintext4 = "11111100";
        String ciphertext1 = "00001111";
        String ciphertext2 = "01110110";
        String ciphertext3 = "01011010";
        String ciphertext4 = "01111001";

        long l = System.currentTimeMillis();
        for (int i = 0; i < 1024; i++) {
            String binary = String.format("%10s", Integer.toBinaryString(i)).replace(' ', '0');

            String newKey = binary;

            String newCiphertext1 = DES.encrypt(plaintext1, newKey);
            String newCiphertext2 = DES.encrypt(plaintext2, newKey);
            String newCiphertext3 = DES.encrypt(plaintext3, newKey);
            String newCiphertext4 = DES.encrypt(plaintext4, newKey);

            if (newCiphertext1.equals(ciphertext1) && newCiphertext2.equals(ciphertext2) && newCiphertext3.equals(ciphertext3) && newCiphertext4.equals(ciphertext4)
                     ) {
                long l2 = System.currentTimeMillis();
                System.out.println("Time: " + (l2 - l) + "ms");
                System.out.println("Found Key: " + newKey);
                System.out.println("Iteration: " + i);
            }
        }
    }
}





