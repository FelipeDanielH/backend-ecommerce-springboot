package com.ecomarket.service.impl;

import com.ecomarket.dto.CategoriaDTO;
import com.ecomarket.model.Categoria;
import com.ecomarket.repository.CategoriaRepository;
import com.ecomarket.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO obtenerCategoriaPorId(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        return convertirADTO(categoria);
    }

    @Override
    public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());

        if (categoriaDTO.getCategoriaPadreId() != null) {
            Categoria categoriaPadre = categoriaRepository.findById(categoriaDTO.getCategoriaPadreId())
                    .orElseThrow(() -> new RuntimeException("Categoría padre no encontrada"));
            categoria.setCategoriaPadre(categoriaPadre);
        }

        Categoria nuevaCategoria = categoriaRepository.save(categoria);
        return convertirADTO(nuevaCategoria);
    }

    @Override
    public CategoriaDTO actualizarCategoria(Integer id, CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());

        if (categoriaDTO.getCategoriaPadreId() != null) {
            Categoria categoriaPadre = categoriaRepository.findById(categoriaDTO.getCategoriaPadreId())
                    .orElseThrow(() -> new RuntimeException("Categoría padre no encontrada"));
            categoria.setCategoriaPadre(categoriaPadre);
        } else {
            categoria.setCategoriaPadre(null);
        }

        return convertirADTO(categoriaRepository.save(categoria));
    }

    @Override
    public void eliminarCategoria(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada");
        }
        categoriaRepository.deleteById(id);
    }

    private CategoriaDTO convertirADTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .categoriaPadreId(categoria.getCategoriaPadre() != null ? categoria.getCategoriaPadre().getId() : null)
                .build();
    }
}
