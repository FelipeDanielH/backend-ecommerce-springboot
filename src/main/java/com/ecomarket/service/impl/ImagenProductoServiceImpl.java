package com.ecomarket.service.impl;

import com.ecomarket.dto.ImagenProductoDTO;
import com.ecomarket.mapper.ImagenProductoMapper;
import com.ecomarket.model.ImagenProducto;
import com.ecomarket.repository.ImagenProductoRepository;
import com.ecomarket.service.ImagenProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImagenProductoServiceImpl implements ImagenProductoService{
    @Autowired
    private ImagenProductoRepository repository;

    @Autowired
    private ImagenProductoMapper mapper;

    @Override
    public ImagenProductoDTO crear(ImagenProductoDTO dto) {
        ImagenProducto entidad = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entidad));
    }

    @Override
    public ImagenProductoDTO obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<ImagenProductoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ImagenProductoDTO actualizar(Integer id, ImagenProductoDTO dto) {
        if (!repository.existsById(id)) return null;
        dto.setId(id);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<ImagenProductoDTO> listarPorProductoId(Integer productoId) {
        return repository.findByProductoId(productoId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
