package com.alkemy.ong.auth;

import com.alkemy.ong.auth.filter.JwtAuthenticationFilter;
import com.alkemy.ong.service.impl.CustomUserDetailsService;
import com.alkemy.ong.utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    private static final String[] publicEndpoint = {
            "/swagger-resources/**",
            "/swagger-ui/**", "/v2/api-docs",
            "/v3/api-docs",
            "/api/docs",
            "/api/docs/**",
            "/api/docs/swagger-ui",
            "/swagger-ui.html",
            "/**/swagger-ui/**",
            "/swagger-ui"
    };

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                //auth
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .antMatchers(HttpMethod.GET, "/auth/me").hasAnyRole(RoleEnum.ADMIN.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())

                //organization
                .antMatchers(HttpMethod.POST, "/organization/public").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/organization/public").permitAll()

                //users
                .antMatchers(HttpMethod.GET, "/users").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/users/{id}").hasAnyRole(RoleEnum.ADMIN.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())
                .antMatchers(HttpMethod.PATCH, "/users/{id}").hasAnyRole(RoleEnum.ADMIN.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())

                //slides
                .antMatchers(HttpMethod.POST, "/slides").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/slides").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/slides/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.PUT, "/slides/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/slides/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())

                //categories
                .antMatchers(HttpMethod.POST, "/categories").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.PUT, "/categories/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/categories").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/categories/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/categories/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())

                //news
                .antMatchers(HttpMethod.POST, "/news").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/news").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.PATCH, "/news").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/news").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/news/{id}/comments").hasRole(RoleEnum.USER.getSimpleRoleName())

                //contacts
                .antMatchers(HttpMethod.POST, "/contacts").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/contacts").hasRole(RoleEnum.ADMIN.getSimpleRoleName())

                //activities
                .antMatchers(HttpMethod.POST, "/activities").hasAnyRole(RoleEnum.ADMIN.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())
                .antMatchers(HttpMethod.PUT, "/activities").hasAnyRole(RoleEnum.ADMIN.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())

                //testimonials
                .antMatchers(HttpMethod.POST, "/testimonials").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.PUT, "/testimonials/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/testimonials/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())

                //comments
                .antMatchers(HttpMethod.GET, "/comments").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.POST, "/comments").hasRole(RoleEnum.USER.getSimpleRoleName())
                .antMatchers(HttpMethod.PUT, "/comments/{id}").hasAnyRole(RoleEnum.ADMIN.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/comments/{id}").hasAnyRole(RoleEnum.ADMIN.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())

                //members
                .antMatchers(HttpMethod.POST, "/members").hasAnyRole(RoleEnum.ADMIN.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())
                .antMatchers(HttpMethod.PUT, "/members/{id}").hasAnyRole(RoleEnum.ADMIN.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/members").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/members/paginated").hasAnyRole(RoleEnum.ADMIN.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/members/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())

                //Swagger
                .antMatchers("/swagger-ui/**","/swagger-resources/**","/v2/api-docs").permitAll()
                .antMatchers(publicEndpoint).permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);



                /*agregar autorizaciones a los endpoints pendientes en desarrollo

                 *EJEMPLO:
                 * PARA TODOS:
                 * .antMatchers(HttpMethod.<TIPO>, "<endpoint>").permitAll()
                 * PARA USER:
                 * .antMatchers(HttpMethod.<TIPO>, "<endpoint>").hasRole(RoleEnum.USER.getSimpleRoleName);
                 * PARA ADMIN:
                 * .antMatchers(HttpMethod.<TIPO>, "<endpoint>").hasRole(RoleEnum.ADMIN.getSimpleRoleName);
                 * PARA USER Y ADMIN:
                 * .antMatchers(HttpMethod.<TIPO>, ">endpoint>").hasAnyRole(RoleEnum.ADMIN.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())
                 */


        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
