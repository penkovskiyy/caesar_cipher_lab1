import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CaesarCipher extends JFrame
{
    private static JTextField inputKeyTF;

    private static JTextArea outputTA, inputTA;

    private JLabel headingL, encryptKeyL;

    private JScrollPane scrollText, scrollText2;

    private JButton encryptB,decryptB;

    private EncryptButtonHandler encrbHandler;
    private DecryptButtonHandler decryptHandler;

    private static int WIDTH = 700;
    private static int HEIGHT = 530;

    public CaesarCipher()
    {
        headingL = new JLabel("Caesar cipher",SwingConstants.CENTER);
        encryptKeyL  = new JLabel("Key");

        inputTA = new  JTextArea("",50,50);

        scrollText2 = new JScrollPane(inputTA);//adds the scroll feature to the outputTA
        scrollText2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        inputTA.setLineWrap(true);
        inputTA.setWrapStyleWord(true);

        inputKeyTF = new JTextField(3);

        outputTA = new JTextArea("",50,50);
        outputTA.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        scrollText = new JScrollPane(outputTA);
        outputTA.setLineWrap(true);
        outputTA.setWrapStyleWord(false);
        outputTA.setEditable(false);

        encryptB = new JButton("Encrypt");
        encrbHandler = new EncryptButtonHandler();
        encryptB.addActionListener(encrbHandler);

        decryptB = new JButton("Decrypt");
        decryptHandler = new DecryptButtonHandler();
        decryptB.addActionListener(decryptHandler);

        setTitle("The Caesar Cipher");

        Container myWindow = getContentPane();
        myWindow.setLayout(null);

        decryptB.setLocation(450, 232);
        encryptB.setLocation(200, 232);

        scrollText2.setLocation(5,52);
        inputKeyTF.setLocation(100,233);
        scrollText.setLocation(5,273);

        decryptB.setSize(200,40);
        encryptB.setSize(200,40);

        headingL.setSize(700,40);
        headingL.setLocation(5,5);
        encryptKeyL.setSize(430,40);
        encryptKeyL.setLocation(50,233);

        scrollText2.setSize(1010,180);
        inputKeyTF.setSize(60,40);
        scrollText.setSize(1010,180);

        myWindow.add(decryptB);
        myWindow.add(encryptB);
        myWindow.add(headingL);
        myWindow.add(encryptKeyL);
        myWindow.add(scrollText2);
        myWindow.add(inputKeyTF);
        myWindow.add(scrollText);

        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class EncryptButtonHandler implements ActionListener
    {
        public  void actionPerformed(ActionEvent e)
        {
            String sentence;
            int key;

            sentence = inputTA.getText();
            sentence = sentence.toLowerCase();
            sentence = sentence.replaceAll("[^a-zA-Z]+","");
            try
            {
                key = Integer.parseInt(inputKeyTF.getText());
                outputTA.setText("");

                if((key >=-25) &&(key<= 25))
                {
                    char[] chars = sentence.toCharArray();

                    for(int i = 0; i < sentence.length(); i++)
                    {
                        char c = chars[i];
                        char encrypted = encrypt(c, key);
                        String output = Character.toString(encrypted);

                        output.split("\\s");
                        String[] outputArray = {output};

                        for (int index=0; index < outputArray.length; index++)
                        {
                            outputTA.append(outputArray[index]);
                        }
                    }
                }

                else
                {
                    outputTA.setText("Enter the key");
                    inputKeyTF.setText("");
                    inputKeyTF.requestFocusInWindow();

                    key = Integer.parseInt(inputKeyTF.getText());


                }
            }
            catch (NumberFormatException h)
            {
                outputTA.setText("Enter the key");
                inputKeyTF.requestFocusInWindow();
            }
            catch (Exception j)
            {
                outputTA.setText("Enter the key");
                inputKeyTF.requestFocusInWindow();
            }
        }
    }

    public static char encrypt(char c, int key)
    {
        char[] alphabet = new char[26];
        int i = 0;

        for(char ch = 'a'; ch <= 'z'; ++ch)
        {
            alphabet[ch-'a']=ch;
        }

        while (i < 26)
        {
            if (c == alphabet[i])
                return alphabet[(i + key + 26)%26];
            i++;
        }
        return c;
    }
    private class DecryptButtonHandler implements ActionListener
    {
        public  void actionPerformed(ActionEvent e)
        {
            String sentence;
            int key;

            sentence = inputTA.getText();

            try
            {
                key = Integer.parseInt(inputKeyTF.getText());

                outputTA.setText("");

                if((key >=-25) &&(key<= 25))
                {
                    char[] chars = sentence.toCharArray();

                    for(int i = 0; i < sentence.length(); i++)
                    {
                        char c = chars[i];
                        char decrypted = decrypt(c, key);
                        String output = Character.toString(decrypted);

                        outputTA.append(output);//Decrypted Output
                    }
                }
                else
                {
                    outputTA.setText("Enter the key");
                    inputKeyTF.setText("");
                    inputKeyTF.requestFocusInWindow();

                    key = Integer.parseInt(inputKeyTF.getText());


                }
            }
            catch (NumberFormatException h)
            {
                outputTA.setText("Enter the key");
                inputKeyTF.requestFocusInWindow();
            }
            catch (Exception j)
            {
                outputTA.setText("Enter the key");
                inputKeyTF.requestFocusInWindow();
            }
        }
    }

    public static char decrypt(char c, int key)
    {
        char[] alphabet = new char[26];
        int i = 0;

        for(char ch = 'a'; ch <= 'z'; ++ch)
        {
            alphabet[ch-'a']=ch;//fills the array with the alphabet
        }
        while (i < 26)
        {
            if (c == alphabet[i])
                return alphabet[(i - key + 26)%26];
            i++;
        }
        return c;
    }

    public static void main(String[] args)
    {
        CaesarCipher cipherObject = new CaesarCipher();
        cipherObject.setLocationRelativeTo(null);
    }
}