package org.riddle.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.riddle.model.RiddleService;
import org.riddle.view.TemplateEngine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/riddle")
public class RiddleController extends HttpServlet   {

    private final static RiddleService service = RiddleService.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String solution = service.getRandomWord2();
        String riddle = service.getRiddle(solution);
        req.setAttribute("solution", solution);
        req.setAttribute("riddle", riddle);

        resp.setHeader("solution", solution);
        TemplateEngine.process("index.html", req, resp);
    }
}
