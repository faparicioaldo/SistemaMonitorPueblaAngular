package com.puebla.monitoralertas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
        	.cors()
        	.and()
        	.csrf().disable()
        	
            .authorizeRequests()
                .antMatchers("/resources/**", "/registration").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/video/**").permitAll()
                .antMatchers("/ws/**","/app/**","/topic/**").permitAll()                
                .antMatchers("/MonitorAlertasPuebla/**").permitAll()
                .anyRequest()
                .authenticated()
//                .and()
//        		.headers().frameOptions().disable()
//                .and().headers().defaultsDisabled()
            		.and()
            			.headers()
            			.frameOptions()
//            				.sameOrigin()
            				.disable()
//            				.addHeaderWriter(
//            						new XFrameOptionsHeaderWriter(
//            								new StaticAllowFromStrategy(
//            										URI.create("puebla.webmaps.com.mx"))))
            				.addHeaderWriter(new StaticHeadersWriter("X-FRAME-OPTIONS"
            						, "ALLOW-FROM puebla.webmaps.com.mx"
//            						, "ALLOW-FROM localhost"
            						)) 
            				
//            				.headers().addHeaderWriter(new StaticHeadersWriter(
//            				        "X-Content-Security-Policy",
//            				        "frame-ancestors self *.puebla.webmaps.com.mx *.localhost"))
//            				.and()
//            				.headers().addHeaderWriter(new StaticHeadersWriter(
//            				        "Content-Security-Policy",
//            				        "frame-ancestors self *.puebla.webmaps.com.mx *.localhost"))            				
            	.and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll()
            .and()
            	.sessionManagement()
        		.maximumSessions(1)
        		.expiredUrl("/login?expired")
        		;

    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}