package com.explicacionD1.projectD1Campuslands.repository;

import com.explicacionD1.projectD1Campuslands.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Query("""
    Select v from Venta v
    Where MONTH(v.fecha)= :mes
    And YEAR(v.fecha)= :anho
    """)
    List<Venta> findByMesYAnho(
            @Param("mes") int mes,
            @Param("anho") int anho
    );
    List<Venta> findByTotalGreaterThanEqual(BigDecimal precio);
    List<Venta> findByFechaBetween(String fechaInicio, String fechaFin);
}
