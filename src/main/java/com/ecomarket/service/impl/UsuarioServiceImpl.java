package com.ecomarket.service.impl;

import com.ecomarket.dto.UsuarioDTO;
import com.ecomarket.model.Carrito;
import com.ecomarket.model.Usuario;
import com.ecomarket.repository.CarritoRepository;
import com.ecomarket.repository.UsuarioRepository;
import com.ecomarket.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    public void crearCarritoParaNuevoUsuario(Usuario usuario) {
        Carrito nuevoCarrito = Carrito.builder()
                .usuario(usuario)
                .fechaCreacion(LocalDateTime.now())
                .build();

        carritoRepository.save(nuevoCarrito);
    }

    @Override
    public List<UsuarioDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO obtenerPorId(Integer id) {
        return usuarioRepository.findById(id)
                .map(this::convertirADTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public UsuarioDTO crear(Usuario usuario) {
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        this.crearCarritoParaNuevoUsuario(usuarioGuardado);
        return convertirADTO(usuarioGuardado);
    }

    @Override
    public UsuarioDTO actualizar(Integer id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setTelefono(usuario.getTelefono());
        usuarioExistente.setDireccion(usuario.getDireccion());
        usuarioExistente.setTipo(usuario.getTipo());
        usuarioExistente.setNumeroCuenta(usuario.getNumeroCuenta());

        return convertirADTO(usuarioRepository.save(usuarioExistente));
    }

    @Override
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO convertirADTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .direccion(usuario.getDireccion())
                .tipo(usuario.getTipo().name())
                .numeroCuenta(usuario.getNumeroCuenta())
                .build();
    }
}
