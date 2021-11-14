package webserver.utility;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class FileHandlerTest {

    private FileHandler fileHandler;
    private int fileLength;
    @Before
    public void setUp() {
        fileHandler = new FileHandler();
        fileLength = 42;
    }

    @Test
    public void openFile() {
        assertEquals("Expected result a new file", fileHandler.OpenFile("NewlyCreatedFile"), new File("NewlyCreatedFile"));

    }


    @Test
    public void fileFoundHeader() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10009);
        Socket clientSocket = serverSocket.accept();
        PrintStream out = new PrintStream(clientSocket.getOutputStream());
        System.out.println("OPEN BROWSER: http://localhost:10009/");

        File file = new File("..\\SVV-Project\\src\\main\\java\\html\\index\\index.html");
        assertEquals("Expected output to succeed the check","Message sent to:" + out + " the file" + file + "type: " + "text/html" + " length:" + fileLength, fileHandler.FileFoundHeader(out, fileLength, file));

        file = new File("..\\SVV-Project\\src\\main\\java\\html\\index\\style.css");
        assertEquals("Expected output to succeed when checking the file","Message sent to:" + out + " the file" + file + "type: " + "text/css" + " length:" + fileLength, fileHandler.FileFoundHeader(out, fileLength, file));
        serverSocket.close();
        clientSocket.close();

    }

    @Test
    public void sendReply() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10011);
        Socket clientSocket = serverSocket.accept();
        PrintStream out = new PrintStream(clientSocket.getOutputStream());
        System.out.println("OPEN BROWSER: http://localhost:10011/");

        File file = new File("..\\SVV-Project\\src\\main\\java\\webserver\\html\\index\\index.html");
        assertEquals("Expected output: ", "Message sent to:" + out + " the file" + file + "type: " + "text/html" + " length:" + fileLength, fileHandler.FileFoundHeader(out, fileLength, file));
        //"Message sent to:" + out + " the file" + file + "type: " + "text/html" + " length:" + fileLength
        DataInputStream in = new DataInputStream(new FileInputStream(new File("..\\SVV-Project\\src\\main\\java\\webserver\\html\\index\\index.html")));
        assertEquals("Expected output to succeed ", "Successfully sending the reply " + out, fileHandler.SendReply(out, in, (int) new File("..\\SVV-Project\\src\\main\\java\\html\\index\\index.html").length()));
        assertEquals("Expected output to fail ", "Got an error when sending a reply to " + out, fileHandler.SendReply(out, in, -30));
        serverSocket.close();
        clientSocket.close();
    }

}