package slfdemo.sidecar.springboot.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;
import slfdemo.sidecar.springboot.Constants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authorizationHeader = request.getHeader("Authorization");
        String[] authHeaderArr = (authorizationHeader == null)? new String[] {} : authorizationHeader.split("Bearer ");


        try{
            String token = authHeaderArr[1];
            Claims claims = Jwts.parser().setSigningKey(Constants.getTokenKey()).parseClaimsJws(token).getBody();

            request.setAttribute("userId", claims.get("userId"));
            request.setAttribute("username", claims.get("username"));

        }catch(Exception ex) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Invalid or Expired Token");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
