package com.ecomarket.repository;

import com.ecomarket.model.Favorito;
import com.ecomarket.model.Producto;
import com.ecomarket.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    List<Favorito> findByUsuario(Usuario usuario);
    boolean existsByUsuarioIdAndProductoId(Integer usuarioId, Integer productoId);
    void deleteByUsuarioIdAndProductoId(Integer usuarioId, Integer productoId);
}