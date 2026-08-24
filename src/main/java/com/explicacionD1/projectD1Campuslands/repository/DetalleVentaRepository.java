package com.explicacionD1.projectD1Campuslands.repository;

import com.explicacionD1.projectD1Campuslands.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByProductoId(Long id);
    List<DetalleVenta> findByVentaId(Long id);
    List<DetalleVenta> findByCantidadLessThanEqual(Double cantidad);
}
