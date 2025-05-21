package com.chat.client;

import java.awt.BorderLayout;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class ChatGUI extends WebSocketClient {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private String clientName;

    public ChatGUI(String name, URI serverUri) {
        super(serverUri);
        this.clientName = name;
        setupGUI();
        this.connect();
    }

    private void setupGUI() {
        frame = new JFrame("Cliente WebSocket - " + clientName);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        JButton sendButton = new JButton("Enviar");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);

        inputField.addActionListener(e -> sendMessage());
        sendButton.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        String msg = inputField.getText().trim();
        if (!msg.isEmpty() && this.isOpen()) {
            this.send(clientName + ": " + msg);
            chatArea.append("Cliente: " + msg + "\n");
            inputField.setText("");
        }
    }

    @Override
    public void conexionServidor (ServerHandshake handshakedata) {
        chatArea.append(" Conectado al servidor.\n");
    }

    @Override
    public void recibirMostrarMensaje(String message) {
        chatArea.append("Mensajito recibido" + message + "\n");
    }

    @Override
    public void cierreConexion(int code, String reason, boolean remote) {
        chatArea.append("Conexión cerrada.\n");
    }

    @Override
    public void error (Exception ex) {
        chatArea.append("⚠️ Error: " + ex.getMessage() + "\n");
        ex.printStackTrace();
    }
}
