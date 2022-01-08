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

    private  FileHandler fileHandlerMock = mock(FileHandler.class);
    private  Path pathMock = mock(Path.class);
    private  Error errorMock =mock(Error.class);
    private  PrintStream  out;
    private  Server webServer;

    @Before
    public  void setUp() throws Exception {
    }

    @After
    public  void tearDown() throws Exception {

    }

    @Test
    public void getFile() throws FileNotFoundException, IOException {
        ServerSocket serverSocket = new ServerSocket(10020);
        Socket clientSocket = serverSocket.accept();
        webServer = new Server(clientSocket);
        out = new PrintStream(clientSocket.getOutputStream());
        String goodPath = "..\\SVV-Project\\src\\main\\java\\webserver\\html\\index\\index.html";
        String badPath =  "..\\SVV-Project\\src\\main\\java\\webserver\\html\\index\\notHere.html";
        File file = new File(goodPath);
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        assertEquals("get index file", "Message sent to:" + out + " the file" + file + "type: " + "text/html" + " length:" + (int) file.length(), fileHandlerMock.FileFoundHeader(out,(int) file.length(),file));
        assertEquals("replay succes","Successfully sending the reply " + out,fileHandlerMock.SendReply(out, in, (int) file.length()));
        file = new File(badPath);
        String error = "File doesn't exist";
        assertEquals("failed to get the file","Message sent to:" + out + "with message" + "Not Found " + badPath,errorMock.ErrorHeader(out,"Not Found " + badPath));

        assertEquals("successful reading",out + "successful reading" + goodPath, webServer.getFile(out , goodPath));
        assertEquals("Not Found",out + "Not Found " + badPath,webServer.getFile(out,badPath));
    }

   @Test
   public void  InitServer() throws IOException{
        ServerSocket serverSocket = new ServerSocket(10021);
        Socket clientSocket = serverSocket.accept();
        webServer = new Server(clientSocket);
        webServer.serverStatus = "EXIT";
        webServer.InitServer();

}

    @Test
    public void TestMaintenanceServerMock() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10019);
        Socket clientSocket = serverSocket.accept();
        webServer = new Server(clientSocket);
        String path = "..\\svv-project\\src\\main\\java\\webserver\\html\\maintenance\\index.html";
        File file = new File(path);
        assertEquals("good path", file, fileHandlerMock.OpenFile(path));
        out = new PrintStream(clientSocket.getOutputStream());
        String error = "ERROR MESSAGE TEST";
        assertEquals("error output", "Message sent to:" + out + "with message" + error, errorMock.ErrorHeader(out, error));
        assertEquals("succes when checking the file", "Message sent to:" + out + " the file" + file + "type: " + "text/html" + " length:" + (int)file.length(), fileHandlerMock.FileFoundHeader(out, (int) file.length(), file));

        webServer.MaintenanceServer();
    }


    @Test
    public void TestRunMock() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10016);
        Socket clientSocket = serverSocket.accept();
        webServer = new Server(clientSocket);
        out = new PrintStream(clientSocket.getOutputStream());
        System.out.println("its ok");
        assertEquals("Expected a good path", "..\\SVV-Project\\src\\main\\java\\webserver\\html\\index\\index.html", pathMock.getPath("GET / HTTP/1.1"));
        String path = "..\\SVV-Project\\src\\main\\java\\webserver\\html\\maintenance\\index.html";
        File file = new File(path);
        assertEquals("Expected a good path for the file", file, fileHandlerMock.OpenFile(path));
        String errMessage = "ERROR MESSAGE TEST";
        PrintStream os = new PrintStream(clientSocket.getOutputStream());
        System.out.println(os);
        assertEquals("Expected an error output", "Message sent to:" + os + "with message" + errMessage, errorMock.ErrorHeader(os, errMessage));
        assertEquals(" succeesful when checking the file","Message sent to:" + out + " the file" + file + "type: " + "text/html" + " length:" + + (int) file.length(), fileHandlerMock.FileFoundHeader(out, (int) file.length(), file));

        webServer.run();
    }

}