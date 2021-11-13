package webserver.controllers;

import java.io.DataInputStream;
import java.io.File;
import java.io.PrintStream;

public class ErrorController {
        public static String ErrorHeader(PrintStream out, String error)
        {
            out.println("HTTP:/1.0 404 Not Found");
            out.println("Content-type: text/html");
            out.println("Content-length: "+error.length());
            out.println("\n");
            out.println(error);
            return "Message sent to:" + out + "with message" + error;
        }
}
