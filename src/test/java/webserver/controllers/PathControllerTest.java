package webserver.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathControllerTest {

    PathController pathController;
    @Before
    public void setUp() throws Exception {
        pathController = new PathController();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPath() {
        assertEquals("Expecting index.html", "..\\SVV-Project\\src\\main\\java\\html\\index\\index.html", pathController.getPath("GET / HTTP/1.1"));
        assertEquals("Expecting style.css", "..\\SVV-Project\\src\\main\\java\\html\\/style.css", pathController.getPath("GET /style.css HTTP/1.1"));
        assertEquals("Expecting null", null, pathController.getPath("POST / HTTP/1.1"));
    }

}