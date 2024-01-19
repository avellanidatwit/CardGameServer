package files;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * This class implements the java socket client
 * @author Pankaj
 *
 */
public class JavaClientExample {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if the server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
        while(true){
            //establish a socket connection to the server
            socket = new Socket(host.getHostName(), 3333);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            
            Scanner scanner = new Scanner(System.in);
            String message, response;
            
            while (true) {
	            System.out.print("Input message: ");
	            message = scanner.nextLine();
	            
	            System.out.println("Sending request to Socket Server");
	            oos.writeObject(message);
	            
	            //read the server response message
	            
	            response = (String) ois.readObject();
	            System.out.println(response);
	            
	            //close resources
	            if(message.equals("exit")) {
	            	ois.close();
	                oos.close();
	            	break;}
        	}
            if(message.equals("exit")) {
            	break;}
    	}
    }
}