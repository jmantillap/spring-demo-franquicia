package work.javiermantilla.appfranquicia.modules.sucursal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import work.javiermantilla.appfranquicia.modules.sucursal.entity.SucursalEntity;

@Repository
public interface SucursalRepository extends JpaRepository<SucursalEntity, Integer> {

}
