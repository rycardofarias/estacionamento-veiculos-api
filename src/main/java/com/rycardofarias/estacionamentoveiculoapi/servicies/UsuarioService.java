package com.rycardofarias.estacionamentoveiculoapi.servicies;

import com.rycardofarias.estacionamentoveiculoapi.entities.Usuario;
import com.rycardofarias.estacionamentoveiculoapi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salver(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado.")
        );
    }

    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmarSenha) {

        if(!novaSenha.equals(confirmarSenha)){
            throw new RuntimeException("Nova senha e confirmação de senha são diferentes.");
        }

        Usuario user = buscarPorId(id);

        if(!user.getPassword().equals(senhaAtual)){
            throw new RuntimeException("Senha atual incorreta.");
        }
        user.setPassword(novaSenha);
        return usuarioRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}
