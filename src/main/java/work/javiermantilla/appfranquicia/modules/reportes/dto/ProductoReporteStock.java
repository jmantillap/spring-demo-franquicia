package work.javiermantilla.appfranquicia.modules.reportes.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import work.javiermantilla.appfranquicia.modules.franquicia.dto.FranquiciaDTO;
import work.javiermantilla.appfranquicia.modules.producto.dto.ProductoDTO;
import work.javiermantilla.appfranquicia.modules.sucursal.dto.SucursalDTO;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoReporteStock {
	
	private FranquiciaDTO franquicia;
	private List<SucursalProducto> sucursales;
	
	@Data
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class SucursalProducto{
		private SucursalDTO sucursal;
		private List<ProductoDTO> productoXSucursal;
	}

}
