package webserver;

import org.junit.*;
import webserver.controllers.Error;
import webserver.controllers.Path;
import webserver.utility.FileHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ServerTest {

    private static FileHandler fileHandlerMock = mock(FileHandler.class);
    private static Path pathMock = mock(Path.class);
    private static Error errorMock =mock(Error.class);
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


        assertEquals("good path", "..\\SVV-Project\\src\\main\\java\\webserver\\html\\index\\index.html", pathMock.getPath("GET / HTTP/1.1"));
        String path = "..\\svv-project\\src\\main\\java\\webserver\\html\\index\\index.html";
        File file = new File(path);
        assertEquals("Expected a good path for the file", file, fileHandlerMock.OpenFile(path));
        String error = "ERROR MESSAGE TEST";
        assertEquals("Expected an error output", "Message sent to:" + out + "with message" + error, errorMock.ErrorHeader(out, error));
        assertEquals("Expected output to succeed when checking the file", "Message sent to:" + out + " the file" + file + "type: " + "text/html" + " length:" + (int) file.length(), fileHandlerMock.FileFoundHeader(out, (int) file.length(), file));
    }

    @Test
    public void getFile() throws FileNotFoundException {
        String goodPath = "..\\SVV-Project\\src\\main\\java\\webserver\\html\\index\\index.html";
        String badPath =  "..\\SVV-Project\\src\\main\\java\\webserver\\html\\index\\notHere.html";
        File file = new File(goodPath);
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        assertEquals("get index file", "Message sent to:" + out + " the file" + file + "type: " + "text/html" + " length:" + (int) file.length(), fileHandlerMock.FileFoundHeader(out,(int) file.length(),file));
        assertEquals("replay succes","Successfully sending the reply " + out,fileHandlerMock.SendReply(out, in, (int) file.length()));
        file = new File(badPath);
        String error = "File doesn't exist";
        assertEquals("failed to get the file","Message sent to:" + out + "with message" + "Not Found " + badPath,errorMock.ErrorHeader(out,"Not Found " + badPath));
    }

    @Test
    public void TestMaintenanceServerMock() throws IOException {
        String path = "..\\svv-project\\src\\main\\java\\webserver\\html\\maintenance\\index.html";
        File file = new File(path);
        assertEquals("good path", file, fileHandlerMock.OpenFile(path));

        String error = "ERROR MESSAGE TEST";
        assertEquals("error output", "Message sent to:" + out + "with message" + error, errorMock.ErrorHeader(out, error));

        assertEquals("succes when checking the file", "Message sent to:" + out + " the file" + file + "type: " + "text/html" + " length:" + (int)file.length(), fileHandlerMock.FileFoundHeader(out, (int) file.length(), file));


    }



}