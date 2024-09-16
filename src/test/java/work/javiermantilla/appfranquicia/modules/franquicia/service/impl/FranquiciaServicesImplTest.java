package work.javiermantilla.appfranquicia.modules.franquicia.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.log4j.Log4j2;
import work.javiermantilla.appfranquicia.modules.franquicia.dto.FranquiciaDTO;
import work.javiermantilla.appfranquicia.modules.franquicia.dto.FranquiciaUpdateDTO;
import work.javiermantilla.appfranquicia.modules.franquicia.entity.FranquiciaEntity;
import work.javiermantilla.appfranquicia.modules.franquicia.repository.FranquiciaRepository;
import work.javiermantilla.appfranquicia.modules.franquicia.service.FranquiciaServices;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@ContextConfiguration(classes = { FranquiciaServicesImpl.class })
@Log4j2
class FranquiciaServicesImplTest {
	
	@Autowired
    private FranquiciaServices franquiciaServices;

	@MockBean    
	private FranquiciaRepository franquiciaRepository;

	@Test
	void testCrearFranquicia() {
		when(this.franquiciaRepository.save(any())).thenReturn(new FranquiciaEntity());
		assertNotNull(this.franquiciaServices.crearFranquicia(new FranquiciaDTO()));
	}

	@Test
	void testGetFranquicias() {
		assertTrue(this.franquiciaServices.getFranquicias().isEmpty());
	}

	@Test
	void testUpdateFranquicia() {
		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			this.franquiciaServices.updateFranquicia(10, new FranquiciaUpdateDTO());
		});
		log.info(exception.getMessage());
		assertFalse(exception.getMessage().isEmpty());
		
		Optional<FranquiciaEntity> oFranquicia =Optional.of(new FranquiciaEntity()); 
		when(this.franquiciaRepository.findById(anyInt())).thenReturn(oFranquicia);		
		when(this.franquiciaRepository.save(any())).thenReturn(new FranquiciaEntity());		
		assertNotNull(this.franquiciaServices.updateFranquicia(10, new FranquiciaUpdateDTO()));
		
		when(this.franquiciaRepository.getNombreRepetido(anyInt(), anyString())).thenReturn(new FranquiciaEntity());
		exception = assertThrows(ResponseStatusException.class, () -> {
			FranquiciaUpdateDTO dto= new FranquiciaUpdateDTO();
			dto.setNombre("nombre");
			this.franquiciaServices.updateFranquicia(10, dto);
		});
		log.info(exception.getMessage());
		assertFalse(exception.getMessage().isEmpty());
	}

	@Test
	void testGetFranquiciaById() {
		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			this.franquiciaServices.getFranquiciaById(10);
		});
		log.info(exception.getMessage());
		assertFalse(exception.getMessage().isEmpty());
		
		Optional<FranquiciaEntity> oFranquicia =Optional.of(new FranquiciaEntity()); 
		when(this.franquiciaRepository.findById(anyInt())).thenReturn(oFranquicia);
		assertNotNull(this.franquiciaServices.getFranquiciaById(10));
		
	}

}
