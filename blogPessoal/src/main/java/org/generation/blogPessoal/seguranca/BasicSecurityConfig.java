package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

//habilita a configuração de Web security
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
/*Basic Security Config (é uma camada) = configura todos os acessos, verifica acesso por acesso, 
para saber se é seguro ou não*/

	@Autowired //são anotações 
	private UserDetailsService userDetailsService;
	//UserDetailsService injetamos de dentro de uma classe que existe dentro de WebSecurityConfigurerAdapter
	
	@Override
	//sobrescreve o método que tem dentro de UserDetailService, o método configure
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	                                                            //throws é uma tratativa de erro
	{
		auth.userDetailsService(userDetailsService);
		
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
	//configure = nome do método
		                         //http = nome do objeto
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers("/usuarios/cadastrar").permitAll() //usuarios/logar = endpoint...
		/*essa configuração serve para liberar endpoints, ou seja liberar alguns caminhos dentro do meu controler 
		 * para que o client tenha acesso sem precisar passar uma chave em token.
		 * No modo geral estou permitindo usuários a se cadastrar e logar
		 */
		.anyRequest().authenticated()
		/*todas as outras requisições deveram ser autenticadas*/
		.and().httpBasic()
		//vamos utilizar o padrão basic pra gerar a chave token
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //indicar qual é o tipo de sessão que vai ser utilizada     //tipo da policy  
        /*uma das finalidades de uma API Rest é que seja STATELESS, ou seja não guarda sessão nenhuma, com isso deixamos 
         * explicito para a IDE, que a sessão a ser guardada é STATELESS
        */
         .and().cors()
         //habilita o cors
         .and().csrf().disable();
         //desabilita o csrf
	}
	
	
}
