package com.bakefinity.handlers.filters;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import com.bakefinity.model.dtos.UserDTO;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@WebFilter( filterName = "AdminAuthorizationFilter", urlPatterns = {"/cart" , "/order-summary" , "/checkout"},
        description = "restricts access of cart and checkout pages to authenticated users only")

public class AdminAuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException ,ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpServletRequest.getSession( false );

        if ( httpSession == null ) {
            httpServletResponse.sendRedirect( httpServletRequest.getContextPath() + "/home" );
            return;
        }

        UserDTO user = (UserDTO) httpSession.getAttribute( "user" );
        if ( user != null && "USER".equals(user.getRole())) {
            chain.doFilter( request, response );
        } else {
            httpServletResponse.sendRedirect( httpServletRequest.getContextPath() + "/home" );
        }
    }
}