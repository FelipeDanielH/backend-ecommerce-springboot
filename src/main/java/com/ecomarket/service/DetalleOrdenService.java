package com.ecomarket.service;

import com.ecomarket.model.DetalleOrden;
import java.util.List;

public interface DetalleOrdenService {
    List<DetalleOrden> obtenerDetallesPorOrdenId(Integer ordenId);
    DetalleOrden guardarDetalle(DetalleOrden detalleOrden);
}
