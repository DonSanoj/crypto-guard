import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.KeyPair;

public class EncryptionTool extends JFrame implements ActionListener {

    private JTextField messageField;
    private JTextArea outputArea;
    private JButton encryptAesButton, decryptAesButton, encryptRsaButton, decryptRsaButton, selectFileButton;
    private SecretKey aesKey;
    private KeyPair rsaKeyPair;

    public EncryptionTool() {
        setTitle("Encryption Tool");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        messageField = new JTextField(20);
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        encryptAesButton = new JButton("Encrypt AES");
        decryptAesButton = new JButton("Decrypt AES");
        encryptRsaButton = new JButton("Encrypt RSA");
        decryptRsaButton = new JButton("Decrypt RSA");
        selectFileButton = new JButton("Select File to Encrypt");

        encryptAesButton.addActionListener(this);
        decryptAesButton.addActionListener(this);
        encryptRsaButton.addActionListener(this);
        decryptRsaButton.addActionListener(this);
        selectFileButton.addActionListener(this);

        add(new JLabel("Enter Message: "));
        add(messageField);
        add(encryptAesButton);
        add(decryptAesButton);
        add(encryptRsaButton);
        add(decryptRsaButton);
        add(selectFileButton);
        add(new JScrollPane(outputArea));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            if (e.getSource() == encryptAesButton) {
                aesKey = AESEncryption.generateKey();
                String message = messageField.getText();
                String encryptedMessage = AESEncryption.encrypt(message, aesKey);
                outputArea.append("AES Encrypted: " + encryptedMessage + "\n");
                FileUtil.saveKey(aesKey, new File("aesKey.key"));
            }

        } catch (Exception ex) {
            outputArea.append("Error: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        new EncryptionTool();
    }
}
