package com.ecomarket.repository;

import com.ecomarket.model.CarritoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoDetalleRepository extends JpaRepository<CarritoDetalle, Integer>  {
    Optional<CarritoDetalle> findByCarritoIdAndProductoId(Integer carritoId, Integer productoId);
    List<CarritoDetalle> findByCarritoId(Integer carritoId);
}
