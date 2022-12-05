package com.ifsp.sesa4.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations{

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .anonymous().disable()
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Não autorizado"))
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .securityMatcher("/api/**").authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .authenticationProvider(jwtAuthenticationProvider)
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedHeaders("Authorization", "AuthorizationLDAP", "Cache-Control", "Content-Type","Executor-Token")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            };
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Arrays.asList(this.jwtAuthenticationProvider));
    }

    public BearerTokenFilter authenticationFilter(){
        BearerTokenFilter bearerTokenFilter = new BearerTokenFilter(this.authenticationService);
        bearerTokenFilter.setAuthenticationManager(authenticationManager());
        bearerTokenFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {});
        return bearerTokenFilter;
    }
}
