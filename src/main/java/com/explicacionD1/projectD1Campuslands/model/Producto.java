package com.explicacionD1.projectD1Campuslands.model;

import jakarta.persistence.*;
import lombok.*;
import tools.jackson.databind.node.LongNode;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String descripcion;
    @Column(name = "precio_compra", nullable = false)
    private BigDecimal precioCompra;
    @Column(name = "precio_venta", nullable = false)
    private BigDecimal precioVenta;
}
