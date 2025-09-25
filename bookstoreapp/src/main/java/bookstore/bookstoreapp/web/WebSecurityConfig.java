package bookstore.bookstoreapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    
    //@Autowired
    private UserDetailsService userDetailsService; // type of attribute -> interface
    
    // Constructor injection
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService; 
    }

@Bean
public SecurityFilterChain configure(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/", "/index", "/css/**").permitAll()
            .requestMatchers(toH2Console()).permitAll()
            .anyRequest().authenticated())
        .headers(headers -> 
            headers.frameOptions(frameOptions -> frameOptions.disable()))
        .formLogin(formlogin -> 
            formlogin
                .defaultSuccessUrl("/", true)
                .permitAll()) // ✅ EI loginPage-riviä → käytetään Springin oletussivua
        .logout(logout -> 
            logout
                .permitAll()
                .invalidateHttpSession(true))
        .csrf(csrf -> 
            csrf.ignoringRequestMatchers(toH2Console()));

    return http.build();
}



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
