package webserver.utility;

import java.io.DataInputStream;
import java.io.File;
import java.io.PrintStream;

public class FileHandler {

    public static String FileFoundHeader(PrintStream out, int fileLength, File file) {

        String contentType = CheckFile(file.toString());
        out.println("HTTP:/1.0 200 OK");
        out.println("type:" +  contentType);
        out.println("length: "+fileLength);
        out.println();

        if (contentType == null) {
            return "Error when checking the file";
        }
        return "Message sent to:" + out + " the file" + file + "type: " + contentType + " length:" + fileLength;

    }


    public static File OpenFile(String filename)
    {
        File file = new File(filename);
        if (file.exists()) return file;
        if (filename.charAt(0) != '/') return file;
        return new File(filename.substring(1));
    }

    public static String SendReply(PrintStream out, DataInputStream in, int flen)
    {
        try
        {
            byte[] buffer = new byte[flen];
            in.read(buffer);
            out.write(buffer, 0, flen);
            in.close();
        }
        catch (Exception e)  {
            System.out.println(e);
            return "Got an error when sending a reply to " + out;
        }
        return "Successfully sending the reply " + out;
    }

    private static String CheckFile(String fileExtension) {

        if(fileExtension.contains(".css")) {
            return "text/css";
        }
        if(fileExtension.contains(".html")) {
            return "text/html";
        }
        return null;
    }

}
