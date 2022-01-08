package webserver;

import java.net.*;
import java.io.*;
import java.util.Scanner;

import webserver.controllers.Error;
import webserver.controllers.Path;
import webserver.utility.FileHandler;



public class Server extends Thread {

    private Socket clientSocket;
    public static String serverStatus = "STOP_SERVER";
    private Error error = new Error();
    private Path path = new Path();
    private FileHandler fileHandler = new FileHandler();

    public Server(Socket clientSocket) {

        this.clientSocket = clientSocket;

        switch (serverStatus) {
            case "EXIT":
                System.exit(-1);
                break;
            case "RUN_SERVER":
                start();
                break;

            case "MAINTENANCE_SERVER":
                MaintenanceServer();
                break;

            case "STOP_SERVER":
                //StopServer();
                break;
            default:
                break;
            }


    }

    public static boolean InitServer() {

        System.out.println("Enter SERVER STATUS:\t1: STOP\t2: MAINTENANCE\t3: RUN\t0: EXIT\n");
        System.out.println("CURRENT SERVER STATUS: " + serverStatus);
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {
            case "1":
                serverStatus = "STOP_SERVER";
                break;

            case "2":
                serverStatus = "MAINTENANCE_SERVER";
                break;

            case "3":
                serverStatus = "RUN_SERVER";
                break;

            case "0":
                serverStatus = "EXIT";
                break;
            default:
                break;
        }


        System.out.println("\n\tNEW CURRENT SERVER STATUS: " + serverStatus + "\n");

        if(!serverStatus.equals("EXIT")) {
            InitServer();
        }
        return false;
    }


    public void run() {

        PrintStream out = null;
        BufferedReader is = null;
        DataInputStream in = null;
        try {

            out = new PrintStream(clientSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String path;
            if ((path = this.path.getPath(is.readLine())) != null) {
                getFile(out,path);
            }
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Problem with Communication Server");
            System.exit(1);
        }
    }



    public String getFile(PrintStream out, String path ) {
        File file = fileHandler.OpenFile(path);
        if (file.exists()) {
            try {
            DataInputStream in = new DataInputStream(new FileInputStream(file));
                fileHandler.FileFoundHeader(out, (int) file.length(), file);
                fileHandler.SendReply(out, in, (int) file.length());
                String ot = out.toString();
                out.flush();
                return  ot + "successful reading" + path;
            } catch (Exception e) {
                error.ErrorHeader(out, "Can't Read " + path);
                String ot = out.toString();
                out.flush();
                return ot + "Can't Read " + path;
            }
        }  else
            error.ErrorHeader(out, "Not Found " + path);
            String ot = out.toString();
            out.flush();
            return ot + "Not Found " + path;
    }

    public void MaintenanceServer() {
        try {
            DataInputStream in;
            PrintStream out = new PrintStream(clientSocket.getOutputStream());
            File file = fileHandler.OpenFile("..\\SVV-Project\\src\\main\\java\\html\\maintenance\\index.html");
            try {
                in = new DataInputStream(new FileInputStream(file));
                fileHandler.FileFoundHeader(out, (int) file.length(), file);
                fileHandler.SendReply(out, in, (int) file.length());
            } catch (Exception e) {
                error.ErrorHeader(out, "Can't read Maintenance html file");
            }
            out.flush();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Problem with Communication Server");
            System.exit(1);
        }
    }




}
