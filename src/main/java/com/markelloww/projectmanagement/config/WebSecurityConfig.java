package com.markelloww.projectmanagement.config;

import com.markelloww.projectmanagement.service.NewUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    public static final String LOGIN_ENDPOINT = "/login";
    public static final String LOGOUT_ENDPOINT = "/logout";
    public static final String REG_ENDPOINT = "/reg";

    private final NewUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives("default-src 'self'; script-src 'self'; style-src 'self' 'unsafe-inline'")
                        )
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .sessionManagement(session -> session
                        .sessionFixation().migrateSession()
                        .maximumSessions(1)
                        .expiredUrl(LOGIN_ENDPOINT + "?expired")
                )
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(LOGIN_ENDPOINT, REG_ENDPOINT, "/css/auth.css").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .formLogin(form -> form
                        .loginPage(LOGIN_ENDPOINT)
                        .loginProcessingUrl(LOGIN_ENDPOINT)
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl(LOGOUT_ENDPOINT)
                        .logoutSuccessUrl(LOGIN_ENDPOINT)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(LOGIN_ENDPOINT))
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
