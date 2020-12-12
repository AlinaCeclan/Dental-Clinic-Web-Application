package org.example.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

@Configuration///@Configuration = aceasta este o clasa de configurare;
@EnableWebSecurity//permite Springului sa gaseasca clasa de configurare pentru web security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired //injectam un bean intr-un bean (un fel de composition)
    private DataSource dataSource;

    @Bean //creaza o instanta a unei clase
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //trebuie sa dezactivam csrf(cross side request forgery) pt a preveni forme de atac asupra site-ului, e activat default
        http.csrf().disable();
        //http.cors().disable();
        //inlantuim comenzi
        http
                //am setat url-urile pe care le vrem sa fie public
                .authorizeRequests()
                .antMatchers("/css/**", "/403", "/login","/images/**", "/doctors","/register","/register-user","/servicesPrices","/home")
                .permitAll()

                //aici permitem doar adminului sa acceseze pagina de adaugare a unui user
                .and()
                .authorizeRequests()

                //aici se autorizeaza cei autentificati
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()

                //in caz de accesare esuata este redirectionat la pagina f403
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")

                //configuram login-ul metoda de logare
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login")

                //aici configuram logout-ul cu url-ul lui
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll();
    }

    @Autowired //apeleaza metoda la rulare
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       PasswordEncoder encoder = passwordEncoder();
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoder);
        System.out.println("pass 'parola' encryted: "+encoder.encode("parola"));
    }

}


