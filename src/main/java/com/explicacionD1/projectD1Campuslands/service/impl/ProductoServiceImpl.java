package com.explicacionD1.projectD1Campuslands.service.impl;

import com.explicacionD1.projectD1Campuslands.dto.request.ProductoRequest;
import com.explicacionD1.projectD1Campuslands.dto.response.ProductoResponse;
import com.explicacionD1.projectD1Campuslands.mapper.ProductoMapper;
import com.explicacionD1.projectD1Campuslands.model.Producto;
import com.explicacionD1.projectD1Campuslands.repository.ProductoRepository;
import com.explicacionD1.projectD1Campuslands.service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor//inyección de dependencias por constructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public ProductoResponse guardar(ProductoRequest dto) {
        Producto producto = productoMapper.dtoToEntity(dto);
        return productoMapper.entityToDto(productoRepository.save(producto));
    }

    @Override
    public List<ProductoResponse> obtenerTodas() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public ProductoResponse obtenerrPorId(Long id) {
        Producto producto=productoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No se encontró el producto"));
        return productoMapper.entityToDto(producto);
    }

    @Override
    //                                      Entity old, Entity new
    public ProductoResponse actualizarProducto(Long id, ProductoRequest dto) {
        Producto producto=productoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No se encontró el producto a actualizar"));
        productoMapper.updateEntityToDto(producto, dto);
        return productoMapper.entityToDto(productoRepository.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {
        Producto producto=productoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No se encontró el producto a eliminar"));
        productoRepository.delete(producto);
    }

    @Override
    public List<ProductoResponse> buscarPorNombre(String nombre) {
        List<Producto> productos = productoRepository.findByNombre(nombre);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoResponse> filtrarPrecioVentaMayorOIgualQue(BigDecimal precio) {
        List<Producto> productos = productoRepository.findByPrecioVentaGreaterThanEqual(precio);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoResponse> filtrarPrecioVentaMenorOIgualQue(BigDecimal precio) {
        List<Producto> productos = productoRepository.findByPrecioVentaLessThanEqual(precio);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoResponse> filtrarPrecioVentaEntre(BigDecimal precio1, BigDecimal precio2) {
        List<Producto> productos = productoRepository.findByPrecioVentaBetween(precio1, precio2);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoResponse> filtrarPorNombreYPrecioVentaMayorOIgualQue(String nombre, BigDecimal precio) {
        List<Producto> productos = productoRepository.findByNombreAndPrecioVentaGreaterThanEqual(nombre, precio);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }
}
