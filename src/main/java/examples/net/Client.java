package examples.net;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args){

        Client client = new Client();
        try {
            System.out.println("Attempting to establish connection with the server");
            Socket socket = new Socket("localhost", 1500);
            System.out.println("Socket created. Processing...");
            client.doProcessing(socket);
        } catch (IOException e) {

        }
    }

    protected void doProcessing(Socket socket) throws IOException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject("Client 1");

            while (true) {
                String s = (String) in.readObject();
                if(s != null) {
                    System.out.println("Response ? " + s);
                    socket.close();
                    break;
                }
            }

        } catch (IOException e){
            System.out.println("Received Exception " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Closing the socket connection");
            socket.close();
        }
    }
}
