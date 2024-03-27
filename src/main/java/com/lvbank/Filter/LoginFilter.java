package com.lvbank.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        ArrayList<String> accessBeforeLoginURI= new ArrayList<>();
        accessBeforeLoginURI.add(request.getContextPath() + "/index.jsp");
        accessBeforeLoginURI.add(request.getContextPath() + "/JSP/profile/entry/Login.jsp");
        accessBeforeLoginURI.add(request.getContextPath() + "/JSP/profile/entry/Login");
        accessBeforeLoginURI.add(request.getContextPath() + "/JSP/profile/entry/SignUp.jsp");
        accessBeforeLoginURI.add(request.getContextPath() + "/JSP/profile/entry/Signup");

        boolean loggedIn = session != null && session.getAttribute("role") != null;
        boolean loginRequest = accessBeforeLoginURI.contains(request.getRequestURI());

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
