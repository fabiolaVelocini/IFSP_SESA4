package com.ifsp.sesa4.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.Objects;

public class BearerTokenFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationService authenticationService;

    protected BearerTokenFilter(AuthenticationService authenticationService){
        super("/**");
        this.authenticationService = authenticationService;
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader("AuthorizationLDAP");
        return !Objects.isNull(authorizationHeader);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String authorizationHeader = request.getHeader("AuthorizationLDAP");
        if(Objects.isNull(authorizationHeader)){
            throw new AuthenticationExceptionImpl("Bearer token not found");
        }else{
            try{
                String bearerToken = authorizationHeader.split(" ")[1];
                if(bearerToken!=null){
                    AuthenticationInfo authenticationInfo = this.authenticationService.authenticateUser(bearerToken);
                    return getAuthenticationManager().authenticate(authenticationInfo);
                }
            }catch(AuthenticationException e){
                throw e;
            }catch (Exception e){
                throw new AuthenticationExceptionImpl(e.getLocalizedMessage());
            }
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }

    @Override
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }

}
