package go_back;

import java.net.*;
import java.io.*;

public class Client{
    static Socket connection;

    public static void main(String a[]) {
        try {
            int n = 5; // Number of frames to send
            int[] frames = {10, 20, 30, 40, 50}; // Frame values

            InetAddress addr = InetAddress.getByName("localhost");
            System.out.println(addr);
            connection = new Socket(addr, 8011);
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            DataInputStream in = new DataInputStream(connection.getInputStream());

            out.write(n); // Send the number of frames
            out.flush();

            for (int i = 0; i < n; i++) {
                out.write(frames[i]); // Send each frame
                out.flush();
            }

            int ack = in.read(); // Receive acknowledgment
            System.out.println("Received acknowledgment: " + ack);

            System.out.println("Closing connection");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
