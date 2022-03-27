package org.jastka4.digitalgamesstorebackoffice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String AUTHORITIES_QUERY = """
            SELECT u.username, a.name FROM user_authorities AS ua
            JOIN users AS u ON ua.user_id = u.id
            JOIN authorities AS a ON a.id = ua.authority_id WHERE u.username = ? AND a.name = 'ROLE_ADMIN'
            """;

    @Resource
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .authoritiesByUsernameQuery(AUTHORITIES_QUERY);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("static/**", "/login").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }
}
