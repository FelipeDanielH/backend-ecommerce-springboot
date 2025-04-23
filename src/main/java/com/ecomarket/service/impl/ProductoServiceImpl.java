package com.ecomarket.service.impl;

import com.ecomarket.dto.producto.ProductoDTO;
import com.ecomarket.dto.producto.VendedorNombreDTO;
import com.ecomarket.model.Categoria;
import com.ecomarket.model.Producto;
import com.ecomarket.model.Usuario;
import com.ecomarket.model.enums.EstadoProducto;
import com.ecomarket.repository.CategoriaRepository;
import com.ecomarket.repository.ProductoRepository;
import com.ecomarket.repository.UsuarioRepository;
import com.ecomarket.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    // Metodo para convertir Producto a ProductoDTO
    private ProductoDTO convertirAProductoDTO(Producto producto) {
        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .estado(producto.getEstado().name().toUpperCase())
                .vendedorId(producto.getVendedor().getId())  // Asumiendo que Producto tiene una relación con Vendedor
                .categoriaId(producto.getCategoria().getId())  // Asumiendo que Producto tiene una relación con Categoria
                .build();
    }

    // Metodo para convertir ProductoDTO a Producto
    private Producto convertirAProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setId(productoDTO.getId());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setEstado(EstadoProducto.valueOf(productoDTO.getEstado()));

        // Obtener el Vendedor desde el repositorio utilizando el ID
        if (productoDTO.getVendedorId() != null) {
            Usuario vendedor = usuarioRepository.findById(productoDTO.getVendedorId())
                    .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
            producto.setVendedor(vendedor);
        }

        // Obtener la Categoria desde el repositorio utilizando el ID
        if (productoDTO.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            producto.setCategoria(categoria);
        }

        return producto;
    }

    @Override
    public List<ProductoDTO> obtenerTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerPorId(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return convertirAProductoDTO(producto);
    }

    @Override
    public List<ProductoDTO> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerPorVendedor(Integer vendedorId) {
        return productoRepository.findByVendedor_Id(vendedorId)
                .stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerPorCategoria(Integer categoriaId) {
        return productoRepository.findByCategoria_Id(categoriaId)
                .stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> buscarPorRangoPrecio(Double min, Double max) {
        return productoRepository.findByPrecioBetween(BigDecimal.valueOf(min), BigDecimal.valueOf(max))
                .stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO guardarProducto(ProductoDTO productoDTO) {
        Producto producto = convertirAProducto(productoDTO);
        Producto guardado = productoRepository.save(producto);
        return convertirAProductoDTO(guardado);
    }

    @Override
    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoDTO actualizarStock(Integer id, Integer nuevoStock) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setStock(nuevoStock);

        if (nuevoStock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        Producto actualizado = productoRepository.save(producto);

        return convertirAProductoDTO(actualizado);
    }

    @Override
    public VendedorNombreDTO obtenerNombreVendedorPorProductoId(Integer productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Usuario vendedor = producto.getVendedor();
        return VendedorNombreDTO.builder()
                .nombre(vendedor.getNombre())
                .build();
    }
}
