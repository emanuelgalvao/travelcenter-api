package com.emanuelgalvao.travelcenter.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfigurations {

    @Autowired
    lateinit var securityFilter: SecurityFilter

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
                .csrf { it.disable() }
                .sessionManagement {
                    it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                }
                .authorizeHttpRequests {
                    it.requestMatchers(HttpMethod.POST, "/auth").permitAll()
                    it.requestMatchers("/countries").hasRole(UserRole.ADMIN.roleName.uppercase())
                    it.requestMatchers("/destinations").hasRole(UserRole.ADMIN.roleName.uppercase())
                    it.requestMatchers("/destination").hasRole(UserRole.ADMIN.roleName.uppercase())
                    it.requestMatchers("/destination/*").hasRole(UserRole.ADMIN.roleName.uppercase())
                    it.requestMatchers("/destinationTypes").hasRole(UserRole.ADMIN.roleName.uppercase())
                    it.requestMatchers("/destinationType").hasRole(UserRole.ADMIN.roleName.uppercase())
                    it.requestMatchers("/destinationType/*").hasRole(UserRole.ADMIN.roleName.uppercase())
                    it.requestMatchers("/destinationAttractions").hasRole(UserRole.ADMIN.roleName.uppercase())
                    it.requestMatchers("/destinationAttraction").hasRole(UserRole.ADMIN.roleName.uppercase())
                    it.requestMatchers("/destinationAttraction/*").hasRole(UserRole.ADMIN.roleName.uppercase())
                    it.requestMatchers("/users").hasRole(UserRole.ADMIN.roleName.uppercase())
                    it.requestMatchers("/user").hasRole(UserRole.ADMIN.roleName.uppercase())
                    it.requestMatchers(HttpMethod.GET, "/app/home").permitAll()
                    it.requestMatchers(HttpMethod.GET, "/app/search").permitAll()
                    it.requestMatchers(HttpMethod.GET, "/app/destinationDetails/*").permitAll()
                    it.requestMatchers(HttpMethod.POST, "/app/register").permitAll()
                    it.requestMatchers(HttpMethod.POST, "/app/login").permitAll()
                    it.requestMatchers(HttpMethod.POST, "/app/destinationRating").authenticated()
                    it.requestMatchers(HttpMethod.POST, "/app/addFavorite").authenticated()
                    it.requestMatchers(HttpMethod.GET, "/app/removeFavorite").authenticated()
                    it.requestMatchers(HttpMethod.POST, "/app/favorites").authenticated()
                }
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter::class.java)
                .build()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}