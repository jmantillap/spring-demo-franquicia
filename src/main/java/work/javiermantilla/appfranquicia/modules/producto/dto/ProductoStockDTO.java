package work.javiermantilla.appfranquicia.modules.producto.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoStockDTO implements Serializable {

	private static final long serialVersionUID = 6198043062145430662L;
	
	@NotNull
	@Positive
	private Integer stock;
	
}
