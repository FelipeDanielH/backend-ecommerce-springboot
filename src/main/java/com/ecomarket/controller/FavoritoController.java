package com.ecomarket.controller;

import com.ecomarket.dto.favorito.FavoritoDTO;
import com.ecomarket.dto.favorito.FavoritoDetalleDTO;
import com.ecomarket.service.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoService favoritoService;

    @PostMapping("/{usuarioId}/{productoId}")
    public ResponseEntity<FavoritoDTO> agregarFavorito(@PathVariable Integer usuarioId, @PathVariable Integer productoId) {
        return ResponseEntity.ok(favoritoService.agregarFavorito(usuarioId, productoId));
    }

    @DeleteMapping("/{usuarioId}/{productoId}")
    public ResponseEntity<Void> eliminarFavorito(@PathVariable Integer usuarioId, @PathVariable Integer productoId) {
        favoritoService.eliminarFavorito(usuarioId, productoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<FavoritoDTO>> obtenerFavoritos(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(favoritoService.obtenerFavoritos(usuarioId));
    }

    @GetMapping("/detalle/{usuarioId}")
    public ResponseEntity<List<FavoritoDetalleDTO>> obtenerFavoritosDetalle(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(favoritoService.obtenerFavoritosConDetalle(usuarioId));
    }
}
