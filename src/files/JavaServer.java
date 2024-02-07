package files;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * This class implements java Socket server
 * @author dominic
 *
 */
public class JavaServer {
    
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 3333;
    
    private static int number = 0;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while(true){
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            
            String[] message;
            
            while (true) {
            	//convert ObjectInputStream object to String
            	message = ((String) ois.readObject()).split(" ");
            	//System.out.println("Message Received: " + message);
            	if (message[0].equals("add")) {
            		number +=  Integer.valueOf(message[1]);
            		oos.writeObject("Number: "+number);
            	}
            	
            	//terminate the server if client sends exit request
            	if(message[0].equals("exit")) {
            		ois.close();
            		oos.close();
            		socket.close();
            		break;}
            }
            if(message[0].equals("exit")) {
        		break;}
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }
    
}