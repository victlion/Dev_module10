package org.example;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;

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
        TimeZone timeZone;

        if (timezoneParam != null && !timezoneParam.isEmpty()) {
            timeZone = TimeZone.getTimeZone(timezoneParam);
        } else {
            timeZone = TimeZone.getTimeZone("GMT");
        }

        if (timeZone == null) {
            timeZone = TimeZone.getTimeZone("GMT");
        }

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(timeZone);
        String currentTimeWithTimeZone = dateFormat.format(currentDate);

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Current Time</title></head>");
        out.println("<body>");
        out.println("<center><h1>Current Time</h1></center>");
        out.println("<center><p>Time: " + currentTimeWithTimeZone + "</p></center>");
        out.println("</body>");
        out.println("</html>");
    }
}
