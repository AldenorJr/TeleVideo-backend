package br.com.nexus.televideo.config;

import br.com.nexus.televideo.controller.FilterToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Configurations {

    @Autowired
    private FilterToken filterToken;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/produtos/{ID}").permitAll()
                .and().authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/produtos/imagem/{ID]").permitAll()
                .and().authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/produtos").permitAll()
                .and().authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/user/register").permitAll()
                .and().cors()
                .and().authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/user/login").permitAll()
                .anyRequest().authenticated().and().addFilterBefore(filterToken, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedPage("/login").and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
