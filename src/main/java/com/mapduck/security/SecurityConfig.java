package com.mapduck.security;

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

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    // Http로 들어오는 링크 중 보안이 필요한 것들 설정.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/login").permitAll() // 로그인의 경우 아직 권한 취득 전이므로 모든 권한 허용
                .antMatchers(HttpMethod.PUT,"/api/user").permitAll() // 회원가입의 경우 모든 권한 허용
                .antMatchers("/api/product/danawa").hasRole("MEMBER") // 상품 등록의 경우 회원 가입된 유저만 허용
                .antMatchers("api/product/myproduct").hasRole("MEMBER") // 내가 등록한 상품 가져오기의 경우 회원가입된 유저만 허용
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

    public CustomeBasicAuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomeBasicAuthenticationEntryPoint();
    }

    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
