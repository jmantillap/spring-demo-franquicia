package work.javiermantilla.appfranquicia.modules.producto.service;

import java.util.List;

import work.javiermantilla.appfranquicia.modules.producto.dto.ProductoDTO;
import work.javiermantilla.appfranquicia.modules.producto.dto.ProductoStockDTO;
import work.javiermantilla.appfranquicia.modules.producto.dto.ProductoUpdateDTO;


public interface ProductoServices {
	ProductoDTO crearProducto(ProductoDTO productoDTO);
	List<ProductoDTO> getProductos();
	ProductoDTO updateProducto(Integer id,ProductoUpdateDTO dto);
	Boolean eliminarProducto(Integer id);
	ProductoDTO updateStock(Integer id,ProductoStockDTO dto);	
	
}
