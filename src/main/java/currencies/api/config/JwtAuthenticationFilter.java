package currencies.api.config;

import currencies.api.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private String jwtToken;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, String jwtToken) {
        this.authenticationManager = authenticationManager;
        this.jwtToken = jwtToken;

        setFilterProcessesUrl("/api/authenticate");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain,
                                            Authentication authentication) {
        User user = ((User) authentication.getPrincipal());
        List<String> authorities = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader(
                "Bearer", generate(user.getUsername(), user.getUsername(), authorities)
        );
    }

    private String generate(String username, String identifier, List<String> roles) {
        byte[] apiKeySecretBytes = jwtToken.getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());

        return Jwts.builder()
                .claim("rol", roles)
                .claim("id", identifier)
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 2064000000))
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }

}
