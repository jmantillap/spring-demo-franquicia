package work.javiermantilla.appfranquicia.modules.producto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import work.javiermantilla.appfranquicia.basicos.utils.GenericMapper;
import work.javiermantilla.appfranquicia.modules.producto.dto.ProductoDTO;
import work.javiermantilla.appfranquicia.modules.producto.dto.ProductoUpdateDTO;
import work.javiermantilla.appfranquicia.modules.producto.entity.ProductoEntity;
import work.javiermantilla.appfranquicia.modules.producto.repository.ProductoRepository;
import work.javiermantilla.appfranquicia.modules.producto.service.ProductoServices;
import work.javiermantilla.appfranquicia.modules.sucursal.dto.SucursalDTO;
import work.javiermantilla.appfranquicia.modules.sucursal.entity.SucursalEntity;
import work.javiermantilla.appfranquicia.modules.sucursal.service.SucursalServices;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductoServicesImpl implements ProductoServices {

	private final SucursalServices sucursalServices;
	private final ProductoRepository productoRepository;
	
	@Override
	public ProductoDTO crearProducto(ProductoDTO productoDTO) {

		SucursalEntity sucursal = this.sucursalServices
				.getSucursalById(productoDTO.getIdSucursal());
		ProductoEntity productoEntity = GenericMapper.map(productoDTO, ProductoEntity.class);
		productoEntity.setSucursal(sucursal);
		productoEntity = this.productoRepository.save(productoEntity);
		productoDTO = GenericMapper.map(productoEntity, ProductoDTO.class);
		productoDTO.setId(productoEntity.getId());
		productoDTO.setIdSucursal(sucursal.getId());
		productoDTO.setSucursal(GenericMapper.map(sucursal,SucursalDTO.class));
		return productoDTO;		
	}

	@Override
	public List<ProductoDTO> getProductos() {
		List<ProductoEntity> list= this.productoRepository.findAll();
		return GenericMapper.mapList(list, ProductoDTO.class);
	}

	@Override
	public ProductoDTO updateProducto(Integer id, ProductoUpdateDTO dto) {
		Optional<ProductoEntity> oProducto = this.productoRepository.findById(id);
		if(!oProducto.isPresent()) {
			log.error("El producto con id: {}, no existe.",id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El producto no existe");
		}		
		oProducto.get().setNombre(dto.getNombre());
		ProductoEntity productoEntity= this.productoRepository.save(oProducto.get());
		return GenericMapper.map(productoEntity, ProductoDTO.class);				
	}

	@Override
	public Boolean eliminarProducto(Integer id) {
		Optional<ProductoEntity> oProducto = this.productoRepository.findById(id);
		if(!oProducto.isPresent()) {
			log.error("El producto con id: {}, no existe.",id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El producto no existe");
		}
		this.productoRepository.delete(oProducto.get());		
		return true;
	}
	
	

}
