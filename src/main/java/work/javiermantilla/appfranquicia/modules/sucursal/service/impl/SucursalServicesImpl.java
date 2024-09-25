package work.javiermantilla.appfranquicia.modules.sucursal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import work.javiermantilla.appfranquicia.basic.util.GenericMapper;
import work.javiermantilla.appfranquicia.modules.franquicia.dto.FranquiciaDTO;
import work.javiermantilla.appfranquicia.modules.franquicia.entity.FranquiciaEntity;
import work.javiermantilla.appfranquicia.modules.franquicia.service.FranquiciaServices;
import work.javiermantilla.appfranquicia.modules.sucursal.dto.SucursalDTO;
import work.javiermantilla.appfranquicia.modules.sucursal.dto.SucursalUpdateDTO;
import work.javiermantilla.appfranquicia.modules.sucursal.entity.SucursalEntity;
import work.javiermantilla.appfranquicia.modules.sucursal.repository.SucursalRepository;
import work.javiermantilla.appfranquicia.modules.sucursal.service.SucursalServices;

@Service
@RequiredArgsConstructor
@Log4j2
public class SucursalServicesImpl implements SucursalServices {

	private final SucursalRepository sucursalRepository;
	private final FranquiciaServices franquiciaServices;

	@Override
	public SucursalDTO crearSucursal(SucursalDTO sucursalDTO) {
		FranquiciaEntity franquicia = this.franquiciaServices
				.getFranquiciaById(sucursalDTO.getIdFranquicia());
		SucursalEntity sucursal = GenericMapper.map(sucursalDTO, SucursalEntity.class);
		sucursal.setFranquicia(franquicia);
		sucursal = this.sucursalRepository.save(sucursal);
		sucursalDTO = GenericMapper.map(sucursal, SucursalDTO.class);
		sucursalDTO.setId(sucursal.getId());
		sucursalDTO.setIdFranquicia(franquicia.getId());
		sucursalDTO.setFranquicia(GenericMapper.map(franquicia,FranquiciaDTO.class));
		return sucursalDTO;
	}

	@Override
	public List<SucursalDTO> getSucursales() {
		List<SucursalEntity> list = this.sucursalRepository.findAll();		
		return GenericMapper.mapList(list,SucursalDTO.class);
	}

	@Override
	public SucursalDTO updateSucursal(Integer id, SucursalUpdateDTO dto) {
		Optional<SucursalEntity> oSucursal = this.sucursalRepository.findById(id);
		if(!oSucursal.isPresent()) {
			log.error("La sucursal con id: {}, no existe.",id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La Sucursal no existe");
		}		
		oSucursal.get().setNombre(dto.getNombre());
		SucursalEntity sucursal= this.sucursalRepository.save(oSucursal.get());
		return GenericMapper.map(sucursal, SucursalDTO.class);
	}

	@Override
	public SucursalEntity getSucursalById(Integer id) {
		Optional<SucursalEntity> oSucursal = this.sucursalRepository.findById(id);
		if(!oSucursal.isPresent()) {
			log.error("La sucursal con id: {}, no existe.",id);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La Sucursal no existe");
		}
		return oSucursal.get();
	}

	@Override
	public List<SucursalEntity> getSucursalesByIdFranquicia(Integer idFranquicia) {
		return this.sucursalRepository.findByIdFranquicia(idFranquicia);
	}

}
