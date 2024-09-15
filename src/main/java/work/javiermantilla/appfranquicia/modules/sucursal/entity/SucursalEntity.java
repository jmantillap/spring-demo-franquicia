package work.javiermantilla.appfranquicia.modules.sucursal.entity;

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
import work.javiermantilla.appfranquicia.modules.franquicia.entity.FranquiciaEntity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sucursal")
public class SucursalEntity implements Serializable {

	private static final long serialVersionUID = -2166180173676134498L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "id_franquicia", nullable = false, referencedColumnName = "id" )
	private FranquiciaEntity franquicia; 

	@Column(name = "nombre", length = 45)
	private String nombre;

}
