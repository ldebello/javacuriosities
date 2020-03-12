package ar.com.javacuriosities.jsf.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            HttpSession session = httpServletRequest.getSession(false);

            String uri = httpServletRequest.getRequestURI();
            if (shouldAllow(uri) || isLogged(session)) {
                chain.doFilter(request, response);
            } else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.xhtml");
            }

        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isLogged(HttpSession session) {
        return session != null && session.getAttribute("logged") != null;
    }

    private boolean shouldAllow(String uri) {
        return uri.contains("/index.xhtml") || uri.contains("/redirect");
    }
}
