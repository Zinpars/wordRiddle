package org.riddle.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.riddle.view.TemplateEngine;

import java.io.IOException;

@WebServlet("/solution/*")
public class SolutionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        String solution = pathInfo.substring(1);
        req.setAttribute("riddle", solution);
        req.setAttribute("solution", solution);
        TemplateEngine.process("index.html", req, resp);
    }
}
