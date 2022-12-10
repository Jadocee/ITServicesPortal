package com.spacejaam.itservicesportal.configs;

import com.spacejaam.itservicesportal.enums.Role;
import com.spacejaam.itservicesportal.services.ClientDetailService;
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
                        .antMatchers("/issues/created_by_me", "/issues/new").hasRole(Role.USER.name())
                        .antMatchers("/issues/tracker").hasRole(Role.ITSTAFF.name())
                        .antMatchers("/knowledge_base").hasAnyRole(Role.ITSTAFF.name(), Role.USER.name())
                        .antMatchers("/API/**").permitAll()
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
