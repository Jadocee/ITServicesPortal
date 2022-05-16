package com.spacejaam.itservicesportal.config;

import com.spacejaam.itservicesportal.service.ClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    //    private final DataSource dataSource;
    @Resource
    private final ClientDetailService clientDetailService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, ClientDetailService clientDetailService) {
        this.passwordEncoder = passwordEncoder;
//        this.dataSource = dataSource;
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
//    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.authenticationProvider(authProvider());
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT * FROM [client] WHERE email = ?");
//                .inMemoryAuthentication()
//                .withUser("dev@spacejaam.com")
//                .password(passwordEncoder.encode("dev"))
//                .roles("User");
//    User.UserBuilder = User.with
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests(expressionInterceptUrlRegistry -> {
//          expressionInterceptUrlRegistry
//              .antMatchers("/", "/home", "/login", "/register", "$/scripts/**", "$styles/**", "$assets/**")
//              .permitAll()
//              .antMatchers("/knowledgebase/**")
//              .access("hasAnyRole('User', 'IT Staff')")
//              .antMatchers("/yourissues/**")
//              .access("hasRole('User')")
//              .anyRequest()
//              .authenticated();
//        })
//        .formLogin(form -> {
//          form.loginPage("/login")
//              .defaultSuccessUrl("/")
//              .permitAll();
//        })
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/error", "/$styles/**.css", "/$scripts/**.js", "/$assets/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/", true)
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login");
//        http.csrf(AbstractHttpConfigurer::disable).authorizeRequests(expressionInterceptUrlRegistry -> {
//            expressionInterceptUrlRegistry
//                    .antMatchers("/", "/$styles/**.css", "/$scripts/**.js", "/$assets/**")
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated();
//        }).formLogin(httpSecurityFormLoginConfigurer -> {
//            httpSecurityFormLoginConfigurer
//                    .loginPage("/login").permitAll()
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                    .defaultSuccessUrl("/", true);
//        }).logout(httpSecurityLogoutConfigurer -> {
//            httpSecurityLogoutConfigurer
//                    .logoutUrl("/logout")
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//                    .clearAuthentication(true)
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID")
//                    .logoutSuccessUrl("/login");
//        });
//        .logout(LogoutConfigurer::permitAll);
    }

//  @Override
//  @Bean
//  protected UserDetailsService userDetailsService() {
////    final
////    UserDetails user = User.builder()
////        .username("dev")
////        .password("dev")
////        .roles("USER")
////        .build();
//    return super.userDetailsService();
//  }

//    @Override
//  public void configure(WebSecurity web) throws Exception {
////    web.ignoring()
////        .mvcMatchers("/resources/**", "$static/*", "$scripts/__Layout.css", "$styles/*", "$assets/*");
//  }
}
