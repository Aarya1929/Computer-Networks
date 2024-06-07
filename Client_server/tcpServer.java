package example;

//TCP Server Side :  Hello Client-Server Program

import java.io.*;
import java.net.*;

public class tcpServer
{
public static void main(String args[]) throws IOException
{
	 Thread t2 = new Thread(new Runnable()
	    {
	        public void run(){
	            try {
	            	ServerSocket ss = null;
	            	Socket socket =null;
	            			
	            	String message;
	            		
	            	ss = new ServerSocket(8001);
	            	System.out.println("Server socket is created and waiting for client");
	            		
	            	socket = ss.accept();
	            	DataOutputStream ostream = new DataOutputStream(socket.getOutputStream());
	            	DataInputStream istream = new DataInputStream(socket.getInputStream());
	            		
	            	if(socket.getTcpNoDelay()) socket.setTcpNoDelay(true);
	            	 // to disable Nagle's Algorithm

	            	message = istream.readUTF(); // read operation

	            	System.out.println("Client Says: "+message);

	            	ostream.writeUTF(message);   // write operation

	            	message = "Thanks";
	            	System.out.println("Reply to Client:" +message);

	            	socket.close();
	            	ostream.close();
	            	istream.close();
	            } catch(Exception e) {
	            }
	        }
	    });
	 t2.start();
}
}