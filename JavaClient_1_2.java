/*
Kate Wooley
9/21/2020
Computer Networks - Iterative Socket Server
Client Side

Version 1.2
Incomplete


*/




import java.io.*;
import java.net.*;



public class JavaClient {

    public static void main(String[] args) {

        //menu
        try {
            InetAddress address = InetAddress.getByName("localhost");  
            Socket socket = new Socket(address, 6868);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String userCmd = "";
            while(!userCmd.toUpperCase().contains("7") && !userCmd.toUpperCase().contains("EXIT"))
            {
                System.out.println("1. Date and Time\t2. Uptime\t3. Memory Use\t4. Netstat\t5. Current Users\t6. Running Processes\t7. Exit");
                //save user command
                userCmd = reader.readLine();
                int userRNum = 1;
                if(!userCmd.toUpperCase().contains("7") && !userCmd.toUpperCase().contains("EXIT"))
                {
                    System.out.println("How many requests?");
                    //convert str to int
                    userRNum = Integer.parseInt(reader.readLine()); //add thrown exceptions, IOexception, NumberFormatException
                }
                
                int count = 0;
                while(count < userRNum)
                {
                    //ClientConnection cc = new ClientConnection(socket, userCmd);
                    new ClientConnection(socket, userCmd).start();
                    //send data to server
                    //get data from server
                    System.out.println("Count: " + count);
                    count++;
                }


                
            }
            
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            

        


        
    }
}


class ClientConnection extends Thread {
    private Socket socket;
    private String userInput;

    public ClientConnection(Socket socket, String userCmd)
    {
        this.socket = socket;
        this.userInput = userCmd;
    }
    
    public void run() {
        try {
 
            //send input from user to server
            System.out.println("New thread");
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
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
            }
            else
            {
                writer.write(userInput);
            }

        } catch (Exception e) {
            
            e.printStackTrace();
        }

    }
}
