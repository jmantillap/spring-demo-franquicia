package work.javiermantilla.appfranquicia.modules.reportes.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import work.javiermantilla.appfranquicia.basic.util.GenericMapper;
import work.javiermantilla.appfranquicia.modules.franquicia.dto.FranquiciaDTO;
import work.javiermantilla.appfranquicia.modules.franquicia.entity.FranquiciaEntity;
import work.javiermantilla.appfranquicia.modules.franquicia.service.FranquiciaServices;
import work.javiermantilla.appfranquicia.modules.producto.dto.ProductoDTO;
import work.javiermantilla.appfranquicia.modules.producto.repository.ProductoRepository;
import work.javiermantilla.appfranquicia.modules.reportes.dto.ProductoReporteStock;
import work.javiermantilla.appfranquicia.modules.reportes.dto.ProductoReporteStock.SucursalProducto;
import work.javiermantilla.appfranquicia.modules.reportes.services.ReporteServices;
import work.javiermantilla.appfranquicia.modules.sucursal.dto.SucursalDTO;
import work.javiermantilla.appfranquicia.modules.sucursal.entity.SucursalEntity;
import work.javiermantilla.appfranquicia.modules.sucursal.service.SucursalServices;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReporteServicesImpl implements ReporteServices {

	private final FranquiciaServices franquiciaServices;
	private final SucursalServices sucursalServices;
	private final ProductoRepository productoRepository;
	
	@Override
	public ProductoReporteStock getReporte(Integer idFranquicia) {
		FranquiciaEntity franquicia = this.franquiciaServices
				.getFranquiciaById(idFranquicia);
		
		ProductoReporteStock productoStock= new ProductoReporteStock(
				GenericMapper.map(franquicia, FranquiciaDTO.class),
				new ArrayList<>());
				
		
		List<SucursalEntity> lista= this.sucursalServices.getSucursalesByIdFranquicia(franquicia.getId());		
		lista.stream().forEach(s->{
			List<Object[]> listProductoMax= this.productoRepository.getProductosMaxStockSucursal(s.getId());
			if(!listProductoMax.isEmpty()) {
				
				List<ProductoDTO> listProductoDTO =
						listProductoMax.stream()
						.map(o-> new ProductoDTO(o[3].toString(),Integer.parseInt(o[4].toString()) )
						).toList();
						
				SucursalProducto sucursalProducto = new SucursalProducto(
						new SucursalDTO(s.getId(),s.getNombre()), 
						listProductoDTO);				
				productoStock.getSucursales().add(sucursalProducto);
			}
		});
		
		return productoStock;
	}
	
}
