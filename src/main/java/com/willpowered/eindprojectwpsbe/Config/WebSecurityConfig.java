package com.willpowered.eindprojectwpsbe.Config;

import com.willpowered.eindprojectwpsbe.Security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    WebSecurityConfig(DataSource dataSource, JwtRequestFilter jwtRequestFilter) {
        this.dataSource = dataSource;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities AS a WHERE username=?");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(POST, "/authenticate/**").permitAll()

                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/portal/**").hasRole("USER")

                .antMatchers(PATCH, "/users/{^[\\w]$}/password").authenticated()
                .antMatchers(POST, "/users/**").hasRole("ADMIN")
                .antMatchers(DELETE, "/users/**").hasRole("ADMIN")
                .antMatchers(PUT, "/users/password").hasRole("USER")
                .antMatchers(PUT, "/users/**").hasRole("USER")
                .antMatchers(GET, "/users/**").permitAll()
                .antMatchers(GET, "/users").permitAll()

                .antMatchers(POST, "/comments/**").hasRole("USER")
                .antMatchers(PUT, "/comments/**").hasRole("USER")
                .antMatchers(DELETE, "/comments/**").hasRole("USER")

                .antMatchers(POST, "/votes").hasRole("USER")

                .antMatchers("/portals/**").hasRole("USER")

                .antMatchers(GET, "/alerts").hasRole("USER")
                .antMatchers(POST, "/alerts").hasRole("ADMIN")
                .antMatchers(PUT, "/alerts").hasRole("ADMIN")
                .antMatchers(DELETE, "/alerts/**").hasRole("USER")

                .antMatchers(POST, "/blogs/**").hasRole("ADMIN")
                .antMatchers(PUT, "/blogs/**").hasRole("ADMIN")
                .antMatchers(DELETE, "/blogs/**").hasRole("ADMIN")

                .antMatchers(POST, "/projects").hasRole("SUPER_USER")
                .antMatchers(PUT, "/projects/**").hasRole("SUPER_USER")
                .antMatchers(DELETE, "/projects/**").hasRole("SUPER_USER")

                .antMatchers("/tasks/**").hasRole("USER")
                .antMatchers(POST, "/tasks").hasRole("USER")
                .antMatchers(PUT, "/tasks").hasRole("USER")

                .antMatchers(POST, "/files").hasRole("USER")
                .antMatchers(GET, "/**/**/**").permitAll()
                .anyRequest().denyAll()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }


}
