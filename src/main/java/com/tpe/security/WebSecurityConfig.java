package com.tpe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Security katmanina bu clasimin konfigurasyon vlasi oldugunu soylutorum
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // merhod seviyede yetkilendirme yapacagimi soyluyorum
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        // !!! bu classda amacimiz : AuthManager, Provider , PassEncoder larimi olusturup birbirleriyle
        // tanistirmak

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().  // csrf korumasini disable yapiyoruz
                authorizeHttpRequests().  // gelen butun rwquestleri yetkilimi diye kontrol edecegiz
                antMatchers("/",
                "index.html",
                "/css/*",
                "/js/*").permitAll(). // bu end-pointleri yetkili mi diye kontrol etme
                anyRequest(). // muaf tutulan end-pointler disinda gelen herhangi bir requesti
                authenticated(). // yetkili mi diye kontrol et
                and().
                httpBasic(); // bunu yaparkende Basic Auth kullanilacagini belirtiyoruz
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);//sifreleme guvenligi 4 ile 30 arasinda yukseldikce sure artar
    }

    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setPasswordEncoder(passwordEncoder()); // encoder ile tanistirdim
        authProvider.setUserDetailsService(userDetailsService); // Service katimi kanistirmis oldum

        return authProvider;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(authProvider());
    }
}



