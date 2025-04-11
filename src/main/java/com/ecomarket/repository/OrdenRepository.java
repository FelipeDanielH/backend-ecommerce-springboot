package com.ecomarket.repository;

import com.ecomarket.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    @Query("SELECT DISTINCT o FROM Orden o " +
            "LEFT JOIN FETCH o.detallesOrden d " +
            "LEFT JOIN FETCH d.producto " +
            "WHERE o.comprador.id = :compradorId")
    List<Orden> findByCompradorId(@Param("compradorId") Integer compradorId);
}
