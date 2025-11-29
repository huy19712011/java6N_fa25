package org.example.java6n_fa25.config;

import jakarta.servlet.http.HttpServletRequest;
import org.example.java6n_fa25.filter.CsrfCookieFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
public class SpringSecurityConfig {

    //@Bean
    //public CorsConfigurationSource corsConfigurationSource() {
    //    org.springframework.web.cors.CorsConfiguration configuration = new CorsConfiguration();
    //    configuration.setAllowedOrigins(List.of("http://localhost:5173"));
    //    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    //    configuration.setAllowedHeaders(List.of("*"));
    //    configuration.setAllowCredentials(true);
    //    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //    source.registerCorsConfiguration("/**", configuration);
    //    return source;
    //}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

        http
                .cors(corsConfig -> corsConfig.configurationSource((CorsConfigurationSource) request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
                    //config.setAllowedOrigins(List.of("http://localhost:5173"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setMaxAge(3600L);
                    return config;
                }))

                .csrf(csrfConfigurer -> csrfConfigurer.disable())

                //.csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                //        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                //.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)

                .authorizeHttpRequests(authorize -> {
                    //authorize.requestMatchers(HttpMethod.GET, "/**").permitAll();
                    //authorize.requestMatchers(HttpMethod.POST, "/**").permitAll();
                    //authorize.requestMatchers(HttpMethod.PUT, "/**").permitAll();
                    //authorize.requestMatchers(HttpMethod.DELETE, "/**").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults());


        return http.build();
    }
}
