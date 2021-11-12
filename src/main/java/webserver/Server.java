package webserver;

import java.net.*;
import java.io.*;



public class Server extends Thread {

    private Socket clientSocket;

    public Server(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    public void run() {

        try {
            DataInputStream in;
            PrintStream out = new PrintStream(clientSocket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String path;

            clientSocket.close();

        } catch (IOException e) {
            System.err.println("Communication problem with the server");
            System.exit(1);
        }
    }
}
