package org.example;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        String timezoneParam = request.getParameter("timezone");

        Instant instant = Instant.now();
        ZoneId customTimeZone;
        try {
            customTimeZone = ZoneId.of(timezoneParam);
        } catch (NullPointerException e) {
            customTimeZone = ZoneId.of("UTC");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z")
                .withZone(customTimeZone);

        String formattedTime = formatter.format(instant);

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Current Time</title></head>");
        out.println("<body>");
        out.println("<center><h1>Current Time</h1></center>");
        out.println("<center><p>Time: " + formattedTime + "</p></center>");
        out.println("</body>");
        out.println("</html>");
    }
}
