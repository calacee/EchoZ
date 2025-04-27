package org.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args){
        try {
            int port = 12345;
            ServerSocket server = new ServerSocket(port);
            System.out.printf("Server started in port: %d...\n", port);
            while (true) {
                Socket socketClient = server.accept();
                System.out.printf("New user connected : %s\n", socketClient);
                ClientHandler handler = new ClientHandler(socketClient, clients);
                clients.add(handler);
                new Thread(handler).start();
            }
        }catch (IOException e){
            System.out.printf("Error in server : %s", e.getMessage());
        }
    }

}
