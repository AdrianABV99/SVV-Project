package webserver.controllers;

import java.io.DataInputStream;
import java.io.File;
import java.io.PrintStream;

public class Error {
        public static String ErrorHeader(PrintStream out, String error)
        {
            out.println("HTTP:/1.0 404 Not Found");
            out.println("type: text/html");
            out.println("length: "+error.length());
            out.println();
            out.println(error);
            return "Message sent to:" + out + "with message" + error;
        }
}
