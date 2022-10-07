package com.medvedkova.pmsystem.security;

import com.medvedkova.pmsystem.user.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailService;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthenticationFailureHandler authenticationFailureHandler;

    public SecurityConfig(UserDetailsService userDetailService,
                          PasswordEncoder passwordEncoder,
                          UserAuthenticationFailureHandler authenticationFailureHandler) {
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .anonymous(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(AbstractHttpConfigurer::disable)
//                .exceptionHandling(AbstractHttpConfigurer::disable)
//                .headers(AbstractHttpConfigurer::disable)
//                .requestCache(AbstractHttpConfigurer::disable)
//                .servletApi(AbstractHttpConfigurer::disable)
//                .securityContext(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers("/", "/signin", "/signup", "/css/**").permitAll()
                        .anyRequest()
//                        .permitAll()
                        .authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/signin")
                        .defaultSuccessUrl("/project")
                        .failureHandler(authenticationFailureHandler)
                        .permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .logout(logout -> logout
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                        .logoutSuccessUrl("/project/1/task/all")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                )
                .passwordManagement(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    DaoAuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

}
