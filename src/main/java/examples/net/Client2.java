package examples.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client2 {

    public static void main(String[] args){

        Client2 client = new Client2();
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
            out.writeObject("Client 2");

            while (true) {
                String s = in.readAllBytes().toString();
                if(s != null) {
                    System.out.println("Response ? " + s);
                    socket.close();
                    break;
                }
            }

        } catch (IOException e){
            System.out.println("Received Exception " + e.getMessage());
        } finally {
            System.out.println("Closing the socket connection");
            socket.close();
        }
    }
}
