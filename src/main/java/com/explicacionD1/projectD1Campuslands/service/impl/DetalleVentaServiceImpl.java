package com.explicacionD1.projectD1Campuslands.service.impl;

import com.explicacionD1.projectD1Campuslands.dto.request.DetalleVentaRequest;
import com.explicacionD1.projectD1Campuslands.dto.response.DetalleVentaResponse;
import com.explicacionD1.projectD1Campuslands.exception.BusinessRuleException;
import com.explicacionD1.projectD1Campuslands.mapper.DetalleVentaMapper;
import com.explicacionD1.projectD1Campuslands.mapper.ProductoMapper;
import com.explicacionD1.projectD1Campuslands.mapper.VentaMapper;
import com.explicacionD1.projectD1Campuslands.model.DetalleVenta;
import com.explicacionD1.projectD1Campuslands.model.Producto;
import com.explicacionD1.projectD1Campuslands.model.Venta;
import com.explicacionD1.projectD1Campuslands.repository.DetalleVentaRepository;
import com.explicacionD1.projectD1Campuslands.repository.ProductoRepository;
import com.explicacionD1.projectD1Campuslands.repository.VentaRepository;
import com.explicacionD1.projectD1Campuslands.service.DetalleVentaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleVentaServiceImpl implements DetalleVentaService {
    private final DetalleVentaRepository detalleVentaRepository;
    private final DetalleVentaMapper detalleVentaMapper;
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final VentaMapper ventaMapper;

    @Override
    /*
     * DTO REQUEST DETALLE_VENTA
     * {
     *   "ventaId": 4,
     *   "productoId": 3,
     *   "cantidad": 4,
     *   "subtotal": 40000
     * }
     * */
    public DetalleVentaResponse crear(DetalleVentaRequest dto) {
        System.out.println("ENTRA");
        Producto producto = productoRepository.findById(dto.productoId()).orElseThrow(() -> new EntityNotFoundException("No existe dicho producto a vender"));
        Venta venta = ventaRepository.findById(dto.ventaId()).orElseThrow(() -> new EntityNotFoundException("No existe la venta a relacionar con el detalle."));
        DetalleVenta detalleVenta = detalleVentaMapper.dtoToEntity(dto, producto, venta);
        BigDecimal cantidades=producto.getPrecioVenta();
        return detalleVentaMapper.entityToDto(detalleVentaRepository.save(detalleVenta), ventaMapper.entityToDto(venta), productoMapper.entityToDto(producto));
    }

    @Override
    //                           Entidad(dv) vieja, entidad(dv) nueva.
    public DetalleVentaResponse actualizar(Long id, DetalleVentaRequest dto) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encuentra el detalle de venta a actualizar"));
        Producto producto = productoRepository.findById(dto.productoId()).orElseThrow(() -> new EntityNotFoundException("No existe dicho producto a vender"));
        Venta venta = ventaRepository.findById(dto.ventaId()).orElseThrow(() -> new EntityNotFoundException("No existe la venta a relacionar con el detalle."));
        detalleVentaMapper.updateEntityToDto(detalleVenta, dto, venta, producto);
        return detalleVentaMapper.entityToDto(detalleVentaRepository.save(detalleVenta), ventaMapper.entityToDto(venta), productoMapper.entityToDto(producto));
    }

    @Override
    public void eliminar(Long id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Error, detalle de venta a eliminar no encontrado"));
        detalleVentaRepository.delete(detalleVenta);
    }

    @Override
    public List<DetalleVentaResponse> listarTodos() {
        return detalleVentaRepository.findAll().stream().map(
                p -> detalleVentaMapper.entityToDto(p,
                        ventaMapper.entityToDto(p.getVenta()),
                        productoMapper.entityToDto(p.getProducto()))
        ).toList();
    }

    @Override
    public DetalleVentaResponse buscarPorId(Long id) {
        DetalleVenta detalleVenta=detalleVentaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Error, codigo de detalle de venta no existe."));
        return detalleVentaMapper.entityToDto(detalleVenta, ventaMapper.entityToDto(
                detalleVenta.getVenta()), productoMapper.entityToDto(detalleVenta.getProducto())
        );
    }

    @Override
    public List<DetalleVentaResponse> buscarPorIdProducto(Long id) {
        return detalleVentaRepository.findByProductoId(id).stream().map(
                p -> detalleVentaMapper.entityToDto(p,
                        ventaMapper.entityToDto(p.getVenta()),
                        productoMapper.entityToDto(p.getProducto()))
        ).toList();
    }

    @Override
    public List<DetalleVentaResponse> buscarPorIdVenta(Long id) {
        return detalleVentaRepository.findByVentaId(id).stream().map(
                p -> detalleVentaMapper.entityToDto(p,
                        ventaMapper.entityToDto(p.getVenta()),
                        productoMapper.entityToDto(p.getProducto()))
        ).toList();
    }

    @Override
    public List<DetalleVentaResponse> filtrarPorCantidadesMenorOIgualQue(Double cantidad) {
        return detalleVentaRepository.findByCantidadLessThanEqual(cantidad).stream().map(
                p -> detalleVentaMapper.entityToDto(p,
                        ventaMapper.entityToDto(p.getVenta()),
                        productoMapper.entityToDto(p.getProducto()))
        ).toList();
    }
}
