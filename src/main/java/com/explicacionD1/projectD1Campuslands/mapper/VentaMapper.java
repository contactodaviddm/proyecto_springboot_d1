package com.explicacionD1.projectD1Campuslands.mapper;
import com.explicacionD1.projectD1Campuslands.dto.request.VentaRequest;
import com.explicacionD1.projectD1Campuslands.dto.response.VentaResponse;
import com.explicacionD1.projectD1Campuslands.model.Venta;
import org.springframework.stereotype.Component;

@Component
public class VentaMapper {
    public VentaResponse entityToDto(Venta venta) {
        if (venta == null) return null;
        return new VentaResponse(
                venta.getId(),
                venta.getFecha(),
                venta.getTotal()
        );
    }

    public Venta dtoToEntity(VentaRequest dto){
        if(dto==null) return null;
        Venta v=new Venta();
        v.setFecha(dto.fecha());
        v.setTotal(dto.total());
        return v;
    }

    public void updateEntityToDto(Venta venta, VentaRequest dto){
        if(dto==null || venta==null) return;
        venta.setFecha(dto.fecha());
        venta.setTotal(dto.total());
    }
}
