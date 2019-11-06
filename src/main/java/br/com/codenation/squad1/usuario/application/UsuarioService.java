package br.com.codenation.squad1.usuario.application;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import br.com.codenation.squad1.usuario.domain.Usuario;
import br.com.codenation.squad1.usuario.infra.dto.CadastroUsuarioDTO;
import br.com.codenation.squad1.usuario.infra.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void cadastrar(CadastroUsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setNome(usuarioDTO.getNome());
		usuario.setSenha(gerarHash(usuarioDTO.getSenha()));
		usuario.setToken(UUID.randomUUID().toString());
		usuarioRepository.save(usuario);
	}

	private String gerarHash(String senha) {
		return DigestUtils.md5DigestAsHex(senha.getBytes());
	}
}
