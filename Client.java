import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Client {
    private static PrintWriter output;
    private static JTextArea chatArea;
    private static JTextField inputField;

    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to server!");

            output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // UI setup
            JFrame frame = new JFrame("Client Chat");
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridBagLayout());
            frame.getContentPane().setBackground(new Color(255, 239, 213));

            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setPreferredSize(new Dimension(500, 400));
            panel.setBackground(Color.white);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            chatArea = new JTextArea();
            chatArea.setEditable(false);
            chatArea.setFont(new Font("Arial", Font.PLAIN, 16));
            JScrollPane scrollPane = new JScrollPane(chatArea);

            inputField = new JTextField();
            JButton sendButton = new JButton("Send");
            sendButton.setBackground(new Color(255, 165, 0));
            sendButton.setForeground(Color.white);

            sendButton.addActionListener(e -> sendMessage());

            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(inputField, BorderLayout.SOUTH);
            panel.add(sendButton, BorderLayout.EAST);

            frame.add(panel);
            frame.setVisible(true);

            // Thread to receive messages from server
            new Thread(() -> {
                String msg;
                try {
                    while ((msg = input.readLine()) != null) {
                        chatArea.append("Server: " + msg + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }

    private static void sendMessage() {
        String text = inputField.getText();
        output.println(text);
        chatArea.append("You: " + text + "\n");
        inputField.setText("");
    }
}
