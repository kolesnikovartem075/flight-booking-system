package org.artem.flight.system.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(urlConfig -> urlConfig
                        .antMatchers("/login","/logout", "/customers/**", "/js/**", "/css/**", "/images/**").permitAll()
                        .antMatchers("/schedules/**", "/flights/**", "/airlines/**", "/airports/**", "/orders/**", "/shopping-cart/**", "/shopping-cart-item/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .anyRequest().denyAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID"))
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/schedules"))
                .build();
    }
}