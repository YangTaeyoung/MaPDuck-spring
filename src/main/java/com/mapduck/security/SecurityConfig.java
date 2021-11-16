package com.mapduck.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    /**
     * 작성자: 양태영
     * 작성일: 21.11.16
     * 설명: 보안이 적용되는 URL 설정
     * @param http: 보안이 적용되는 Http
     * @throws Exception
     */
    // Http로 들어오는 링크 중 보안이 필요한 것들 설정.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/user").permitAll() // 회원 가입의 경우 모든 권한이 할 수 있게 허용
                .antMatchers(HttpMethod.GET, "/api/user/chke").permitAll() // 이메일 중복 확인의 경우 모든 권한이 할 수 있게 허용
                .antMatchers(HttpMethod.GET, "/api/user/chkp").permitAll() // 핸드폰 중복 확인의 경우 모든 권한이 할 수 있게 허용
                .anyRequest().hasRole("MEMBER")
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint()) // 로그인 실패에 따른 정보를 HTML 대신 JSON으로 변경하기 위해 설정
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
