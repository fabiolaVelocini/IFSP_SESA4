    package com.ifsp.sesa4.security;

    import com.ifsp.sesa4.DTOs.JwtUserDTO;
    import com.ifsp.sesa4.repositories.UserRepository;
    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.JwtException;
    import io.jsonwebtoken.Jwts;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;

    import java.util.Optional;

    @Component
    public class JwtTokenValidator {
        private final String secret;
        public JwtTokenValidator(
                @Value("${jwt.secret}") String secret
    ) {
        this.secret = secret;
    }

    @Autowired
    private UserRepository userRepository;

    public Optional<AuthenticationInfo> parseToken(String token) {
        JwtUserDTO jwtUserDTO = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            jwtUserDTO = new JwtUserDTO();
            jwtUserDTO.setUsername(body.getSubject());
            return this.userRepository
                    .findById(Long.parseLong((String) body.get("userId")))
                    .map(AuthenticationInfo::new);
        } catch (JwtException e) {
        }
        return Optional.empty();
    }

}
