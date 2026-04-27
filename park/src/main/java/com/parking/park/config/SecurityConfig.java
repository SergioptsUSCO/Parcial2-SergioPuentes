package com.parking.park.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMINISTRADOR")
                                .requestMatchers("/acomodador/**").hasRole("ACOMODADOR")        //Asignando los roles
                                .requestMatchers("/cliente/**").hasRole("CLIENTE")
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                                .defaultSuccessUrl("/home", true)
                )
                .logout(logout -> logout.logoutSuccessUrl("/login"));

        return http.build();
    }

}