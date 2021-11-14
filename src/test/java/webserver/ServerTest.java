package webserver;

import org.junit.*;
import webserver.controllers.ErrorController;
import webserver.controllers.PathController;
import webserver.utility.ObjectFile;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ServerTest {

    private static ObjectFile objectFileMock = mock(ObjectFile.class);
    private static PathController pathControllerMock = mock(PathController.class);
    private static ErrorController errorControllerMock =mock(ErrorController.class);
    private static PrintStream out;
    private static Server webServer;

    @BeforeClass
    public static void setUp() throws Exception {
        ServerSocket serverSocket = new ServerSocket(10013);
        Socket clientSocket = serverSocket.accept();
        out = new PrintStream(clientSocket.getOutputStream());
        webServer = new Server(clientSocket);
        webServer.serverStatus = "RUN_SERVER";
    }

    @AfterClass
    public static void tearDown() throws Exception {

    }
    @Test
    public void TestRunMock() throws IOException {


        assertEquals("good path", "..\\SVV-Project\\src\\main\\java\\html\\index\\index.html", pathControllerMock.getPath("GET / HTTP/1.1"));
        String path = "..\\svv-project\\src\\main\\java\\html\\index\\index.html";
        File file = new File(path);
        assertEquals("Expected a good path for the file", file, objectFileMock.OpenFile(path));

        String error = "ERROR MESSAGE TEST";
        assertEquals("Expected an error output", "Message sent to:" + out + "with message" + error, errorControllerMock.ErrorHeader(out, error));

        assertEquals("Expected output to succeed when checking the file", "Message sent to:" + out + " the file" + file + "type: " + "text/html" + " length:" + (int) file.length(), objectFileMock.FileFoundHeader(out, (int) file.length(), file));
        //webServer.run();
    }


}