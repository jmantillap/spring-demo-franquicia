package work.javiermantilla.appfranquicia.modules.producto.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.javiermantilla.appfranquicia.modules.sucursal.entity.SucursalEntity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "producto")
public class ProductoEntity implements Serializable {

	private static final long serialVersionUID = 733209426202178370L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_sucursal", nullable = false, referencedColumnName = "id")
	private SucursalEntity sucursal; 

	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;

	@Column(name = "stock", nullable = false)
	private Integer stock;
	
}
