/*
Kate Wooley
9/14/2020
Computer Networks - Iterative Socket Server
Client Side

Version 1.1
Incomplete


*/




import java.io.*;
import java.net.*;

class ClientConnection extends Thread {
    //private Socket socket;
    int end = 0;
    public void run() {
        try {
            InetAddress address = InetAddress.getByName("localhost");  
            Socket socket = new Socket(address, 6868);
            //get input from user
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            //send input from user to server
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            String userInput;
            userInput = reader.readLine();
            String userInputU = userInput.toUpperCase();
            if(userInput.contains("1") || userInputU.contains("DATE")) {
                writer.write("Date and Time");
            }
            else if(userInput.contains("2") || userInputU.contains("UPTIME")) {
                writer.write("Uptime");
            }
            else if(userInput.contains("3") || userInputU.contains("MEMORY")) {
                writer.write("Memory Use");
            }
            else if(userInput.contains("4") || userInputU.contains("NETSTAT")) {
                writer.write("Netstat");
            }
            else if(userInput.contains("5") || userInputU.contains("CURRENT") || userInputU.contains("USERS")) {
                writer.write("Current Users");
            }
            else if(userInput.contains("6") || userInputU.contains("RUNNING") || userInputU.contains("PROCESS")) {
                writer.write("Running Processes");
            }
            else if(userInput.contains("7") || userInput.contains("EXIT")) {
                writer.write("Exit");
                end = 1;
            }
            else
            {
                writer.write(userInput);
            }

            socket.close();
        } catch (Exception e) {
            
            e.printStackTrace();
        }

    }
}

public class JavaClient {

    public static void main(String[] args) {

        //menu
        boolean cont = true;
        do{
            System.out.println("1. Date and Time\t2. Uptime\t3. Memory Use\t4. Netstat\t5. Current Users\t6. Running Processes\t7. Exit");
            ClientConnection cc = new ClientConnection();
            if(cc.end == 1) {
                cont = false;
            }

        }while(cont);
        


        
    }
}
