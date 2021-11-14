package webserver.controllers;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.mock;

public class ErrorTest {


    private Error error;
    @Before
    public void setUp() {
        error = new Error();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void ErrorHeader() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10017);
        Socket clientSocket = serverSocket.accept();
        PrintStream out = new PrintStream(clientSocket.getOutputStream());
        System.out.println("OPEN BROWSER: http://localhost:10017/");
        String error = "ERR TEST MSG";
        assertEquals("Expected this output: ", "Message sent to:" + out + "with message" + error, this.error.ErrorHeader(out, error));
        assertNotNull(this.error);
        serverSocket.close();
        clientSocket.close();
    }


}