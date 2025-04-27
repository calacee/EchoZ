package org.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable{
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private List<ClientHandler> clients;

    public ClientHandler(Socket psocket, List<ClientHandler> pclients) {
        this.socket = psocket;
        this.clients = pclients;

        try{
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch(IOException e){
            System.out.printf("Error in the server : %s", e.getMessage());
        }
    }

    @Override
    public void run(){
        try{
            String message;
            while ((message = input.readLine()) != null){
                System.out.printf("Received message: %s\n", message);
                for (ClientHandler client : clients) {
                    if(client != this)
                        client.output.println(message);
                }
            }
        } catch (IOException e){
            System.out.println("User disconected.");
        } finally {
            try{
                clients.remove(this);
                socket.close();
            } catch (IOException e){
                System.out.println("Error to close connection.");
            }
        }
    }
}
