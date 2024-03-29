package io.github.servercard.eurekaserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authConfig -> authConfig.anyRequest().authenticated())
        .httpBasic(httpBasic -> {})
        .logout(logout -> {
            logout.logoutUrl("/logout");
            logout.addLogoutHandler((request, response, authentication) -> {
                authentication.setAuthenticated(false);
            });
        });
        return http.build();
    }
}
