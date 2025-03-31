package com.ecomarket.repository;

import com.ecomarket.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByVendedor_Id(Integer vendedorId);
    List<Producto> findByCategoria_Id(Integer categoriaId);
    List<Producto> findByPrecioBetween(BigDecimal min, BigDecimal max);
}
