// repository/ResenaRepository.java
package com.ecomarket.repository;

import com.ecomarket.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena, Integer> {
    List<Resena> findByProductoId(Integer productoId);
}
