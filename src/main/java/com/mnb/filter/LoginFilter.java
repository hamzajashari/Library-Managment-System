package com.mnb.filter;

import com.mnb.entity.User;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
@Profile("servlet")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User)request.getSession().getAttribute("user");

        String path = request.getServletPath();

        if (!"/login".equals(path) &&
                !"/h2".equals(path) &&
                !"/register".equals(path) &&
                !"/login.css".equals(path) &&
                user==null &&
                !"/books".equals(path) &&
                !"/publishers".equals(path) &&
                !"/authors".equals(path) &&
                !"/books/search".equals(path) &&
                !"/authors/search".equals(path) &&
                !"/publishers/search".equals(path)
        ) {
            response.sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
