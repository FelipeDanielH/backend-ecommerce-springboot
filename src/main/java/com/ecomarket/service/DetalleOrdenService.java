package com.ecomarket.service;

import com.ecomarket.dto.DetalleOrdenDTO;
import com.ecomarket.model.DetalleOrden;
import java.util.List;

public interface DetalleOrdenService {
    List<DetalleOrdenDTO> obtenerDetallesPorOrdenId(Integer ordenId);
    DetalleOrdenDTO guardarDetalleOrden(DetalleOrdenDTO detalleOrdenDTO);
}
