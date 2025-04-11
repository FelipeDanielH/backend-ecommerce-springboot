package com.ecomarket.controller;

import com.ecomarket.dto.ImagenProductoDTO;
import com.ecomarket.service.ImagenProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagenes-producto")
public class ImagenProductoController {
    @Autowired
    private ImagenProductoService service;

    @PostMapping
    public ImagenProductoDTO crear(@RequestBody ImagenProductoDTO dto) {
        return service.crear(dto);
    }

    @GetMapping("/{id}")
    public ImagenProductoDTO obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<ImagenProductoDTO> listarTodos() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    public ImagenProductoDTO actualizar(@PathVariable Integer id, @RequestBody ImagenProductoDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }

    @GetMapping("/producto/{productoId}")
    public List<ImagenProductoDTO> listarPorProducto(@PathVariable Integer productoId) {
        return service.listarPorProductoId(productoId);
    }
}
