package com.example.demo.service;

import com.example.demo.domain.Producto;
import com.example.demo.dto.ProductoRequest;
import com.example.demo.dto.ProductoResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProductoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Cacheable(value = "productos", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    @Transactional(readOnly = true)
    public Page<ProductoResponse> getProductos(Pageable pageable) {
        Page<Producto> productos = productoRepository.findByActivoTrue(pageable);
        return productos.map(this::mapToResponse);
    }

    @CacheEvict(value = "productos", allEntries = true)
    @Transactional
    public ProductoResponse createProducto(ProductoRequest request) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setCategoria(request.getCategoria());
        producto.setStock(request.getStock());
        producto.setPrecio(request.getPrecio());
        
        Producto guardado = productoRepository.save(producto);
        return mapToResponse(guardado);
    }

    @CacheEvict(value = "productos", allEntries = true)
    @Transactional
    public void deleteProducto(Long id) {
        Producto producto = productoRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado o ya eliminado"));
        
        producto.setActivo(false);
        productoRepository.save(producto);
    }

    private ProductoResponse mapToResponse(Producto producto) {
        ProductoResponse response = new ProductoResponse();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setCategoria(producto.getCategoria());
        response.setStock(producto.getStock());
        response.setPrecio(producto.getPrecio());
        response.setActivo(producto.getActivo());
        response.setCreadoEn(producto.getCreadoEn());
        return response;
    }
}
