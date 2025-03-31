package com.ecomarket.model;

import com.ecomarket.model.enums.EstadoProducto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Usuario vendedor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;

    @Enumerated(EnumType.STRING)
    private EstadoProducto estado;

    @Column(name = "fecha_publicacion", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaPublicacion = LocalDateTime.now();
}