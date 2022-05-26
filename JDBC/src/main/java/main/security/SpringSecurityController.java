package main.security;

import main.security.jwt.JwtProvider;
import main.security.jwt.JwtSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SpringSecurityController extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtProvider jwtProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/login/**").permitAll()
                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,
                        "/", "/goods", "/sales",
                        "/warehouse/wh_id=1/notes", "/warehouse/wh_id=2/notes").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/wc/warehouse/**", "/wc/sales/**", "/wc/goods/**").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/wc/add_note_to_wh/**", "/wc/add_list_notes_to_wh/**",
                        "/wc/add_sale", "/wc/add_list_sales",
                        "/wc/add_good", "/wc/add_list_goods").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfigurer(jwtProvider));
    }
}
