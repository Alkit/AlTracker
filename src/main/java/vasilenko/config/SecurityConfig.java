package vasilenko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import vasilenko.serivces.RoleAuthenticationSuccessHandler;
import vasilenko.serivces.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@SuppressWarnings("SpringJavaAutowiringInspection")
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private RoleAuthenticationSuccessHandler roleAuthenticationSuccessHandler;

   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("user").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                .withUser("Dniwe").password("admin").roles("ADMIN");
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                formLogin()
                .loginPage("/")
                .successHandler(roleAuthenticationSuccessHandler)
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .rememberMe()
                .tokenRepository(new InMemoryTokenRepositoryImpl())
                .tokenValiditySeconds(172800).key("track")
                .and()
                .httpBasic()
                .realmName("AlTracker")
                .and()
                .authorizeRequests()
                .mvcMatchers("/admin/*").hasRole("ADMIN")
                .mvcMatchers("/admin").hasRole("ADMIN")
                .mvcMatchers("/employee").hasRole("USER")
                .mvcMatchers("/employee/*").hasRole("USER")
                .mvcMatchers("/pm/*").hasRole("PM")
                .mvcMatchers("/pm").hasRole("PM")
                .mvcMatchers("/api/*").hasRole("PM")
                .mvcMatchers("/sprint/*").hasRole("PM")
                .mvcMatchers("/report/*").hasRole("PM")
                .anyRequest()
                .permitAll();
    }
}
