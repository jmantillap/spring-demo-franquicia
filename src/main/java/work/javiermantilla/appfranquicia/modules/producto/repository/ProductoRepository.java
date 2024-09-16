package work.javiermantilla.appfranquicia.modules.producto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import work.javiermantilla.appfranquicia.modules.producto.entity.ProductoEntity;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> { 


	 @Query(
	            value = "SELECT s.id as idSucursal,s.nombre,p.id as idProducto,p.nombre,p.stock "
	            		+ "FROM sucursal s "
	            		+ "	INNER JOIN producto p ON (p.id_sucursal= s.id) "
	            		+ "WHERE s.id = :idSucursal "
	            		+ "AND p.stock= (SELECT max(p2.stock) "
	            		+ "			  FROM sucursal s2  "
	            		+ "				   INNER JOIN producto p2 ON (p2.id_sucursal = s2.id ) "
	            		+ "              WHERE s2.id= :idSucursal ) ",
	            nativeQuery = true
	    )
	List<Object[]> getProductosMaxStockSucursal(@Param("idSucursal") Integer idSucursal);
}
