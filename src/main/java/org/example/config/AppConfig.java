package org.example.config;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.*;


@SpringBootApplication //@Configuration = aceasta este o clasa de configurare; @EnableAutoConfig = defineste pachetul de cautare de baza; @ComponentScan = scaneaza toate clasele din subpachetl de baza
@ComponentScan("org.example")//@ComponentScan = scaneaza toate clasele din subpachetl de baza
@EntityScan("org.example.entities")//ii spunem aplicatiei in ce pachet se afla entitatile, ca sa le poata scana(altfel aplicatia le cauta in pachetul main sau in subpachetele sale); @EnableAutoConfiguration va scana atat pachetul entities cat si subpachetele entities daca sunt
@EnableJpaRepositories("org.example.repositories")//ii spunem aplicatiei in ce pachet se afla repositories, ca sa le poata scana(altfel aplicatia le cauta in pachetul main sau in subpachetele sale); @EnableAutoConfiguration va scana pachetul repositories si subpachetele sale daca sunt, pentru a activa JPARepositories
@EnableWebMvc//creaza componentele necesare pentru aplicatii web
@Import(WebSecurityConfig.class)//incarca fisierul de AppConfig la pornire +WebSecurityConfig
public class AppConfig implements WebMvcConfigurer {

    @Override //suprascriem metoda din interfata WebMVCConfigurer
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/templates/css/");
    }
}
