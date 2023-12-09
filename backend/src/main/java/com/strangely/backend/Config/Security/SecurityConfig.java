package com.strangely.backend.Config.Security;


import com.strangely.backend.Config.AuthConfig.AuthEP;
import com.strangely.backend.Config.AuthConfig.AuthProvider;
import com.strangely.backend.Config.JWTConfig.JWTConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private AuthProvider ap;
    @Autowired
    private AuthEP aep;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(aep))
                .addFilterBefore(new JWTConfig(ap), BasicAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(authorize -> {
                    authorize.anyRequest().permitAll();
//                            .requestMatchers(HttpMethod.POST, "/loginSS", "/registerSS", "/user-by-tokenSS", "/resetpasswordSS", "/resetSS", "/logoutSS", "/getIdByLatLong", "/getCitybyId","/chat/send-message").permitAll()
//                            .requestMatchers(HttpMethod.GET, "/strangely/*", "/strangely/myaccount/*", "/update/", "/strangely/all-users", "/loginwithgoogle", "/getIdByLatLong","/chat/get-messages").permitAll()
//                            .requestMatchers(HttpMethod.PUT, "/strangely/*", "/strangely/update/*").permitAll()

                })
                .formLogin().permitAll().and().logout().permitAll();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
