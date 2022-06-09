package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		//encripta a senha 
		
		return repository.save(usuario);
		//salvaremos o objeto usuario já com a senha modificada
		
	}
	
	public Optional<UserLogin> Logar(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		//pesquisa pelo nome do usuario digitado pelo cliente
		
				if(usuario.isPresent()) {
					//vou comparar a senha digitada pelo usuario com a que tem no objeto
						if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())){
							//método
							/*enconder pega a senha encriptada e a que não está, verificando se são iguais, em caso positivo ele retorna um true
							 * com isso ele entra no if devolvendo a senha encriptada
							 */
							
							String auth = user.get().getUsuario() + ":" + user.get().getSenha();
							              //nome do usuario e a senha
							byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
							String authHeader = "Basic " + new String(encodedAuth);
							
							user.get().setToken(authHeader);
							user.get().setNome(usuario.get().getNome());
							//estou acessando o user através do método set
							
							return user;
			}
		}
				return null;
				//caso não entre dentro do if, ocorrendo isso é subtendido que não existe aquele usuário na base de dados
	}
}
