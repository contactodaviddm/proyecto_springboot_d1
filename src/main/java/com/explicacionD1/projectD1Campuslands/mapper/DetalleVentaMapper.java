package com.explicacionD1.projectD1Campuslands.mapper;

import com.explicacionD1.projectD1Campuslands.dto.request.DetalleVentaRequest;
import com.explicacionD1.projectD1Campuslands.dto.response.DetalleVentaResponse;
import com.explicacionD1.projectD1Campuslands.dto.response.ProductoResponse;
import com.explicacionD1.projectD1Campuslands.dto.response.VentaResponse;
import com.explicacionD1.projectD1Campuslands.model.DetalleVenta;
import com.explicacionD1.projectD1Campuslands.model.Producto;
import com.explicacionD1.projectD1Campuslands.model.Venta;
import org.springframework.stereotype.Component;

@Component
public class DetalleVentaMapper {
    public DetalleVentaResponse entityToDto(DetalleVenta detalleVenta, VentaResponse ventaDto, ProductoResponse productoDto){
        if(detalleVenta==null) return null;
        return new DetalleVentaResponse(
                detalleVenta.getId(),
                ventaDto,
                productoDto,
                detalleVenta.getCantidad(),
                detalleVenta.getSubtotal()
        );
    }
    public DetalleVenta  dtoToEntity(DetalleVentaRequest  dto, Producto producto, Venta venta){
        if(dto==null || producto==null || venta==null) return null;
        DetalleVenta dv=new DetalleVenta();
        dv.setVenta(venta);
        dv.setProducto(producto);
        dv.setCantidad(dto.cantidad());
        dv.setSubtotal(dto.subtotal());
        return dv;
    }
    public void updateEntityToDto(DetalleVenta dv, DetalleVentaRequest dto, Venta venta, Producto producto){
        if(dto==null || venta==null || dv==null || producto==null) return;
        dv.setVenta(venta);
        dv.setProducto(producto);
        dv.setCantidad(dto.cantidad());
        dv.setSubtotal(dto.subtotal());
    }
}
