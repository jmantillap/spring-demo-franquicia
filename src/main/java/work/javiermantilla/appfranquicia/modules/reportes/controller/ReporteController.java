package work.javiermantilla.appfranquicia.modules.reportes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import work.javiermantilla.appfranquicia.basic.dto.GenericResponseDTO;
import work.javiermantilla.appfranquicia.basic.util.FranquiciaConstants;
import work.javiermantilla.appfranquicia.modules.reportes.services.ReporteServices;

@RestController
@RequestMapping("/api/v1/reporte")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Log4j2
public class ReporteController {
	
	private final ReporteServices reporteServices;
	private GenericResponseDTO genericResponse;
	
	@GetMapping(value = "/franquicia/{idFranquicia}")
	public ResponseEntity<Object> getProductos(@Valid @PathVariable Integer idFranquicia) {
		log.info("Consulta de lista de productos con más stock en la sucursal "
				+ "perteneciente a la franquicia id: {} ",idFranquicia);
		genericResponse = new GenericResponseDTO(
				this.reporteServices.getReporte(idFranquicia), 
				true,
				FranquiciaConstants.RESPONSE_FIND, 
				HttpStatus.OK, 
				FranquiciaConstants.TITTLE_FIND);
		return new ResponseEntity<>(genericResponse, HttpStatus.OK);
	}
}
