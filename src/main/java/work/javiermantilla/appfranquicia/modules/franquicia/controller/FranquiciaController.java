package work.javiermantilla.appfranquicia.modules.franquicia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import work.javiermantilla.appfranquicia.basic.dto.GenericResponseDTO;
import work.javiermantilla.appfranquicia.basic.util.FranquiciaConstants;
import work.javiermantilla.appfranquicia.modules.franquicia.dto.FranquiciaDTO;
import work.javiermantilla.appfranquicia.modules.franquicia.dto.FranquiciaUpdateDTO;
import work.javiermantilla.appfranquicia.modules.franquicia.service.FranquiciaServices;

@RestController
@RequestMapping("/api/v1/franquicia")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Log4j2
public class FranquiciaController {

	private final FranquiciaServices franquiciaServices;
	private GenericResponseDTO genericResponse;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> crearFranquicia(@Valid @RequestBody FranquiciaDTO franquiciaDTO) {

		log.info("Inicio la franquicia a crear : {}", franquiciaDTO);
		genericResponse = new GenericResponseDTO(this.franquiciaServices.crearFranquicia(franquiciaDTO), true,
				FranquiciaConstants.RESPONSE_CREATED, HttpStatus.OK, FranquiciaConstants.TITTLE_CREATED);
		return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
	}

	@GetMapping(value = "")
	public ResponseEntity<Object> listaFranquicia() {

		log.info("Consulta de lista de franquicias ");
		genericResponse = new GenericResponseDTO(
				this.franquiciaServices.getFranquicias(), 
				true,
				FranquiciaConstants.RESPONSE_FIND, 
				HttpStatus.OK, 
				FranquiciaConstants.TITTLE_FIND);
		return new ResponseEntity<>(genericResponse, HttpStatus.OK);
	}

	@PatchMapping(value="/{id}")
	public ResponseEntity<Object> actualizarNombre(@Valid @PathVariable Integer id
			,@Valid @RequestBody  FranquiciaUpdateDTO franquiciaUpdateDTO) {
		
		log.info("Se actualiza el nombre de la franquicia");
		genericResponse = new GenericResponseDTO(
				this.franquiciaServices.updateFranquicia(id,franquiciaUpdateDTO), 
				true,
				FranquiciaConstants.RESPONSE_UPDATE, 
				HttpStatus.OK, 
				FranquiciaConstants.TITTLE_UPDATE);
		return new ResponseEntity<>(genericResponse, HttpStatus.OK);
	}

}
