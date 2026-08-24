package com.explicacionD1.projectD1Campuslands.service.impl;

import com.explicacionD1.projectD1Campuslands.dto.request.VentaRequest;
import com.explicacionD1.projectD1Campuslands.dto.response.VentaResponse;
import com.explicacionD1.projectD1Campuslands.mapper.VentaMapper;
import com.explicacionD1.projectD1Campuslands.model.Venta;
import com.explicacionD1.projectD1Campuslands.repository.VentaRepository;
import com.explicacionD1.projectD1Campuslands.service.VentaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {
    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;

    @Override
    public VentaResponse guardar(VentaRequest dto) {
        Venta venta = ventaMapper.dtoToEntity(dto);
        return ventaMapper.entityToDto(ventaRepository.save(venta));
    }

    @Override
    public List<VentaResponse> obtenerTodas() {
        List<Venta> ventas = ventaRepository.findAll();
        return ventas.stream().map(ventaMapper::entityToDto).toList();
    }

    @Override
    public VentaResponse obtenerPorId(Long id) {
        Venta venta= ventaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No se encontró la venta"));
        return ventaMapper.entityToDto(venta);
    }

    @Override
    public VentaResponse actualizar(Long id, VentaRequest dto) {
        Venta venta= ventaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No se encontró la venta a actualizar"));
        ventaMapper.updateEntityToDto(venta, dto);
        return ventaMapper.entityToDto(ventaRepository.save(venta));
    }

    @Override
    public void eliminar(Long id) {
        Venta venta= ventaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No se encontró la venta a eliminar"));
        ventaRepository.delete(venta);
    }

    @Override
    public List<VentaResponse> buscarPorTotalMayorQue(BigDecimal total) {
        List<Venta> ventas = ventaRepository.findByTotalGreaterThanEqual(total);
        return ventas.stream().map(ventaMapper::entityToDto).toList();
    }

    @Override
    public List<VentaResponse> filtrarEntreFechas(String fechaInicio, String fechaFin) {
        List<Venta> ventas = ventaRepository.findByFechaBetween(fechaInicio, fechaFin);
        return ventas.stream().map(ventaMapper::entityToDto).toList();
    }
}
