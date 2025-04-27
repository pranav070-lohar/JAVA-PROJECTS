import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Server {
    private static PrintWriter output;
    private static JTextArea chatArea;
    private static JTextField inputField;

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started, waiting for client connection...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // UI setup
            JFrame frame = new JFrame("Server Chat");
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridBagLayout());
            frame.getContentPane().setBackground(new Color(240, 255, 255));

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
            sendButton.setBackground(new Color(60, 179, 113));
            sendButton.setForeground(Color.white);

            sendButton.addActionListener(e -> sendMessage());

            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(inputField, BorderLayout.SOUTH);
            panel.add(sendButton, BorderLayout.EAST);

            frame.add(panel);
            frame.setVisible(true);

            // Thread to receive messages from client
            new Thread(() -> {
                String msg;
                try {
                    while ((msg = input.readLine()) != null) {
                        chatArea.append("Client: " + msg + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Keep the server running to accept and read from the client
            // The server should remain open for communication until manually closed.
            // No need to close the socket right now; let it stay open for communication.

        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    private static void sendMessage() {
        String text = inputField.getText();
        output.println(text);
        chatArea.append("You: " + text + "\n");
        inputField.setText("");
    }
}
