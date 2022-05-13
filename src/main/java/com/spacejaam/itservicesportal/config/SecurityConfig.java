package com.spacejaam.itservicesportal.config;

import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  private UserDetailsService userDetailsService;
  @Lazy
  @Resource
  private PasswordEncoder passwordEncoder;

//  @Lazy
//  @Autowired
//  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
//    this.passwordEncoder = passwordEncoder;
//  }

  @Bean
  public DaoAuthenticationProvider authProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder);
    return authProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    auth.authenticationProvider(authProvider())
        .inMemoryAuthentication()
        .withUser("dev")
        .password("dev")
        .roles("User");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(expressionInterceptUrlRegistry -> {
          expressionInterceptUrlRegistry
              .antMatchers("/", "/home", "/login", "/register")
              .permitAll()
              .antMatchers("/knowledgebase/**")
              .access("hasAnyRole('User', 'IT Staff')")
              .antMatchers("/yourissues/**")
              .access("hasRole('User')");
//              .anyRequest()
//              .authenticated();
        })
        .formLogin(form -> {
          form.loginPage("/login")
              .defaultSuccessUrl("/")
              .permitAll();
        })
        .logout(httpSecurityLogoutConfigurer -> {
          httpSecurityLogoutConfigurer.permitAll();
        });
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/resources/**", "/static/**", "$scripts/**", "$styles/**", "$assets/**");
  }
}
