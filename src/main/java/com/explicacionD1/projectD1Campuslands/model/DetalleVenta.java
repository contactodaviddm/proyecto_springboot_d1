package com.explicacionD1.projectD1Campuslands.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_venta")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "venta_fk", nullable = false)
    private Venta venta;
    @ManyToOne
    @JoinColumn(name = "producto_fk", nullable = false)
    private Producto producto;
    @Column(nullable = false)
    private Double cantidad;
    @Column(nullable = false)
    private BigDecimal subtotal;
}
