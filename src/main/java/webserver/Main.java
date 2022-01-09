package webserver;

import webserver.Server;

import java.net.*;
import java.io.*;

import static webserver.Server.InitServer;


public class Main extends Thread {
    protected Socket clientSocket;
    public static String serverRoot = "..\\svv-project\\src\\main\\java\\webserver\\html\\";
    public static String serverMaintenance = "..\\svv-project\\src\\main\\java\\webserver\\html\\maintenance\\index.html";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;


        Server webServer = null;
        Thread startServer=new Thread() {
            public void run() {
                InitServer();
            }
        };
        startServer.start();

        try {
            serverSocket = new ServerSocket(10008);
            try {
                while (true) {
                    System.out.println("Waiting for Connection");
                    new Server(serverSocket.accept(),serverRoot,serverMaintenance);
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 10008.");
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Could not close port: 10008.");
            }
        }
    }



}