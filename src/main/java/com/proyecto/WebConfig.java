package com.proyecto;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /* localeResolver se utiliza para crear una sesión de cambio de idioma*/
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    /* localeChangeInterceptor se utiliza para crear un interceptor de cambio de idioma*/
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    //Bean para poder acceder a los Messages.properties en código...
    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /* Los siguiente métodos son para implementar el tema de seguridad dentro del proyecto */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/aboutus/about_us").setViewName("/aboutus/about_us");
        registry.addViewController("/menu/menu").setViewName("/menu/menu");
        registry.addViewController("/ubicacion/ubicacion").setViewName("/ubicacion/ubicacion");
        registry.addViewController("/order").setViewName("order");
        registry.addViewController("/registro/registro").setViewName("/registro/registro");
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/", "/index", "/errores/**", "/menu/**",
                        "/carrito/**", "/aboutus/**", "/usuario/**", "/js/**",
                        "/webjars/**", "/css/**", "/img/**", "/registro/**",
                        "/registro/activacion/**", "/registro/activa", "/registro/activar", "/ubicacion/ubicacion/**", "/order/**")
                .permitAll()
                .requestMatchers(
                        "/menu/menu", "/registro/registro", "/registro/activacion/**",
                        "/registro/recordar/**", "/registro/salida/**", "/usuario/guardar",
                        "/usuario/modificar/**", "/usuario/eliminar/**",
                        "/order/**", "/carrito/**", "registro/**", "/login/**", "/carrito/**"
                ).hasRole("ADMIN")
                .requestMatchers("/order", "/aboutus/about_us",
                        "/menu/menu", "/ubicacion/ubicacion/**", "/registro/registro", "/registro/activacion",
                        "/registro/recordar/**", "/registro/salida/**", "/order/**", "/carrito/**")
                .hasRole("USER")
                )
                .formLogin((form) -> form
                .loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }*/

    @Autowired
    private UserDetailsService userDetailsService;

    //Para encriptar la contraseña
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}