package ma.tc.projects.securityJwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.tc.projects.entity.AppUser;
import ma.tc.projects.repository.AppUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private AppUserRepository userRepository;


    public JWTAuthenticationFilter(AuthenticationManager a,AppUserRepository us){
        authenticationManager=a;
        userRepository=us;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AppUser appUser=null;
         try {
             appUser=new ObjectMapper().readValue(request.getInputStream(),AppUser.class);
             return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(),appUser.getPassword()));

         } catch (IOException e) {

        }
         catch(UsernameNotFoundException s){
             response.addHeader("user","user not found");
         }
         throw new RuntimeException("Probleme in request content");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {


        User user=(User)authResult.getPrincipal();
        AppUser u=userRepository.findByUsername(user.getUsername()).get();
        List<String> roles=new ArrayList<>();
        user.getAuthorities().forEach(r->{
            roles.add(r.getAuthority());
        });


        String jwt = JWT.create()
                .withIssuer(request.getRequestURI())
                .withSubject(user.getUsername())
                .withArrayClaim("roles",roles.toArray(new String[roles.size()] ))
                 .withClaim("magasinNale",u.getMagasin().getNom())
                 .withClaim("magasinId",u.getMagasin().getIdMagasin())
                .withExpiresAt(new Date(System.currentTimeMillis()+SecurityParams.EXPIRATION))
                .sign(Algorithm.HMAC256(SecurityParams.PRIVATE_KEY));
        response.addHeader(SecurityParams.JWT_HEADER,SecurityParams.HEADER_PREFIX+jwt);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {


        if(failed.getMessage()==null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
        }
        if(failed.getMessage().equals("Bad credentials")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
    }
}
