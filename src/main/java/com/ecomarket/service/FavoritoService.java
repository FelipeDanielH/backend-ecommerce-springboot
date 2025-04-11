package com.ecomarket.service;

import com.ecomarket.dto.favorito.FavoritoDTO;
import com.ecomarket.dto.favorito.FavoritoDetalleDTO;

import java.util.List;

public interface FavoritoService {
    FavoritoDTO agregarFavorito(Integer usuarioId, Integer productoId);
    void eliminarFavorito(Integer usuarioId, Integer productoId);
    List<FavoritoDTO> obtenerFavoritos(Integer usuarioId);

    List<FavoritoDetalleDTO> obtenerFavoritosConDetalle(Integer usuarioId);
}
