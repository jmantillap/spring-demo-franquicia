package work.javiermantilla.appfranquicia.modules.franquicia.service;

import java.util.List;

import work.javiermantilla.appfranquicia.modules.franquicia.dto.FranquiciaDTO;
import work.javiermantilla.appfranquicia.modules.franquicia.dto.FranquiciaUpdateDTO;

public interface FranquiciaServices {
	FranquiciaDTO crearFranquicia(FranquiciaDTO franquiciaDTO);
	List<FranquiciaDTO> getFranquicias();
	FranquiciaDTO updateFranquicia(Integer id,FranquiciaUpdateDTO dto);
}
