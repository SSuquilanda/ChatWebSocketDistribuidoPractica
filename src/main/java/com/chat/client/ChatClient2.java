package com.chat.client;

import java.net.URI;

public class ChatClient2 {
    public static void main(String[] args) throws Exception {
        new ChatGUI("Cliente2", new URI("ws://localhost:8765"));
    }
}
