package com.spacejaam.itservicesportal.config;

import com.spacejaam.itservicesportal.model.client.Role;
import com.spacejaam.itservicesportal.service.ClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    @Resource
    private final ClientDetailService clientDetailService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, ClientDetailService clientDetailService) {
        this.passwordEncoder = passwordEncoder;
        this.clientDetailService = clientDetailService;
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(clientDetailService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable).authorizeRequests(expressionInterceptUrlRegistry -> expressionInterceptUrlRegistry
                        .antMatchers("/", "/login", "/error", "/$styles/**.css", "/$scripts/**.js", "/$assets/**").permitAll()
                        .antMatchers("/your-issues", "/issues/new").hasRole(Role.USER.name())
                        .antMatchers("/tracker").hasRole(Role.ITSTAFF.name())
                        .antMatchers("/knowledge-base").hasAnyRole(Role.ITSTAFF.name(), Role.USER.name())
                        .anyRequest().authenticated()
                ).formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/", true)
                        .usernameParameter("email")
                        .passwordParameter("password")
                ).logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/logout")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login")
                );
    }
}
