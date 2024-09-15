package work.javiermantilla.appfranquicia.modules.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import work.javiermantilla.appfranquicia.modules.producto.entity.ProductoEntity;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> { 

}
