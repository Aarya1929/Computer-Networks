package go_back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerSocket serverSocket;
    static DataInputStream dis;
    static DataOutputStream dos;

    public static void main(String[] args) {
        try {
            int expectedFrames = 5; // Expected number of frames
            int[] receivedFrames = new int[expectedFrames]; // Array to store received frames

            serverSocket = new ServerSocket(8011);
            System.out.println("Waiting for connection");
            Socket client = serverSocket.accept();
            dis = new DataInputStream(client.getInputStream());
            dos = new DataOutputStream(client.getOutputStream());

            int n = dis.read(); // Receive the number of frames

            for (int i = 0; i < n; i++) {
                receivedFrames[i] = dis.read(); // Receive each frame
            }

            // Simulate acknowledgment (acknowledges the last correctly received frame)
            int ack = expectedFrames - 1;
            dos.write(ack); // Send acknowledgment
            dos.flush();

            System.out.println("Received frames:");
            for (int i = 0; i < n; i++) {
                System.out.println(receivedFrames[i]);
            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                dis.close();
                dos.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
