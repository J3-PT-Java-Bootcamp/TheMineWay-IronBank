package com.themineway.themineway_ironbank.config.security;

import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
            .antMatchers("/user/account-holder").hasRole("admin")
            .antMatchers("/user/admin").hasRole("admin")
            .antMatchers("/user/third-party").hasRole("admin")

            // CHECKING ACCOUNT
            .antMatchers("/checking-account/my-account/{id}").permitAll()
            .antMatchers("/checking-account/my-accounts").permitAll()
            .antMatchers("/checking-account/transfer").permitAll()
            .antMatchers("/checking-account/**").hasRole("admin")

            // CREDIT ACCOUNT
            .antMatchers("/credit-account/my-account/**").permitAll()
            .antMatchers("/credit-account/my-accounts").permitAll()
            .antMatchers("/credit-account/transfer").permitAll()
            .antMatchers("/credit-account/**").hasRole("admin")

            // SAVINGS
            .antMatchers("/savings/my-account/**").permitAll()
            .antMatchers("/savings/my-accounts").permitAll()
            .antMatchers("/savings/transfer").permitAll()
            .antMatchers("/savings/**").hasRole("admin")

            // STUDENT CHECKING
            .antMatchers("/student-checking-account/my-account/**").permitAll()
            .antMatchers("/student-checking-account/my-accounts").permitAll()
            .antMatchers("/student-checking-account/transfer").permitAll()
            .antMatchers("/student-checking-account/**").hasRole("admin")

            .anyRequest()
            .permitAll();
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }
}