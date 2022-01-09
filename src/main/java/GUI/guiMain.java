package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import webserver.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class guiMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static String serverAddres = "http://localhost:";
    public static int serverListeningPort = 10008;
    public static String serverRoot = "..\\SVV-project\\src\\main\\java\\webserver\\html\\";
    public static String serverMaintenance = "..\\svv-project\\src\\main\\java\\webserver\\html\\maintenance\\index.html";
    public static ServerSocket serverSocketGUI = null;
    public static Server webServerGUI = null;

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/SvvProject.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void server() {
        if(webServerGUI != null) {
            webServerGUI.serverStatus = "STOP_SERVER";
            return;
        }

        webServerGUI.serverStatus = "RUN_SERVER";
        Thread startServer= new Thread(Server:: InitServer);
        startServer.start();
        Thread server = new Thread(() -> {
            try {
                while (true) {
                    if(!webServerGUI.serverStatus.equals("STOP_SERVER")) {
                        serverSocketGUI = new ServerSocket(serverListeningPort);
                        try {
                            while (!webServerGUI.serverStatus.equals("STOP_SERVER")) {
                                System.out.println("Waiting for Connection");
                                new Server(serverSocketGUI.accept(), serverRoot, serverMaintenance);
                            }
                            System.out.println(serverListeningPort);
                        } catch (IOException e) {
                            System.err.println("Accept failed.");
                        }
                        System.out.println("After while");
                    }
                    System.out.println(serverListeningPort + " " + webServerGUI.serverStatus);
                }
            } catch (IOException e) {
                System.err.println("Could not listen on port: 10008.");
            } finally {
                try {
                    serverSocketGUI.close();
                } catch (IOException e) {
                    System.err.println("Could not close port: 10008.");
                }
            }
        });

        server.start();
    }

}