import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DESEncryptionGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("DES Encryption/Decryption");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridBagLayout()); // Use GridBagLayout


        // Center the window on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        JPanel inputPanel = new JPanel();
        JLabel inputLabel = new JLabel("Input Text: ");
        JTextField inputField = new JTextField(20);
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        JPanel keyPanel = new JPanel();
        JLabel keyLabel = new JLabel("Key: ");
        JTextField keyField = new JTextField(20);
        keyField.setFont(new Font("Arial", Font.PLAIN, 24));
        keyPanel.add(keyLabel);
        keyPanel.add(keyField);

        JPanel resultPanel = new JPanel();
        JLabel resultLabel = new JLabel("Result: ");
        JTextField resultField = new JTextField(20);
        resultField.setFont(new Font("Arial", Font.PLAIN, 24));
        resultField.setEditable(false);
        resultPanel.add(resultLabel);
        resultPanel.add(resultField);

        JPanel buttonPanel = new JPanel();
        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");
        JButton clearButton = new JButton("Clear");
        encryptButton.setFont(new Font("Arial", Font.BOLD, 20));
        decryptButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);
        buttonPanel.add(clearButton);

        // Add components with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(inputPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(keyPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(resultPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(buttonPanel, gbc);



        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                String keyText = keyField.getText();

                if(DESUtils.isLegalInput(inputText) && DESUtils.isLegalKey(keyText)) {
                    if(inputText.length() == 1){
                        String temp1 = DESUtils.charToBinaryString(inputText);
                        String encryptedText = DES.encrypt(temp1, keyText);
                        String temp2 = DESUtils.binaryStringToChar(encryptedText);
                        resultField.setText(temp2);
                    }
                    else{
                        String encryptedText = DES.encrypt(inputText, keyText);
                        resultField.setText(encryptedText);
                    }

                }else{
                    JOptionPane.showMessageDialog(frame, "Input text or key is not legal!");
                    return;
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                String keyText = keyField.getText();

                if(DESUtils.isLegalInput(inputText) && DESUtils.isLegalKey(keyText)) {
                    if(inputText.length() == 1){
                        String temp1 = DESUtils.charToBinaryString(inputText);
                        String encryptedText = DES.decrypt(temp1, keyText);
                        String temp2 = DESUtils.binaryStringToChar(encryptedText);
                        resultField.setText(temp2);
                    }
                    else{
                        String decryptedText = DES.decrypt(inputText, keyText);
                        resultField.setText(decryptedText);
                    }

                }else{
                    JOptionPane.showMessageDialog(frame, "Input text or key is not legal!");
                    return;
                }

            }

        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                keyField.setText("");
                resultField.setText("");
            }
        });

        frame.setVisible(true);
    }
}
