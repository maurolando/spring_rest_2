package py.edu.facitec.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity					//Libreria Spring-Security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
  	protected void configure(HttpSecurity http) throws Exception {
	    http
		.csrf().disable()
	 	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		   .addFilterAfter(new TokenFilter(), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers("/**").permitAll()
		 .anyRequest().authenticated()
		   .and()
   		.formLogin().disable(); //Formulario JAVA del cliente
	    
	}
	

	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/");
		web.ignoring().antMatchers("/autenticar"); //No es necesario loguearse a estas URL's
		web.ignoring().antMatchers(HttpMethod.GET,"/form");
		web.ignoring().antMatchers("/lib/**");
		web.ignoring().antMatchers("/**/**.ico**");
		
	}
}
