package com.chat.client;

import java.net.URI;

public class ChatClient1 {
    public static void main(String[] args) throws Exception {
        new ChatGUI("Cliente1", new URI("ws://localhost:8765"));
    }
}
