package webserver.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathTest {

    Path path;
    @Before
    public void setUp() throws Exception {
        path = new Path();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPath() {
        assertEquals("Expecting index.html", "..\\SVV-Project\\src\\main\\java\\webserver\\html\\index\\index.html", path.getPath("GET / HTTP/1.1"));
        assertEquals("Expecting style.css", "..\\SVV-Project\\src\\main\\java\\webserver\\html\\/style.css", path.getPath("GET /style.css HTTP/1.1"));
        assertEquals("Expecting null", null, path.getPath("POST / HTTP/1.1"));
        String error = "File doesn't exist";
        assertEquals("Expecting wrong string", "..\\SVV-Project\\src\\main\\java\\webserver\\html/FalsePositive", path.getPath("GET /FalsePositive HTTP/1.1"));
    }

}