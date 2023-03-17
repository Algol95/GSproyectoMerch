package aplicacion.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import aplicacion.modelo.Usuario;
import aplicacion.persistencia.UsuarioRepo;
import aplicacion.servicio.impl.UsuarioServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UsuarioServiceImpl userDetailsService;

	private UsuarioRepo usuarioRepo;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers(
					"/img/**",
					"/login**",
					"/registro**"
					).anonymous()
			.antMatchers("/productos**",
					"/usuarios**",
					"/admin").hasRole("ADMIN")
			.antMatchers("/tienda","/usuario").authenticated()
			.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/")
				.failureUrl("/login?error=true")
				.usernameParameter("username").passwordParameter("password")
				.and()
				.logout().permitAll()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/?logout")
				.invalidateHttpSession(true)
				.clearAuthentication(true).and()
				
				.csrf().disable();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
	}

}
