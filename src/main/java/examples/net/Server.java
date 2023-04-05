package examples.net;

import examples.processor.MessageProcessor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String args[]){
        Server server = new Server();

        try {
            ServerSocket ss = new ServerSocket(1500);

            System.out.println("Socket created. Listening on port 1500 ....");

            boolean loop = true;
            while(loop) {
                System.out.println(Thread.currentThread().getName() + ": waiting for new connection");
                Socket socket = ss.accept();
                System.out.println("Received connection from a socket");
                Thread t = new Thread(new MessageProcessor(socket));
                System.out.println(Thread.currentThread().getName() + ": I am going to process message on thread " + t.getName());
                t.start();

                //t.start();
            }
        } catch(IOException e){
            System.out.print(Thread.currentThread().getName() + ": Received exception " + e);
        }
        System.out.println(Thread.currentThread().getName() + ": I am done, closing the main thread");
    }
}
