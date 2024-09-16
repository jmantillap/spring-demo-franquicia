package work.javiermantilla.appfranquicia.modules.producto.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.javiermantilla.appfranquicia.modules.sucursal.dto.SucursalDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoDTO implements Serializable {
	
	private static final long serialVersionUID = -4666772745629603369L;
	@Null
	private Integer id;
	@NotNull
	@NotEmpty
	private String nombre;
	@NotNull
	@Positive
	private Integer idSucursal;
	@Null
	private SucursalDTO sucursal;	
	@NotNull
	@Positive
	private Integer stock;
	public ProductoDTO(String nombre, Integer stock) {
		super();
		this.nombre = nombre;
		this.stock = stock;
	}
	
	
}
