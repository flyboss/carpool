package com.carpool.configuration;

import com.carpool.domain.UserEntity;
import com.carpool.website.dao.SessionRepository;
import com.carpool.website.dao.UserEntityRepository;
import com.carpool.website.service.AuthenticationService;
import com.carpool.website.service.LoginService;
import com.carpool.website.service.SessionService;
import com.carpool.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;


/**
 * Created by deado on 2016/12/15.
 */
@Configuration
@EnableWebSecurity
@ComponentScan
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private
    UserEntityRepository userEntityRepository;

    @Autowired
    private
    UserService userService;

    @Autowired
    private
    SessionRepository sessionRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        //generate a system user
        UserEntity admin = new UserEntity("0000001", "系统", "qiangwudi", (byte)0, 5.0, "", 100000, "", "", "");
        admin.setReceivedComments(0);
        this.userService.saveUser(admin);

        SessionService sessionService = new SessionService(sessionRepository);

        PersistentTokenBasedRememberMeServices rememberMeService =
                new PersistentTokenBasedRememberMeServices(
                        "CarpoolRememberCheck",
                        new LoginService(userEntityRepository),
                        sessionService
                );

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/Test").permitAll()
                .antMatchers("/Test/insert").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/static/*/*/*").permitAll()
                .antMatchers("/static/*/*").permitAll()
                .antMatchers("/*/*/*.js").permitAll()
                .antMatchers("/*/*/*.png").permitAll()
                .antMatchers("/*/*/*/*.js").permitAll()
                .antMatchers("/*/*/*/*/*.css").permitAll()
                .antMatchers("/*/*/*.css").permitAll()
                .antMatchers("*.icon").permitAll()
                .mvcMatchers("/getLoginCode").permitAll()
                .antMatchers("/checkimagecode").permitAll()
                .mvcMatchers("/index").permitAll()
                .mvcMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/home/main", true)
                .and()
                .rememberMe()
                .tokenValiditySeconds(60*30)
                .rememberMeServices(rememberMeService)
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .sessionManagement().sessionAuthenticationErrorUrl("/login").maximumSessions(1);

    }


    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{

        LoginService loginService = new LoginService(userEntityRepository);

        auth.authenticationProvider(new AuthenticationService(loginService, userService)).userDetailsService(loginService);
    }

}
