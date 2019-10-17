package io.github.dndanoff.doormonitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final AppConfig appConfig;

	@Autowired
	public WebSecurityConfig(AppConfig appConfig){
		this.appConfig = appConfig;
	}

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication()
        .withUser(appConfig.getSecurity().getAdminName()).password(passwordEncoder().encode(appConfig.getSecurity().getAdminPassword())).roles(ApplicationRoles.ADMIN.getRoleName())
        .and()
        .withUser(appConfig.getSecurity().getClientName()).password(passwordEncoder().encode(appConfig.getSecurity().getClientPassword())).roles(ApplicationRoles.SENSOR_CLIENT.getRoleName());
    }
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication()
        .withUser(appConfig.getSecurity().getAdminName()).password(passwordEncoder().encode(appConfig.getSecurity().getAdminPassword())).roles(ApplicationRoles.ADMIN.getRoleName())
        .and()
        .withUser(appConfig.getSecurity().getClientName()).password(passwordEncoder().encode(appConfig.getSecurity().getClientPassword())).roles(ApplicationRoles.SENSOR_CLIENT.getRoleName());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
        	.exceptionHandling()
	        	.defaultAuthenticationEntryPointFor(adminAauthenticationEntryPoint(), new AntPathRequestMatcher("/management/**"))
	        	.defaultAuthenticationEntryPointFor(generalAuthenticationEntryPoint(), new AntPathRequestMatcher("/api/**"))
    	.and()
	        .httpBasic()
        .and()
	        .sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.cors()
		.and()
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/actuator/**").hasAnyRole(ApplicationRoles.ADMIN.getRoleName())
				.antMatchers(HttpMethod.POST, "/api/v1/door-readings/**").hasAnyRole(ApplicationRoles.SENSOR_CLIENT.getRoleName())
				.antMatchers(HttpMethod.GET, "/api/v1/doors/**").permitAll()
				.antMatchers("/", "/index.html", "/images/**", "/js/**", "/css/**").permitAll()
				.antMatchers("/api/**").authenticated()
				.anyRequest().authenticated();

		http.headers().frameOptions().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(appConfig.getSecurity().getPasswordEncoderStrength());
	}
	
	@Bean
	public AuthenticationEntryPoint generalAuthenticationEntryPoint(){
	    BasicAuthenticationEntryPoint entryPoint = 
	      new BasicAuthenticationEntryPoint();
	    entryPoint.setRealmName(appConfig.getSecurity().getRealmName());
	    return entryPoint;
	}
	
	@Bean
	public AuthenticationEntryPoint adminAauthenticationEntryPoint(){
	    BasicAuthenticationEntryPoint entryPoint = 
	      new BasicAuthenticationEntryPoint();
	    entryPoint.setRealmName(appConfig.getSecurity().getAdminRealmName());
	    return entryPoint;
	}
}
