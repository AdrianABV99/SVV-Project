package webserver;

import java.net.*;
import java.io.*;
import java.util.Scanner;

import webserver.controllers.ErrorController;
import webserver.controllers.PathController;
import webserver.utility.ObjectFile;



public class Server extends Thread {

    private Socket clientSocket;
    public static String serverStatus = "STOP_SERVER";
    private ErrorController errorController = new ErrorController();
    private PathController pathController = new PathController();
    private ObjectFile objectFile = new ObjectFile();

    public Server(Socket clientSocket) {

        this.clientSocket = clientSocket;
        if (serverStatus.equals("EXIT")) {
            System.exit(-1);
        }
        if (serverStatus.equals("RUN_SERVER"))
        {
            start();
        }
        if (serverStatus.equals("MAINTENANCE_SERVER")) {
            MaintenanceServer();
        }
        if (serverStatus.equals("STOP_SERVER")) {
            //StopServer();
        }
    }


    public void run() {

        try {
            DataInputStream in;
            PrintStream out = new PrintStream(clientSocket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String path;
            if ((path = pathController.getPath(is.readLine())) != null) {
                File file = objectFile.OpenFile(path);
                if (file.exists()) {
                    try {
                        in = new DataInputStream(new FileInputStream(file));
                        objectFile.FileFoundHeader(out, (int) file.length(), file);
                        objectFile.SendReply(out, in, (int) file.length());
                    } catch (Exception e) {
                        errorController.ErrorHeader(out, "Can't Read " + path);
                    }
                    out.flush();
                } else
                    errorController.ErrorHeader(out, "Not Found " + path);
            }
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Problem with Communication Server");
            System.exit(1);
        }
        }


    public static void InitServer() {

        System.out.println("Enter SERVER STATUS:\t0: STOP\t1: MAINTENANCE\t2: RUN\t9: EXIT\n");
        System.out.println("CURRENT SERVER STATUS: " + serverStatus);
        Scanner myObj = new Scanner(System.in);
        if(myObj.nextLine().equals("1"))
        {
            serverStatus = "STOP_SERVER";
        }
        if(myObj.nextLine().equals("2")) {
            serverStatus = "MAINTENANCE_SERVER";
        }
        if(myObj.nextLine().equals("3")) {
            serverStatus = "RUN_SERVER";
        }
        if(myObj.nextLine().equals("0")) {
            serverStatus = "EXIT";
        }
        System.out.println("\n\tNEW CURRENT SERVER STATUS: " + serverStatus + "\n");

        if(!serverStatus.equals("EXIT")) {
            InitServer();
        }
    }



    public void MaintenanceServer() {
        try {
            DataInputStream in;
            PrintStream out = new PrintStream(clientSocket.getOutputStream());
            File file = objectFile.OpenFile("..\\SVV-Project\\src\\main\\java\\html\\maintenance\\index.html");
            try {
                in = new DataInputStream(new FileInputStream(file));
                objectFile.FileFoundHeader(out, (int) file.length(), file);
                objectFile.SendReply(out, in, (int) file.length());
            } catch (Exception e) {
                errorController.ErrorHeader(out, "Can't read Maintenance html file");
            }
            out.flush();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Problem with Communication Server");
            System.exit(1);
        }
    }




}
