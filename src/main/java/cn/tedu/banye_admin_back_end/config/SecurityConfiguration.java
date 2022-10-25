package cn.tedu.banye_admin_back_end.config;


import cn.tedu.banye_admin_back_end.filter.JwtAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Slf4j
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        log.debug("创建@Bean方法定义的对象：PasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        log.debug("@Bean创建authentication对象");
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] urls={
                "/user/login",
                "/doc.html",
                "/**/*.js",
                "/**/*.css",
                "/swagger-resources",
                "/v2/api-docs"
        };

        log.debug("可以无验证通过的项目是{}",urls);

        http.csrf().disable();//禁用CSRF（防止伪造的跨域攻击）
        http.cors();

        http.authorizeRequests()
                .antMatchers(urls)
                .permitAll()
                .anyRequest()
                .authenticated();

        /*http.formLogin();*/
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    JwtAuthorizationFilter authorizationFilter;
}
