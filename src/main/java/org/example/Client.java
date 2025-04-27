package org.example;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try{
            int port = 12345;
            Socket socket = new Socket("localhost", port);
            BufferedReader message = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            new Thread(() -> {
                try{
                    String receivedMessage;
                    while ((receivedMessage = input.readLine()) != null){
                        System.out.printf(">> %s \n", receivedMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Finished conection.");
                }
            }).start();

            String userMessage;
            while((userMessage = message.readLine()) != null){
                output.println(userMessage);
            }
        }catch (IOException e){
            System.out.printf("Error: %s", e);
        }
    }
}
