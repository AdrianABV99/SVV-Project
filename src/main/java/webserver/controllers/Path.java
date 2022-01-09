package webserver.controllers;

public class Path {

    public static String getPath(String msg, String rootPath) {
        if (msg.length() == 0 || !msg.substring(0,3).equals("GET")){
            return null;
        }
        String path = msg.substring(msg.indexOf(' ')+1);
        path = path.substring(0,path.indexOf(' '));

        if(path.contains(".txt")){
            if(path.contains(" ")) {
                path = path.replace(" ", "%20");
            }
            return rootPath + path;
        }

        if(path.contains(".css")) {
            if(path.contains(" "))
                path = path.replace(" ", "%20");

            return rootPath + path;
        }


        if(path.contains(" "))
            path = path.replace(" ", "%20");
        if (path.equals(""))
            return rootPath + "index\\index.html";
        if (path.charAt(path.length()-1) == '/')
            return  rootPath + "index\\index.html";

        return  rootPath + path;
    }




}
