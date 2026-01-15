package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/hello", true)
                )

                //AuthenticationEntryPoint = LoginUrlAuthenticationEntryPoint("/login") by default
//        So Why /login Specifically?
//
//        Because Spring Security’s default rule is:
//
//        If authentication is required for a web page, redirect the user to a login page instead of returning 401.
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User.withUsername("admin")
                .password("$2a$10$VA.kz9cp/u1DusO6J8JjfuBCLVEc6zG9.tG94qKJCX3yTHb.NzzAO")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("user")
                .password("$2a$10$8D9NaQkiBUITUXTxGqDFIO/pyAJTmGREPFbP4I4MHJ8y2yy94rY.W")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
