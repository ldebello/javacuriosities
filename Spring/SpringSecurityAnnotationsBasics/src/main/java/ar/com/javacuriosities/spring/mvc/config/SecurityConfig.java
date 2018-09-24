package ar.com.javacuriosities.spring.mvc.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http. //
            authorizeRequests(). //
                antMatchers("/admin**"). //
                hasRole("ADMIN");

        withFormLogin(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        configureInMemoryAuthentication(auth);
    }

    private void withBasicLogin(HttpSecurity http) throws Exception {
        http. //
                httpBasic();
    }

    private void withFormLogin(HttpSecurity http) throws Exception {
        http. //
                formLogin().and(). //
                logout();
    }

    private void configureInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth. //
                inMemoryAuthentication() //
                .withUser("admin") //
                .password("{noop}1234") //
                .roles("ADMIN");
    }

    private void configureJDBCAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled"
                        + " from users where username=?")
                .authoritiesByUsernameQuery("select username, authority "
                        + "from authorities where username=?");
    }
}
