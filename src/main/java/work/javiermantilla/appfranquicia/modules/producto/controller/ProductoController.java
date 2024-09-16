package work.javiermantilla.appfranquicia.modules.producto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import work.javiermantilla.appfranquicia.basicos.dto.GenericResponseDTO;
import work.javiermantilla.appfranquicia.basicos.utils.FranquiciaConstants;
import work.javiermantilla.appfranquicia.modules.producto.dto.ProductoDTO;
import work.javiermantilla.appfranquicia.modules.producto.dto.ProductoStockDTO;
import work.javiermantilla.appfranquicia.modules.producto.dto.ProductoUpdateDTO;
import work.javiermantilla.appfranquicia.modules.producto.service.ProductoServices;


@RestController
@RequestMapping("/api/v1/producto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Log4j2
public class ProductoController {
	private final ProductoServices productoServices;
	private GenericResponseDTO genericResponse;
	
	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {

		log.info("Inicio el producto a crear : {}", productoDTO);
		genericResponse = new GenericResponseDTO(
				this.productoServices.crearProducto(productoDTO),
				true,
				FranquiciaConstants.RESPONSE_CREATED, 
				HttpStatus.OK,
				FranquiciaConstants.TITTLE_CREATED);
		return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "")
	public ResponseEntity<Object> getProductos() {
		log.info("Consulta de lista de productos ");
		genericResponse = new GenericResponseDTO(
				this.productoServices.getProductos(), 
				true,
				FranquiciaConstants.RESPONSE_FIND, 
				HttpStatus.OK, 
				FranquiciaConstants.TITTLE_FIND);
		return new ResponseEntity<>(genericResponse, HttpStatus.OK);
	}
	
	@PatchMapping(value="/{id}")
	public ResponseEntity<Object> actualizarNombre(@Valid @PathVariable Integer id
			,@Valid @RequestBody  ProductoUpdateDTO productoUpdateDTO) {
		
		log.info("Se actualiza el nombre del producto");
		genericResponse = new GenericResponseDTO(
				this.productoServices.updateProducto(id,productoUpdateDTO), 
				true,
				FranquiciaConstants.RESPONSE_UPDATE, 
				HttpStatus.OK, 
				FranquiciaConstants.TITTLE_UPDATE);
		return new ResponseEntity<>(genericResponse, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminarProducto(@Valid @PathVariable Integer id) {
		log.info("Se elimina el producto id: {}",id);
		genericResponse = new GenericResponseDTO(
				this.productoServices.eliminarProducto(id), 
				true,
				FranquiciaConstants.RESPONSE_DELETE, 
				HttpStatus.OK, 
				FranquiciaConstants.TITTLE_DELETE);
		return new ResponseEntity<>(genericResponse, HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping(value="/update-stock/{id}")
	public ResponseEntity<Object> actualizarStock(@Valid @PathVariable Integer id
			,@Valid @RequestBody  ProductoStockDTO productoStockDTO) {
		
		log.info("Se actualiza el stock del producto");
		genericResponse = new GenericResponseDTO(
				this.productoServices.updateStock(id,productoStockDTO), 
				true,
				FranquiciaConstants.RESPONSE_UPDATE, 
				HttpStatus.OK, 
				FranquiciaConstants.TITTLE_UPDATE);
		return new ResponseEntity<>(genericResponse, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/stock-max/{idFranquicia}")
	public ResponseEntity<Object> getProductos(@Valid @PathVariable Integer idFranquicia) {
		log.info("Consulta de lista de productos con más stock en la sucursal "
				+ "perteneciente a la franquicia id: {} ",idFranquicia);
		genericResponse = new GenericResponseDTO(
				this.productoServices.getProductos(), 
				true,
				FranquiciaConstants.RESPONSE_FIND, 
				HttpStatus.OK, 
				FranquiciaConstants.TITTLE_FIND);
		return new ResponseEntity<>(genericResponse, HttpStatus.OK);
	}
	
}
