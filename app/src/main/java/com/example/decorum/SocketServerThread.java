package com.example.decorum;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerThread extends Thread {
    static final int SOCKET_SERVER_PORT = 43770;
    private com.example.decorum.SampleCamActivity sampleCamActivity;
    private String jsonInput;

    public SocketServerThread(com.example.decorum.SampleCamActivity callingActivity) {
        sampleCamActivity = callingActivity;
    }
    private void getJSON() {
        jsonInput = sampleCamActivity.getJSON();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(SOCKET_SERVER_PORT);

            while(true) {
                getJSON();
                Socket socket = serverSocket.accept();
                com.example.decorum.SocketServerReplyThread socketServerReplyThread = new com.example.decorum.SocketServerReplyThread(socket, jsonInput);
                socketServerReplyThread.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
