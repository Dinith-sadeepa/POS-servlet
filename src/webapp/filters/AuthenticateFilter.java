package webapp.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "AuthenticateFilter" , urlPatterns = {"/web/*"})
public class AuthenticateFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        Object isLoggedIn = httpServletRequest.getSession().getAttribute("isLoggedIn");
        if (isLoggedIn == null) {
            req.getRequestDispatcher("/").forward(req, resp);
        }else {
            chain.doFilter(req, resp);
        }
    }
}
