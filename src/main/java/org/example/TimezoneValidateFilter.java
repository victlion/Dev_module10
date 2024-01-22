package org.example;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/time")
public class TimezoneValidateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String timezoneParam = request.getParameter("timezone");

        if (!isValidTimezone(timezoneParam)) {
            request.setAttribute("errorStatus", HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("errorMessage", "Invalid timezone parameter");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private static boolean isValidTimezone(String timezone) {
        if (timezone != null) {
            Matcher matcher = Pattern.compile("^UTC\\+([1-9]|1[0-2])$").matcher(timezone);
            Matcher matcher_ = Pattern.compile("^UTC-([1-9]|1[0-2])$").matcher(timezone);
            Matcher matcher__ = Pattern.compile("^UTC%2B([1-9]|1[0-2])$").matcher(timezone);
            return matcher.find() || matcher_.find() || matcher__.find();
        }
        return true;
    }
}