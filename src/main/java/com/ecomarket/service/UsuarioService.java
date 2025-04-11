package com.ecomarket.service;

import com.ecomarket.dto.UsuarioDTO;
import com.ecomarket.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioDTO> obtenerTodos();
    UsuarioDTO obtenerPorId(Integer id);
    UsuarioDTO crear(Usuario usuario);
    UsuarioDTO actualizar(Integer id, Usuario usuario);
    void eliminar(Integer id);
}