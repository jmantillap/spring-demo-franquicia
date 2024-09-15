package work.javiermantilla.appfranquicia.modules.sucursal.service;

import java.util.List;

import work.javiermantilla.appfranquicia.modules.sucursal.dto.SucursalDTO;
import work.javiermantilla.appfranquicia.modules.sucursal.dto.SucursalUpdateDTO;

public interface SucursalServices {
	SucursalDTO crearSucursal(SucursalDTO sucursalDTO);
	List<SucursalDTO> getSucursales();
	SucursalDTO updateSucursal(Integer id,SucursalUpdateDTO dto);
}
