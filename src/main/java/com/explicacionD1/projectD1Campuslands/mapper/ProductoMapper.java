package com.explicacionD1.projectD1Campuslands.mapper;

import com.explicacionD1.projectD1Campuslands.dto.request.ProductoRequest;
import com.explicacionD1.projectD1Campuslands.dto.response.ProductoResponse;
import com.explicacionD1.projectD1Campuslands.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    public ProductoResponse entityToDto(Producto producto) {
        if (producto == null) return null;
        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecioCompra(),
                producto.getPrecioVenta()
        );
    }
    public Producto dtoToEntity(ProductoRequest dto){
        if(dto==null) return null;
        Producto producto=new Producto();
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecioCompra(dto.precioCompra());
        producto.setPrecioVenta(dto.precioVenta());
        return producto;
    }

    public void updateEntityToDto(Producto producto, ProductoRequest dto){
        if(dto==null || producto==null) return;
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecioCompra(dto.precioCompra());
        producto.setPrecioVenta(dto.precioVenta());
    }
}
