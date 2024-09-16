package work.javiermantilla.appfranquicia.modules.franquicia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import work.javiermantilla.appfranquicia.basicos.utils.GenericMapper;
import work.javiermantilla.appfranquicia.modules.franquicia.dto.FranquiciaDTO;
import work.javiermantilla.appfranquicia.modules.franquicia.dto.FranquiciaUpdateDTO;
import work.javiermantilla.appfranquicia.modules.franquicia.entity.FranquiciaEntity;
import work.javiermantilla.appfranquicia.modules.franquicia.repository.FranquiciaRepository;
import work.javiermantilla.appfranquicia.modules.franquicia.service.FranquiciaServices;

@Service
@Log4j2
@RequiredArgsConstructor
public class FranquiciaServicesImpl implements FranquiciaServices {

	private final FranquiciaRepository franquiciaRepository;

	@Override
	public FranquiciaDTO crearFranquicia(FranquiciaDTO franquiciaDTO) {
		franquiciaDTO.setId(null);
		franquiciaDTO.setEstado(true);
		FranquiciaEntity franquiciaEntity = GenericMapper.map(franquiciaDTO, FranquiciaEntity.class);
		franquiciaEntity = this.franquiciaRepository.save(franquiciaEntity);
		return GenericMapper.map(franquiciaEntity, FranquiciaDTO.class);
	}

	@Override
	public List<FranquiciaDTO> getFranquicias() {		
		List<FranquiciaEntity> list= this.franquiciaRepository.findAll();		
		return GenericMapper.mapList(list, FranquiciaDTO.class);
	}

	@Override
	public FranquiciaDTO updateFranquicia(Integer id, FranquiciaUpdateDTO dto) {		
		Optional<FranquiciaEntity> oFranquicia = this.franquiciaRepository.findById(id);
		if(!oFranquicia.isPresent()) {
			log.error("La franquicia con id: {}, no existe.",id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La Franquicia no existe");
		}
		if(this.franquiciaRepository.getNombreRepetido(id, dto.getNombre())!=null) {
			log.error("El nombre: {} ,ya  existe.",dto.getNombre());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El nombre de la franquicia ya existe");
		}
		oFranquicia.get().setNombre(dto.getNombre());
		FranquiciaEntity franquicia= this.franquiciaRepository.save(oFranquicia.get());
		return GenericMapper.map(franquicia, FranquiciaDTO.class);
	}

	@Override
	public FranquiciaEntity getFranquiciaById(Integer id) {
		Optional<FranquiciaEntity> oFranquicia = this.franquiciaRepository.findById(id);
		if(!oFranquicia.isPresent()) {
			log.error("La franquicia con id: {}, no existe.",id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La Franquicia no existe");
		}
		return oFranquicia.get();
	}
	
	
}
