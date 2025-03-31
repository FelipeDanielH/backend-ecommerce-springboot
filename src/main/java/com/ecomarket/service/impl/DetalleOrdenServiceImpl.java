package com.ecomarket.service.impl;

import com.ecomarket.model.DetalleOrden;
import com.ecomarket.repository.DetalleOrdenRepository;
import com.ecomarket.service.DetalleOrdenService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService {

    private final DetalleOrdenRepository detalleOrdenRepository;

    public DetalleOrdenServiceImpl(DetalleOrdenRepository detalleOrdenRepository) {
        this.detalleOrdenRepository = detalleOrdenRepository;
    }

    @Override
    public List<DetalleOrden> obtenerDetallesPorOrdenId(Integer ordenId) {
        return detalleOrdenRepository.findAll()
                .stream()
                .filter(detalle -> detalle.getOrden().getId().equals(ordenId))
                .toList();
    }

    @Override
    public DetalleOrden guardarDetalle(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }
}
