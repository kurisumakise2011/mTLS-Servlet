package com.wix.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.cert.X509Certificate;

@WebServlet(urlPatterns = "/*")
public class ClientCertificateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        X509Certificate[] cert = (X509Certificate[]) req.getAttribute("javax.servlet.request.X509Certificate");

        PrintWriter wr = resp.getWriter();
        wr.println("TLS Enabled");
        wr.flush();

        if (cert != null) {
            for (X509Certificate certificate : cert) {
                wr.println(certificate.toString());
            }
        } else {
            wr.println("Empty certs");
        }

        resp.setStatus(200);
    }
}
