package ma.tc.projects.securityJwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwt=httpServletRequest.getHeader(SecurityParams.JWT_HEADER);



        httpServletResponse.addHeader("Access-Control-Allow-Origin","*");
        httpServletResponse.addHeader("Access-Control-Allow-Headers","Origin, Accept,X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,Authorization");
        httpServletResponse.addHeader("Access-Control-Expose-Headers","Access-Control-Allow-Origin,Access-Control-Allow-Credentials, Authorization");
        httpServletResponse.addHeader("OPTIONS","*");
        httpServletResponse.addHeader("Access-Control-Allow-Methods","GET, POST,DELETE,PUT,PATCH");
        if(httpServletRequest.getMethod().equals("OPTIONS")){
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        }

        else if(httpServletRequest.getRequestURI().equals("/login")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return ;
        }

        else {

            if (jwt == null || !jwt.startsWith(SecurityParams.HEADER_PREFIX)) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return ;
            }

                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SecurityParams.PRIVATE_KEY)).build();
                String token = jwt.substring(SecurityParams.HEADER_PREFIX.length());


            try {
                   DecodedJWT decodeJwt = jwtVerifier.verify(token);
                    List<String> roles = decodeJwt.getClaim("roles").asList(String.class);
                    System.out.println("roles++++++++++++++" + roles);
                    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                    String username = decodeJwt.getSubject();
                    for (String role : roles) {
                        authorities.add(new SimpleGrantedAuthority(role));
                    }
                    UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(user);
                    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                }

            catch (TokenExpiredException r){
                System.out.println("errror");
                httpServletResponse.addHeader("expired","token expired");
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return ;
            }

        }
    }
}
