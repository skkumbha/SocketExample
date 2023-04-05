package examples.processor;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MessageProcessor implements Runnable{

    private Socket socket;

    public MessageProcessor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
           // System.out.println(Thread.currentThread().getName() + ": I am going to sleep for 1 second");
          //  Thread.currentThread().sleep(5000);
            String threadName = Thread.currentThread().getName();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println(threadName + ":Waiting for message to be available in the stream");
            String s = (String) inputStream.readObject();
            System.out.println(threadName + ":Received Input message from client " + s);

            System.out.println(threadName + ":Sending Acknowledgement to the message");

            String output = new String("Thanks for the Message, I have received this " + s);
//            OutputStream ou = socket.getOutputStream();
//            ou.write(output.getBytes(StandardCharsets.UTF_8));
            outputStream.writeObject(output);
            outputStream.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
