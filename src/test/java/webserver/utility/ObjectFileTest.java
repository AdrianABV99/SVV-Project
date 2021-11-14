package webserver.utility;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.*;


public class ObjectFileTest {

    private ObjectFile objectFile;
    private int fileLength;
    @Before
    public void setUp() {
        objectFile = new ObjectFile();
        fileLength = 42;
    }

    @Test
    public void openFile() {
        assertEquals("Expected result a new file",objectFile.OpenFile("NewlyCreatedFile"), new File("NewlyCreatedFile"));

    }


    @Test
    public void fileFoundHeader() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10009);
        Socket clientSocket = serverSocket.accept();
        PrintStream out = new PrintStream(clientSocket.getOutputStream());
        System.out.println("OPEN BROWSER: http://localhost:10009/");

        File file = new File("..\\SVV-Project\\src\\main\\java\\html\\index\\index.html");
        assertEquals("Expected output to succeed the check","Message sent to:" + out + " the file" + file + "type: " + "text/html" + " length:" + fileLength, objectFile.FileFoundHeader(out, fileLength, file));

        file = new File("..\\SVV-Project\\src\\main\\java\\html\\index\\style.css");
        assertEquals("Expected output to succeed when checking the file","Message sent to:" + out + " the file" + file + "type: " + "text/css" + " length:" + fileLength, objectFile.FileFoundHeader(out, fileLength, file));

    }

}